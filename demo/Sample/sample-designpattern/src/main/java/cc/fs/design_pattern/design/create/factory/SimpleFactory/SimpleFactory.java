package cc.fs.design_pattern.design.create.factory.SimpleFactory;

/**
 * 简单工厂方法
 * 介绍：公开一个static方法，构造产品
 * 特点：只有一个工厂，多个产品，1对多
 * 缺点：
 * 1.不利于拓展，破坏开闭原则
 */
public class SimpleFactory {
    //工厂根据传入参数构造产品
    public static Drink create(String name) {
        if (name.equals("Cole")) {
            return new Cole();
        } else if (name.equals("Tea")) {
            return new Tea();
        } else {
            return null;
        }
    }
}
