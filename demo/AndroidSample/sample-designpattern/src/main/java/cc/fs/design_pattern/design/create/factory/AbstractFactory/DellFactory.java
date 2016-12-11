package cc.fs.design_pattern.design.create.factory.AbstractFactory;

public class DellFactory implements AbstractFactory{
    @Override
    public PC makePC() {
        return new DellPC();
    }

    @Override
    public Server makeServer() {
        return new DellServer();
    }
}
