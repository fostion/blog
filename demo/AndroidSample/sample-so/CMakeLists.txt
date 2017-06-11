# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html 构建脚本介绍
# NDK API: https://developer.android.google.cn/ndk/guides/stable_apis.html?hl=zh-cn#a3

# 设置CMake最低版本，用于确定可使用方法
# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# 设置标头文件路径
# Specifies a path to native header files.
include_directories(src/main/cpp/include/)

# 设置编译库名字 模式 STATIC 或 SHARED 编译源码的路径，这里可以定义多个库  static和share区别
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-log

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-log.cpp )

# 查找需要添加的NDK提供的方法  ndk提供哪些方法？
# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.  库的名字
              log-lib

              # Specifies the name of the NDK library that  本地名字，其他定法调用，直接用这个变量
              # you want CMake to locate.
              log )

# 确保本地库可以使用ndk提供的库
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
#
target_link_libraries( # Specifies the target library.
                       native-log

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )