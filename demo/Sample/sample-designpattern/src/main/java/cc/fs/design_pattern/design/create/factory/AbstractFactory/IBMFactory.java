package cc.fs.design_pattern.design.create.factory.AbstractFactory;

public class IBMFactory implements AbstractFactory {

    @Override
    public PC makePC() {
        return new IBMPC();
    }

    @Override
    public Server makeServer() {
        return new IBMServer();
    }
}
