#### Android TV
[官网](https://developer.android.com/tv/index.html)

##### TV App 和 手机 App区别
1. 距离：电视和人距离比手机和人距离远，所以可能有些小细节无法注意，同时文本小会看不见
2. 操作方式：电视采用的是远程控制设备，而手机则是触碰方式

#### google应用市场发布规则
[介绍](https://developer.android.com/distribute/googleplay/tv.html)


#### 标准（google pay 上架标准）
[相关资料](https://developer.android.com/distribute/essentials/quality/tv.html#ux)
- Launcher
 - TV-LM:应用安装后显示的`Launcher icon`
 - TV-LB:应用在显示在 302px x 180px 全大小banner`Launcher icon`
 - TV-BN:应用显示在banner包含icon和名字
 - TV-LG:应用属于游戏类型，且显示在游戏栏
 - TV-LS:用户选择桌面banner且运行应用成功   
 <br/>

- Layout
 - TV-LO：layout摆放规则

 ```
 1. 布局保持一个方向，电视屏幕是横向的
 2. 导航栏放在屏幕最左或最右，保持内容的垂直空间
 3. 界面最好分为几部分内容，使用Fragment,GridView
 4. 使用RelativeLayout和LinearLayout控制子View，这些组件允许系统适配
 5. 设置margin属性，避免混乱子view
 ```  
 - TV-TC:显示重要文本必须在16sp以上
 - TV-TA:应用所有字体显示在12sp以上
 - TV-OV:过长字体需要控制，不要显示切断样式
 - TV-TR:应用程序不遮盖其他应用程序，且应用程序填充整个屏幕，并非有一个透明背景  
 <br/>

- Navigation
 - TV-DP:导航栏由方向键控制，否则使用游戏方向控制
 - TV-DK:如果应用需要游戏控制器，参考TV-GP,所有导航栏的使用都是游戏控制器的按键
 - TV-DM:应用的菜单选项并不是依赖于电视遥控的菜单按钮   
 <br/>

- Search
 - TV-SD:搜索结果使用导航方向键控制，除非时游戏控制器
 - TV-SB:搜索查询需要显示搜索输入框，类似提供一个SearchFragment,结果是与搜索内容相关   
 <br/>

- Manifest
 - TV-ML:Manifest设置intent type 为 `ACTION_MAIN`,category 为 `CATEGORY_LEANBACK_LAUNCHER`
 - TV-MT:Manifest不需要设置硬件特性 `android.hardware.touchscreen`   
<br/>

- Game Controllers
 - TV-GP:如果要使用游戏控制器作为输入来源，需要在manifest声明`<uses-feature>`
 - TV-GC:如果应用使用游戏控制器提供虚拟命令，需要提供说明和相应的按钮布局   
 <br/>

- Advertising
 - TV-AP:应用程序允许方向键控制转换广告
 - TV-AD:对于那些使用全屏和没有视频的广告，方向键可以切换广告
 - TV-AU:对于那些可点击，非全屏，没有视频的广告，应用内不要内嵌广告链接
 - TV-AA:对于那些可以点击，非全屏，没有视频的广告，不可以点击后前换到另外一个应用   
<br/>

- Web Content
 - TV-WB:使用webview组件打开网页，禁止使用系统的web浏览器   
<br/>

- Media Playback
 - TV-NP: 如果用户离开时还在播放音频或者视频，应用需要提供一个`Now Playing`在桌面控制栏，一般用户控制
 - TV-PA:如果用户点击了`Now Playing`，将要跳转到用户可以暂停播放的页面
 - TV-PP:播放音频或者视频时，切换播放和暂停，将会发送事件
