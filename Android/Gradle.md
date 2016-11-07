## Gradle

[官网](https://gradle.org/)   
[Android使用](https://developer.android.com/studio/releases/gradle-plugin.html)   
[中文学习资料](https://developer.android.com/studio/releases/gradle-plugin.html)
[深入了解gradle](chrome-extension://ikhdkkncnoglghljlkmcimlnlhkeamad/pdf-viewer/web/viewer.html?file=http%3A%2F%2F7xt41p.com2.z0.glb.clouddn.com%2F%25E6%25B7%25B1%25E5%2585%25A5%25E7%2590%2586%25E8%25A7%25A3Android%25E4%25B9%258BGradle%2528%25E5%25AE%258C%25E6%2595%25B4%25E7%2589%2588%2529.pdf)   
[详细使用](https://segmentfault.com/a/1190000004229002)

**构建的生命周期**
gradle一共有两个重要概念，分别是project和tasks，每次构建至少有一个project完成，每个build.gradle文件代表一个project,tasks是在gradle文件中定义,一旦tasks被执行，那么他就不再执行，不包含依赖的Tasks先执行，三个阶段：
1. 初始化阶段：多个project实例创建
2. 配置阶段：build.gradle脚本将会执行，为每个project创建和配置所有tasks
3. 执行阶段：gradle决定哪个tasks会先执行，哪个tasks会被执行完全依赖开始构建时传入的参数和所在文件夹位置有关

**Task类型**
- assemble任务，执行项目所有项目输出(打包)
- check任务，执行所有所有校验
- build任务，执行assemble和check所有工作
- clean任务，清除项目所有输出

**命令**
```
//查看某个项目的tasks任务
//例如:gradle app:tasks 或者 AndroidStudio右边栏可以看到
gradle 目录名:tasks

//查看配置信息
gradle properties
```

**Gradle 执行顺序**
1. root project 中的 setting.gradle
2. root project 中的 build.gradle
3. 各个subproject中的build.gradle

**项目输出**
- assembleDebug  `gradle assembleDebug `
- assembleRelease `gradle assembleRelease / gradle aR`
- 构建某个渠道 `gradle assembleXiaoMi`

**设置全局变量**
支持project 和 gradle 对象添加属性，例如 andorid sdk版本
```gradle
//在Project的build.gradle设置
ext{
	params = '123'
}

//子项目中应用
subParams = rootProject.ext.params
//或者 ext省略
subParams = rootProject.params
```

**配置资源文件**
```gradle
android{
//项目源文件配置
  sourceSets{
     main{
       //定义资源文件位置
       res.srcDirs = ['src/main/res']
       //定义jni文件位置
       jniLibs.srcDirs = ['ibs']
       //定义assets文件位置
       assets.srcDirs = ['assets']
     }
  }
}
```

**配置不同的作用的安装包**
BuildTypes默认会生成debug和release两种不同的安装包，区别在于debug是用作调试作用,release用作正式上线签名,主要是apk配置问题
```gradle
android{
//配置key
 signingConfigs {
 debug {
	 Properties p = new Properties()
	 p.load(new FileInputStream( project.file('../keystore.properties')))
	 storeFile file("../test.jks"
     storePassword p.KEYSTORE_PASSWORD
     keyAlias p.KEY_ALIAS
     keyPassword p.KEY_PASSWORD
 }

 release {
	 Properties p = new Properties()
     p.load(new FileInputStream( project.file('../keystore.properties')))
	 storeFile file("../test.jks"
     storePassword p.KEYSTORE_PASSWORD
     keyAlias p.KEY_ALIAS
     keyPassword p.KEY_PASSWORD
 }
}


//设置buildTypes
 buildTypes {
  debug {
  //applicatonId添加.debug后缀
  //applicationIdSuffix '.debug'
  //文件名添加.debug后缀
  //packageNameSuffix '.debug'
   minifyEnabled false
   //定义签名
   signingConfig signingConfigs.debug
  }

  release {
   minifyEnabled false
   //定义签名
   signingConfig signingConfigs.release
   //设置混淆规则
   proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
  }
}
}
```

**第三方key配置**
```gradle
defaultConfig{
//value为字符串所以 value1 = \"value值\"
// 往 BuildConfig 中添加变量
buildConfigField "String", "key1","value1"
buildConfigField "String", "key2","value2"

// 替换 Manifest 中的值
manifestPlaceholders = [
  key1 :  'value1',
  key2 :  'value2'
 ]
}
```

**包依赖**
项目结构:
```
MyProject/
	| settings.gradle
   app/
	| build.gradle
 library/
  lib1/
    | build.gradle
  lib2/
    |build.gradle

```
`setting.gradle`设置
```
include ":app",":library:lib1"
```

app `build.gradle`配置：
```
compile project(':library:lib1')
```

**远程仓库设置**
```gradle
1. 默认
repositories {
  jcenter()
  //mavenCentral()  
  //mavenLocal()
}

2. maven远程
repositories {
  maven {
      url "http://repo.acmecorp.com/maven2"
  }
}

3. Ivy
repositories {
  maven {
      url "http://repo.acmecorp.com/maven2"
      credentials {
          username 'user'
          password '123456'
      }
   }
}
```

**依赖包类型**
- jar：只包含class文件和清单，不包含资源文件
- aar：包含所有文件

```
dependencies {
 //本地包依赖
 compile fileTree(include: ['*.jar'], dir: 'libs')
 //测试包依赖
 testCompile 'junit:junit:4.12'
 //远程包依赖
 compile 'com.android.support:appcompat-v7:23.4.0'
 //module依赖
 compile project(':lib-test')
 //aar依赖
 compile(name:'libraryname', ext:'aar')
}
```


**构建不同的上线包**
```
android{
productFlavors{
  channel1{
      //todo 具体属性配置
      applicationId cc.demo.channel1
      println(" - 生成channel1安装包 - ")
  }

  channel2{
      //todo 具体属性配置
      applicationId cc.demo.channel2
      println(" - 生成channel2安装包 - ")
  }
 }
}
```
- productFlavors里面的不同上线包默认是与defaultConfig 使用共同属性，若是需要特点重写即可
- 这种方式生成的渠道包一共有4种：channel1的debug和release，channel2的debug和release包


**apk各种签名及原理**
1. 使用Gradle实现，采用productFlavors条件编译没有兼容问题，方便集成，但是每次重新打包，速度比较慢
2. 采用写文件方法，apk就是一个签名的zip包，zip注释包涵Comment Length 和 File Comment两个字段，前者注释内容长度，后者是注释内容，实现方式是将渠道信息放在后者，速度非常快，但是不支持productFlavors条件编译，需要自定义.[工具1](https://github.com/mcxiaoke/packer-ng-plugin) [工具2](https://github.com/seven456/MultiChannelPackageTool)    
