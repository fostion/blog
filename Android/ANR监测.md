## ANR监测

#### 检测ANR的两种方式

1. 利用Looper 使用handler不断发消息，若在超过时间内未执行将视为ANR

2. 使用Choreographer，监听阶段时间内帧数，计算出fps，判断是否ANR

#### 参考博客
**第一类型**   
[BlockCanary — 轻松找出Android App界面卡顿元凶](http://blog.zhaiyifan.cn/2016/01/16/BlockCanaryTransparentPerformanceMonitor/?utm_source=tuicool&utm_medium=referral)   
[AndroidPerformanceMonitor](https://github.com/markzhai/AndroidPerformanceMonitor)    
[AnrWatcher](http://blog.zhaiyifan.cn/2016/01/16/BlockCanaryTransparentPerformanceMonitor/?utm_source=tuicool&utm_medium=referral)   
[ANR-WatchDog](https://github.com/SalomonBrys/ANR-WatchDog)


**第二类型**    
[Android系统Choreographer机制实现过程](http://blog.csdn.net/yangwen123/article/details/39518923)   
[Takt](https://github.com/wasabeef/Takt/)
