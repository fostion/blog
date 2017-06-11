//
//打印log的cpp
//方法路径 Java_包名_方法名
//方法如何填写
//
#include <jni.h>
#include <string>
#include "include/android_log_print.h"

extern "C" {
JNIEXPORT jstring JNICALL
Java_cn_demo_so_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    //打印log
    LOGE("hello_jni");
    return env->NewStringUTF(hello.c_str());
}

//加密算法
JNIEXPORT jstring JNICALL
Java_cn_demo_so_MainActivity_generateKey(
        JNIEnv *env,
        jobject /* this */, jstring content) {
    //将java 字符串转换成c可操作的字符串
    const char *pName = (*env).GetStringUTFChars(content, NULL);
    //todo 进行加密操作

    LOGE("%sg",pName);
    return env->NewStringUTF(pName);
}

}



