package cc.fs.design_pattern.design.create.factory.AbstractFactory;

public class IBMServer implements Server {
    @Override
    public void makeServer() {
        System.out.println(" 使用 IBM 服务 ");
    }
}
