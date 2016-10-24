### **AndroidInterview**

#### **Java**

#### **Android**
##### dvm的进程和Linux的进程, 应用程序的进程是否为同一个概念
```
Dvm的进程是dalivk虚拟机进程,每个android程序都运行在自己的进程里面,每个android程序系统都会给他分配一个单独的liunx uid(user id),每个dvm都是linux里面的一个进程.所以说这两个进程是一个进程.
```

##### android数字证书
```
android通过数字签名来标识应用程序的作者和在应用程序之间建立信任关系，不是用来决定最终用户可以安装哪些应用程序。这个数字签名由应用程序的作者完成，并不需要权威的数字证书签名机构认证，它只是用来让应用程序包自我认证的.
```

##### Android如何动态加载代码
```
ClassLoader分类
  1. BootClassLoader:系统启动时创建
  2. PathClassLoader:应用启动时创建,只能加载系统安装过的apk
  3. DexClassLoader:可以加载jar/apk/dex，可以在sd卡中加载未安装过的apk(插件化原理)

ClassLoader加载特点（双亲代理模型）
  1. 查询当前ClassLoader实例是否加载过此类，有就返回
  2. 如果没有，查询Parent是否加载过此类，如果已经加载过，直接返回Parent加载过的类
  3. 如果继承路线上的ClassLoader都没有加载，才由Child执行类加载工作

优点：
  1. 共享功能，一些Framework层级的类一旦被加载了，任何地方都不需要重新加载。
  2. 隔离功能，不同继承线路上的ClassLoader加载的类肯定不是同一个类，这样避免用户自己的类冒充核心类

注意事项:
  若是想要使用这种方式修复bug，则必须保证包含补丁dex文件先与旧dex加载，这样旧类才不会再加载，从而使用新的补丁类,若是使用不同ClassLoader可以加载旧类和新类，这样将会出现一些问题
  同一个类 = 相同的ClassName + PackageName +　ClassLoader

Android与Java程序使用上的区别:
  1. Android许多组件都需要Manifest文件中注册才能工作，如果新加载进来的组件类没有注册的话，是无法运行的
  2. Res资源在Android开发中经常使用，这些资源用对应 id，运行时通过id从Resource获取对应的资源，如果动态加载进来的新类采用新的资源，那么会出现异常
```



##### 多进程问题
```
两个相同的Activity,Application会有怎样的变化？
  每个应用进程都会配备虚拟机，不同虚拟机在内存分配上有不同的地址空间，这就导致在不同的虚拟机中访问同一个类的对象会产生多份副本。

如何开启开启新的进程
  在AndroidManifest文件中，添加android:process=":demao",请中 : 代表应用程序的私有进程，私有进程名称会自动在 : 前加上包名，而全局进程不会，一般情况下，都是使用应用的私有进程

多进程的优点及缺点
  优点是分担主进程的内存压力，因为android内存限制主要限制虚拟机，每个虚拟机是进程，所以能分担内存压力，缺点是占用系统内存，容易导致内存占满手机卡顿的问题。

多进程引起的问题
  1. 静态成员和单例模式失效
  2. 线程同步机制完全失效
  3. SharePreferences可靠性下降
  4. Application会多次创建

跨进程通信（IPC）
  1. 使用Bundle
  2. 使用文件共享
  3. 使用Messager(例：service返回IBinder对象创建Messager,使用Messager向service发送消息，若客户端需要发消息，则创建Handler,通过Message的replyTo参数传递消息)
  4. AIDL
  5. ContentProvider
  6. Socket
```


##### AIDL的全称是什么？如何工作？
```
AIDL(Android Interface definition language)是一种接口描述语言; 编译器可以通过aidl文件生成一段代码，通过预先定义的接口达到两个进程内部通信进程的目的. 如果需要 在一个Activity中, 访问另一个Service中的某个对象, 需要先将对象转化成AIDL可识别的参数(可能是多个参数), 然后使用AIDL来传递这些参数, 在消息的接收端, 使用 这些参数组装成自己需要的对象.AIDL的IPC的机制和COM或CORBA类似, 是基于接口的，但它是轻量级的。它使用代理类在客户端和实现层间传递值.

需要完成两件事情:
  1.引入AIDL的相关类.
  2.调用aidl产生的class. AIDL的创建方法: AIDL语法很简单,可以用来声明一个带一个或多个方法的接口，也可以传递参数和返回值。由于远程调用的需要, 这些参数和返回值并不是任何类型.

AIDL支持的数据类型:
  1.不需要import声明的简单Java编程语言类型(int,boolean等)
  2.String, CharSequence不需要特殊声明
  3.List, Map和Parcelables类型, 这些类型内所包含的数据成员也只能是简单数据类型, String等其他比支持的类型.

```

##### handler机制的原理
```
andriod提供了Handler和Looper来满足线程间的通信。Handler先进先出原则。Looper类用来管理特定线程内对象之间的消息交换(MessageExchange)。

  1.Looper:一个线程可以产生一个Looper对象，由它来管理此线程里的MessageQueue(消息队列).
  2.Handler:你可以构造Handler对象来与Looper沟通，以便push新消息到MessageQueue里;或者接收Looper从MessageQueue取出)所送来的消息。
  3.MessageQueue(消息队列):用来存放线程放入的消息。
  4.线程：UIthread通常就是mainthread，而Android启动程序时会替它建立一个MessageQueue.
```

##### ANR出现情况及如何避免
```
ApplicationNotResponding一般出现在Android主线程操作耗时操作所引发的情况。

出现场景：
  1.在5秒内没有响应输入的事件（例如:按键按下,屏幕触摸）
  2.BroadcastReceiver在10秒内没有执行完毕
  3.主线程操作io，网络请求，耗时计算，图片处理等

避免ANR:
  1.尽量不要在onCreate和onResume做耗时操作，采取：Handler+Thread 或者 AsyncTask的方式去异步进行（io,数据库，图片处理，网络）
  2.在BroadcastReceiver和Service也不要做耗时操作

从 /data/anr/traces.txt文件中获取ANR信息,留意thread.wait,thread.sleep等信息，找出关键行数即可

```


##### Fragment生命周期
![Fragment生命周期](../art/fragment_lifestyle.png)

##### Activity和Fragment生命周期对比
![Activity和Fragment的生命周期对照](../art/activity_fragment_lifestyle.png)

##### 根据上面生命周期图，可以得出以下结论：
```
 1.onAttach()：当fragment和activity被关联的时候调用
 2.onCreateView():当创建fragmentUI初始化调用
 3.onActivityCreated():当activity的onCrate方法返回时调用
 4.onDestoryView():当fragmentUI被移除的时候调用
 5.onDetach():当fragment和activity关联时调用
 6.其他生命周期则是跟activity生命周期一起调用
 7.当activity处于Resumed状态时，可以自由添加和移除fragment,此时fragment的状态可以独立改变
```
