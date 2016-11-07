package cc.fs.design_pattern.design.create.factory.SimpleFactory;

/**
 * 茶
 */
public class Tea implements Drink {
    @Override
    public void call() {
        System.out.print("茶");
    }
}
