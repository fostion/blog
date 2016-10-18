### AndroidInterview

#### Java


#### Android

- dvm的进程和Linux的进程, 应用程序的进程是否为同一个概念
> Dvm的进程是dalivk虚拟机进程,每个android程序都运行在自己的进程里面,每个android程序系统都会给他分配一个单独的liunx uid(user id),每个dvm都是linux里面的一个进程.所以说这两个进程是一个进程.


- android数字证书
> android通过数字签名来标识应用程序的作者和在应用程序之间建立信任关系，不是用来决定最终用户可以安装哪些应用程序。
 这个数字签名由应用程序的作者完成，并不需要权威的数字证书签名机构认证，它只是用来让应用程序包自我认证的.

- AIDL的全称是什么？如何工作？
> 把对象拆分成操作系统能理解的简单形式, 以达到跨界对象访问的目的. 在J2EE中,采用RMI的方式, 可以通过序列化传递对象. 在Android中, 则 采用AIDL的方式. 理论上AIDL可以传递Bundle,实际上做起来却比较麻烦。 AIDL(AndRoid接口描述语言)是一种借口描述语言; 编译器可以通过aidl文件生成一段代码，通过预先定义的接口达到两个进程内部通信进程的目的. 如果需要 在一个Activity中, 访问另一个Service中的某个对象, 需要先将对象转化成AIDL可识别的参数(可能是多个参数), 然后使用AIDL来传递这些参数, 在消息的接收端, 使用 这些参数组装成自己需要的对象.AIDL的IPC的机制和COM或CORBA类似, 是基于接口的，但它是轻量级的。它使用代理类在客户端和实现层间传递值.
<br>
>需要完成两件事情:
>>1.引入AIDL的相关类.
<br/>
>>2.调用aidl产生的class. AIDL的创建方法: AIDL语法很简单,可以用来声明一个带一个或多个方法的接口，也可以传递参数和返回值。由于远程调用的需要, 这些参数和返回值并不是任何类型.
>
> AIDL支持的数据类型:
>>1.不需要import声明的简单Java编程语言类型(int,boolean等)
<br/>
>>2.String, CharSequence不需要特殊声明
<br/>
>>3.List, Map和Parcelables类型, 这些类型内所包含的数据成员也只能是简单数据类型, String等其他比支持的类型.

- android引入广播机制
> a:从MVC的角度考虑引入广播机制可以方便几大组件的信息和数据交互。
b：程序间互通消息(例如在自己的应用程序内监听系统来电)
c：效率上(参考UDP的广播协议在局域网的方便性)
d：设计模式上(反转控制的一种应用，类似监听者模式)

- handler机制的原理
> andriod提供了Handler和Looper来满足线程间的通信。Handler先进先出原则。Looper类用来管理特定线程内对象之间的消息交换(MessageExchange)。 1)Looper:一个线程可以产生一个Looper对象，由它来管理此线程里的MessageQueue(消息队列)。 2)Handler:你可以构造Handler对象来与Looper沟通，以便push新消息到MessageQueue里;或者接收Looper从MessageQueue取出)所送来的消息。 3)MessageQueue(消息队列):用来存放线程放入的消息。 4)线程：UIthread通常就是mainthread，而Android启动程序时会替它建立一个MessageQueue.

- ANR出现情况及如何避免
> ApplicationNotResponding一般出现在Android主线程操作耗时操作所引发的情况。
<br/>
一般出现场景：
>>1.在5秒内没有响应输入的事件（例如:按键按下,屏幕触摸）
<br/>
>>2.BroadcastReceiver在10秒内没有执行完毕
<br/>
>>3.主线程操作io，网络请求，耗时计算，图片处理等
>
>避免ANR:
>>1. 尽量不要在onCreate和onResume做耗时操作，采取：Handler+Thread 或者 AsyncTask的方式去异步进行（io,数据库，图片处理，网络）
>>2. 在BroadcastReceiver和Service也不要做耗时操作
>
>从 /data/anr/traces.txt文件中获取ANR信息
>> 留意thread.wait,thread.sleep等信息，找出关键行数即可
