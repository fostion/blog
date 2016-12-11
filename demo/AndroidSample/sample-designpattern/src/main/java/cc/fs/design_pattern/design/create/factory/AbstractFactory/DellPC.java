package cc.fs.design_pattern.design.create.factory.AbstractFactory;

public class DellPC implements PC {
    @Override
    public void makePC() {
        System.out.println("生产dell pc");
    }
}
