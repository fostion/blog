## Android内存检测

### Logcat检测
```
dalvikvm: GC_CONCURRENT freed 1915K, 23% free 10567K/13660K, paused 3ms+5ms, total 76ms
```
产生GC的原因:
1. GC_CONCURRENT： 当堆内存快用完的时候，出发GC回收
2. GC_FOR_MALLOC：堆内存已经满了，同时又尝试分配新的内存，所以系统要回收内存
3. GC_EXTERNAL_ALLOC：在Android3.0以前，释放通过外部内存(2.3以前，产生Bitmap对象存储在Native Memory)3.0以后不再有这种内存分配
4. GC_EXPLICIT：调用System.gc时产生


### 使用工具：
#### [1. MAT](http://eclipse.org/mat/downloads.php )
**基础概念**
1. `Shallow heap`: 对象本身占有的内存大小，不包含其他引用对象
  - 常规对象(非数组)的ShallowSize由其成员变量的数量和类型决定
  - 数组由数组元素类型(对象类型/基本类型)和数组长度决定

2. `Retained Heap`：它表示如果一个对象被释放掉，那会因为该对象的释放而减少引用进而被释放的所有的对象（包括被递归释放的）所占用的heap大小。于是，如果一个对象的某个成员new了一大块int数组，那这个int数组也可以计算到这个对象中。相对于shallow heap，Retained heap可以更精确的反映一个对象实际占用的大小（因为如果该对象释放，retained heap都可以被释放）。

**常用Action**
1. Histogram：计算出该类所有对象的情况
2. DominatorTree：对象之间关系，若是GC Root到达
3. Path To GC Roots：查找引用，通常用来识别出对象在哪里被引用导致无法释放(exclude all phantom/weak/soft etc.references 排除弱引用,软引用,虚引用)
4. OQL:使用类似SQL语句查询固定类,检测是否出现泄漏
`select * from instanceof cn.demo.MainActivity`

**读取文件**
1. 使用hprof后缀文件，在android studio可以生成，但是需要转换
2. LeakCanary生成的hprof文件不是MAT使用需要使用SDK中的 `hprof-conv src.hprof new.hprof` 转换一下格式

### [2. LeakCanary](https://github.com/square/leakcanary)
内存泄漏检测工具,具体使用可以看详情wiki

`总结`:根据这两款工具的使用情况来看,LeakCanary只能分析应用退到后台界面出现GC才会检测内存泄漏问题,对于static , 单例对象的情况无法分析,MAT工具可以分析类,对象级别,功能比较强大,但是需要一定的学习需要,结合使用时最好.


内存泄漏情况:
1. 对于生命周期比较长,最好使用ApplicationContext,一定要使用Activity,需要弱引用/软引用
2. 非static对象创建的时候会持有外部对象,这个时候如果是单例设置监听,就会造成内存泄漏,因此最好在结束的时候设置为null. 例如: 外部ManagerA持有Activity context,而单例ManagerB在ManagerA设置监听listenerB,listenerB创建的时候持有类ManagerA的实例,这样导致Activity在销毁的时候存在被引用无法销毁,导致内存泄漏.




参考工具：
[MAT入门](http://www.jianshu.com/p/d8e247b1e7b2)
[MAT WIKI](http://wiki.eclipse.org/MemoryAnalyzer#Getting_Started)
[LeakCanary源码分析](https://www.liaohuqiu.net/cn/posts/leak-canary-read-me/)
[AndroidStudio检测](http://www.jianshu.com/p/216b03c22bb8)
[内存泄漏检测](http://blog.csdn.net/xiaanming/article/details/42396507)
[高效地分析Android内存--MAT工具解析](http://blog.csdn.net/caroline_wendy/article/details/50619746)
[MAT使用进阶](http://www.lightskystreet.com/2015/09/01/mat_usage/)
