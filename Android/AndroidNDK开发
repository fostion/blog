#### Android NDK 实践
- [官方下载地址](https://developer.android.com/ndk/downloads/index.html)
- [开源下载](https://github.com/inferjay/AndroidDevTools)


#### 须知理论

###### 1. 适配各种版本的cpu架构
```
- ARMv5
- ARMv7
- ARMv8
- ARM64-v8a
- x86
- x86_64
- MIPS
- MIPS64
```

一般所有的库都知识armeabi架构的so库，但不是100%,所以需要取舍，例如新的64位ARM64-v8a，所以选取一些cpu市场比较大的so库

###### 2. so存放位置
- AndroidStudio 工程放在jniLibs/ABI目录中
- Eclipse 工程放在lib/ABI 目录中
- AAR压缩包位于jni/ABI目录中
- APK在lib/ABI目录中

###### 3. 存放方式
 - 放在项目中
 - 动态下载

###### 4. 数据类型

|	 java类型       |	本地类型		    |
|  :--------      |    ----------: |
|    boolean    	|    jboolean 	 |
|     byte        |       jbye		 |
|     char        |       jchar		 |
|     short       |      jshort		 |   
|      int        |        jint		 |
|     long        |       jlong		 |
|     float       |       jfloat	 |
|     double      |      jdouble	 |
|     Object      |      jobject	 |
|     Class       |       jclass	 |
|    String       |      jstring	 |
|    Object[] 		|  jobjectArray	 |
|    boolean[]	  | jbooleanArray  |
|     byte[]      |   jbyteArray	 |
|     char[]      |   jcharArray	 |
|     short[]     |   jshortArray	 |
|      int[]      |   jintArray 	 |
|     long[]      |   jlongArray	 |
|     float[]     |   jfloatArray	 |
|     double[]    |  jdoubleArray	 |



#### 两种开发方式
- 使用Application.mk(非必要)、Android.mk文件开发(手工编译脚本)，脚本规则，gradle配置规则
- gradle-experimental插件开发，使用AndroidStudio内部mk文件，只需要配置ndk(简单,不灵活)

###### gradle插件开发
1. 系统配置（需要用到java所以java环境也要配置）

```
window :   classpath 添加ndk路径
```

2. 项目配置NDK路径
- AndroidStudio: Project Structure,切换到SDK Location，添加NDK路径

3. 配置build.gradle

```gradle
//gradle 版本
classpath 'com.android.tools.build:gradle-experimental:0.7.0'

defaultConfig {
  ndk{
     moduleName "hello-jni" //so名称
     abiFilters "armeabi","x86" //编译适配cpu类型
     cFlags.add('-std=99')  //编译器选项
      ldLibs.addAll(['android','log']) //链接库文件
 }
}
```
注意: local.properties配置正确：

```gradle
ndk.dir=D\:\\coding\\tool\\androidNDK
```

4. 添加native类和方法,jni文件夹
- AndroidStudio新建jni文件夹

5. 生成h头文件和c主文件

```java
//生成头文件 cc.fs.jni.JniTest.h
javah -d ../jni 类全路径
//例如 javah -d ../jni cc.fs.jni.JniTest
//注意:当前位置是在 app/src/main/java
```

6. 代码中加载so文件

```java
static{
    System.loadLibrary("so名字");
}
```

##### 待完善
1. Application.mk 和 Android.mk使用，及gradle脚本编写[资料](https://www.crystax.net/cn/blog/3) [资料2](http://gold.xitu.io/entry/57086e1fa34131004c0b13d5)
[AndroidStudio优雅使用NDK](http://blog.majiajie.me/2016/03/27/%E5%A6%82%E4%BD%95%E4%BC%98%E9%9B%85%E5%9C%B0%E4%BD%BF%E7%94%A8NDK/)
2. java 和 c++对应着的类型变换
