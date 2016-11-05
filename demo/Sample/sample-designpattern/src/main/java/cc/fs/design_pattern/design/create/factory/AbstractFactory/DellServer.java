package cc.fs.design_pattern.design.create.factory.AbstractFactory;

public class DellServer implements Server {
    @Override
    public void makeServer() {
        System.out.println(" 使用 Dell 服务 ");
    }
}
