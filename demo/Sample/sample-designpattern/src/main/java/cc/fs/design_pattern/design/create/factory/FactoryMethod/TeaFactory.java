package cc.fs.design_pattern.design.create.factory.FactoryMethod;

import cc.fs.design_pattern.design.create.factory.SimpleFactory.Drink;
import cc.fs.design_pattern.design.create.factory.SimpleFactory.Tea;

public class TeaFactory implements FactoryMethod {
    @Override
    public Drink crate() {
        return new Tea();
    }
}
