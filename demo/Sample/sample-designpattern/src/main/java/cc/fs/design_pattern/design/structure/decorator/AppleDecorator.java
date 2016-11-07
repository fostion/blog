package cc.fs.design_pattern.design.structure.decorator;

public class AppleDecorator implements Fruit {

    Apple apple;

    public AppleDecorator(Apple apple){
        this.apple = apple;
    }

    @Override
    public void make() {
        System.out.println("制作前的加工");
        apple.make();
        System.out.println("制作后的加工");
    }
}
