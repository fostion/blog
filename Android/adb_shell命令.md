## adb shell命令
```
//开启
adb start-server

//关闭
adb kill-server

//查看设备
adb devices

//连接设备
adb connect <ip>

//查看log
adb logcat

//将log输出到当前目录文件中
adb logcat >> log.txt

//重启机器
adb reboot

//安装apk
adb install <apkfile>/或者拖进来

//卸载apk
adb uninstall <package>

//cpu和内存占用情况
apk shell top

//查看内存占用前6 App
adb shell top -m 6

//查看各进程内存使用情况
adb shell procrank

//杀死进程
adb shell kill <pid>

//查看进程列表
adb shell ps

//将本地文件复制到设备
adb push <local> <remote>

//将设备文件复制到本地
adb pull <remote> <local>

//查看当前目录文件
adb shell ls

//进入文件夹
adb shell cd <folder>
```
