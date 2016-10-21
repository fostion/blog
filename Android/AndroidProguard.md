#### Android Proguard混淆
[网址](https://developer.android.com/studio/build/shrink-code.html)
[微信团队资源混淆](http://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=208135658&idx=1&sn=ac9bd6b4927e9e82f9fa14e396183a8f#rd)

- 混淆作用：
> 1. 避免安全加固，避免被反编译时暴露代码
  2. 删除无用代码，方法和资源，减小apk大小

- 注意：
> 混淆必须在项目初期开始，否则一旦项目拥有大量代码，此刻混淆则会出现异常代码，同时混淆不是万能的，该破解还是会破解抓http包，因此根据实际项目考虑，若是使用则必须保存`mapping.txt`文件，对照着混淆源代码才能查询bug，否则无法修复bug因为混淆无法知道真实位置

- 混淆原则：
> 1. jni方法不可混淆
  2. 反射用到的类不混淆（否则反射可能有问题）
  3. AndroidManifest中的类不混淆，四大组件、Application的子类、Framework层所有的类默认不进行混淆
  4. Parcelables的子类和create静态成员变量不混淆，否则会产生android.os.BadParcelableException异常
  5. 使用Gson、fastjson等框架时，所写的json对象类不混淆，否则无法将json解析成对应的对象
  6. 使用第三方开源库或者引用其他第三方sdk包时，需要在混淆中加入对应的混淆规则
  7. 使用webview调用JS也要保证接口不混淆

- gradle脚本使用

```
release {
    shrinkResources true
    minifyEnabled true
    proguardFiles getDefaultProguardFile(‘proguard-android.txt'),
            'proguard-rules.pro'
}
```
这里`proguard-android.txt`是默认的Proguard设置，位置在AndroidSDK`tools/proguard`文件夹中，而`proguard-rules.pro`则是自定义的混淆规则，删除无用代码后，shrinkResources删除资源，但是不会删除values下的资源（strings,dimensions,styles,colors）

- 输出文件 使用混淆文件编译后 <module-name>/build/outputs/mapping/release/
> 1. `dump.txt:apk`:内所有类描述
  2. `mapping.txt`:原文件和混淆后的方法属性
  3. `seeds.txt`:没有被混淆的类和其成员
  4. `usage.txt`:删除的无用代码列表

- 规则：
> 1. libraryjars class_path 应用依赖包，如android-support-v4
  2. -keep [,modifier,...] class_specification 不混淆某些类
  3. -keepclassmembers [,modifier,..] class_specification 不混淆类成员
  4. -keepclasseswithmembers [,modifier,...] class_specification 不混淆类及成员
  5. -keepnames class_specification 不混淆类名
  6. -keepclassmembernames class_specification 不混淆类的成员名
  7. -keepclasseswithmembernames 不混淆类机器成员名
  8. -assumenosideeffects class_specification 驾驶调用不产生任何影响，在proguard代码优化时调用remove,如system.out.print和log.v
  9. -dontwarn [class_filter] 不提示warnning


- proguard-rules.pro脚本配置(例)

```

-keepakeepattributes *Annotation* //保护注解不被混淆

-keepclasseswithmembernames class * {//指定不混淆所有的JNI方法  
  native <methods>;  
}  


-keepclassmembers public class * extends android.view.View {//所有View的子类及其子类的get、set方法都不进行混淆  
   void set*(***);  
   *** get*();  
}  


-keepclassmembers class * extends android.app.Activity {//不混淆Activity中参数类型为View的所有方法  
 public void *(android.view.View);  
}  


-keepclassmembers enum * {//不混淆Enum类型的指定方法  
    public static **[] values();  
    public static ** valueOf(java.lang.String);  
}  


//不混淆R类里及其所有内部static类中的所有static变量字段  
  -keepclassmembers class **.R$* {  
    public static <fields>;  
}  


//不混淆Parcelable和它的子类，还有Creator成员变量  
-keep class * implements android.os.Parcelable {  
  public static final android.os.Parcelable$Creator *;  
}  


-dontwarn android.support.**//不提示兼容库的错误警告


//使用反射需要加入
-keepattributes Signature  
-keepattributes EnclosingMethod


//想让bbean对象不混淆 cn.bean
-keep class bean.**{
  *;
} //不混淆所有cn.bean包下的所有类和这些类的成员变量


//webview调用js接口
-keepclassmembernames class cn.webview.jsapi {
  public *;
}


//移除一些log代码
-assumenosideeffects class android.util.log{
  public static *** v(...);  
  public static *** i(...);  
  public static *** d(...);  
  public static *** w(...);  
  public static *** e(...);  
}


//gson例子
#gson
#-libraryjars libs/gson-2.2.2.jar
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }


# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt

```


- 需要保存资源

```gradle
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools"
    tools:keep="@layout/l_used*_c,@layout/l_used_a,@layout/l_used_b*"
    tools:discard="@layout/unused2" />

```
