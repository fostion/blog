##ReactNative 
### 1. 环境搭建 window 
[官网搭建教程](http://facebook.github.io/react-native/docs/getting-started.html) [中文搭建教程](http://www.open-open.com/lib/view/open1451012403948.html) [学习网站](http://reactnative.cn/docs/0.22/native-modules-android.html#content)

1. 所需环境
`nodejs` `python2` `jdk8`  
可以使用[Chocolatey](https://chocolatey.org/)包管理器，下载所需环境

2. 下载React-natvie
```
npm install -g react-native-cli
```

3. 搭建android开发环境

4. 创建ReactNative项目
```
react-native init AwesomeProject
```

5. 启动服务
进入AwesomeProject文件夹下， 执行命令npm start,启动任务，看到开启服务器端口8081,在浏览器打链接 [http://localhost:8081/index.android.bundle?platform=android](http://localhost:8081/index.android.bundle?platform=android)

6. 编译运行应用
```
react-native run-android
```
可以使用AndroidStudio编译，直接使用AndroidStudio打开android项目，编译即可安装，但是必须执行第5步骤，不然看到的是空白，同时需要授予权限

`注意`:真机运行会出现无法加载Js现象，需要使用命令
```
adb reverse tcp:8081 tcp:8081
```
[详情](http://www.open-open.com/lib/view/open1451012403948.html)


###2.项目结构
1. 架构： node.js开发服务器， 设备ios/android, 开发过程中，会将更改js文件更新到客户端,最终发版本时，会将jsBundle文件打包到应用内

2. 文件架构
```
-project
--__tests__/
--node_modules/
--android/  android代码
--ios/      ios代码
--app.json           
--index.ios.js  ios页面显示内容
--index.android.js  android页面显示内容
--package-lock.json       
--package.json
```
###
 
3. 组件化思想    
通过集成的方式将实现致自己的标签，分开不同js实现不同功能
1.继承Component，实现render方法返回dom，实现相应的功能
2.在主js文件进行组装，而不同组件可以在不同js实现，实现解耦

4. 布局方式   

[参考资料](http://blog.csdn.net/fengyuzhengfan/article/details/52090154)    



5. 增加新的模块   

在ReactNative中不使用原生的音频View需要引入进来   
例如,Video
```
# 1.安装
npm install react-native-video

# 2.刷新
rnpm link react-native-video 

其他：
react-native-vector-icons//字体图标
react-native-image-picker//读入照片
react-native-camera//相机
react-native-linear-gradient//颜色渐变
react-native-search-bar//search bar
react-native-tableview//原生的tableview，可以在list右侧显示索引
react-native-video  //Video组件
```
[Video集成文档](http://blog.csdn.net/DeepLies/article/details/53054628)
```
<Video source={{uri: "background"}} // 视频的URL地址，或者本地地址，都可以. 
    rate={1.0}                   // 控制暂停/播放，0 代表暂停paused, 1代表播放normal. 
    volume={1.0}                 // 声音的放大倍数，0 代表没有声音，就是静音muted, 1 代表正常音量 normal，更大的数字表示放大的倍数 
    muted={false}                // true代表静音，默认为false. 
    paused={false}               // true代表暂停，默认为false 
    resizeMode="cover"           // 视频的自适应伸缩铺放行为，
    repeat={true}                // 是否重复播放 
    playInBackground={false}     // 当app转到后台运行的时候，播放是否暂停
    playWhenInactive={false}     // [iOS] Video continues to play when control or notification center are shown. 仅适用于IOS
    onLoadStart={this.loadStart} // 当视频开始加载时的回调函数
    onLoad={this.setDuration}    // 当视频加载完毕时的回调函数
    onProgress={this.setTime}    //  进度控制，每250ms调用一次，以获取视频播放的进度
    onEnd={this.onEnd}           // 当视频播放完毕后的回调函数
    onError={this.videoError}    // 当视频不能加载，或出错后的回调函数
    style={styles.backgroundVideo} />
```


 

问题：
1.  执行机制，布局文件，代码怎么写
2.  怎样调用原生代码
3.  demo版开发
4.  怎样跳转，怎样显示其他界面，java层不写代码，



