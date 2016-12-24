## Android 界面分析

`ViewManager`
介绍:WindowManager接口规范
- addView() //添加view
- updateViewLayout() //更新viewlayout
- removeView() //移除view

`WindowManager`
介绍:一个Window接口资源管理集合,定义一些类型和常量,以及像是LayoutParams,Window的数据备份操作

`WindowManagerImpl`
介绍:WindowManager接口的实现类,实现具体方法
- WindowManagerGlobal 单例:全局View显示隐藏操控,放在Windwod内部使用,不在其他地方使用
- Display 输出设备
- Window
- IBinder

`WindowManagerGlobal `
介绍:WindowManager全局管理类,具有锁机制(涉及多线程更新,锁住布局),单例获取,真正实现`ViewManager`接口的类
- ArrayList Views //所有view
- ArrayList ViewRootImpls //所有ActivityView的顶级图层
- getWindowManagerService() //获取wms服务
- getWindowSession //获取ws

`ViewRootImpl`
介绍:View的顶级图层,对接View和WindowManager
这里包含了View涉及的更新操作,更新,输入,滑动等
- W类 ipc中IBinder与wms通信
- AttachInfo 一系列信息当view绑定到父window的时候,这个信息包含IBinder等信息一并传递到子ViewGroup和View

` Window类`
介绍:虚类,包含style/Ibinder联通方式
- WindowManager mWindowManager
- IBinder mAppToken

可以化界面区别:
1. Actvity 使用的是PhoneWindow
2. Dialog使用的是PhoneWindow,按钮事件自动监听
3. PopupWindow使用的父类的PhoneWindow,按钮事件被截取后监听



参考资料:
[浅谈窗口](http://bugly.qq.com/bbs/forum.php?mod=viewthread&tid=555&extra=page%3D1)
