package cc.fs.design_pattern.design.structure.decorator;

/**
 * 饰器模式
 * 介绍：从对象外部添加新功能
 * 优点：使用组合方式而非继承方式来拓展和复用新功能
 */
public class DecoratorRun {

    public void run(){
        Apple apple = new Apple();
        AppleDecorator decorator = new AppleDecorator(apple);
        decorator.make();
    }

}
