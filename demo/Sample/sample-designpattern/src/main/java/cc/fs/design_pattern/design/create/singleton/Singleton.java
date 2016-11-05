package cc.fs.design_pattern.design.create.singleton;

/**
 * 单例
 * 介绍：整个类有且只有一个实例对象
 * 注意问题：
 * 1.防止多线程多次创建
 * 2.在不同的虚拟机中或进程中，单例将会失效
 */
public class Singleton {

    private static Singleton instance;

    //构造函数私有化
    private Singleton() {
    }

    //对外公开一个获取方法
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void call(){
        System.out.println("Singleton 实例构造");
    }

}
