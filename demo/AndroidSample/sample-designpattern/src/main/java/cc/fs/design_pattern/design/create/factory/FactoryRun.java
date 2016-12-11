package cc.fs.design_pattern.design.create.factory;

import cc.fs.design_pattern.design.create.factory.AbstractFactory.DellFactory;
import cc.fs.design_pattern.design.create.factory.AbstractFactory.IBMFactory;
import cc.fs.design_pattern.design.create.factory.FactoryMethod.ColeFactory;
import cc.fs.design_pattern.design.create.factory.FactoryMethod.TeaFactory;
import cc.fs.design_pattern.design.create.factory.SimpleFactory.Cole;
import cc.fs.design_pattern.design.create.factory.SimpleFactory.SimpleFactory;
import cc.fs.design_pattern.design.create.factory.SimpleFactory.Tea;

/**
 * 运行demo代码
 */
public class FactoryRun {

    //运行简单工厂
    public void runSimpleFactory() {
        Cole cole = (Cole) SimpleFactory.create("Cole");
        Tea tea = (Tea) SimpleFactory.create("Tea");

        if (cole != null) cole.call();
        if (tea != null) tea.call();
    }

    //运行工厂方法
    public void runFactoryMethod() {
        Cole cole = (Cole) (new ColeFactory()).crate();
        Tea tea = (Tea) (new TeaFactory()).crate();

        cole.call();
        tea.call();
    }

    //运行抽象工厂
    public void runAbstractFactory(){
        DellFactory dellFactory = new DellFactory();
        IBMFactory ibmFactory = new IBMFactory();

        dellFactory.makePC().makePC();
        dellFactory.makeServer().makeServer();

        ibmFactory.makePC().makePC();
        ibmFactory.makeServer().makeServer();
    }

}
