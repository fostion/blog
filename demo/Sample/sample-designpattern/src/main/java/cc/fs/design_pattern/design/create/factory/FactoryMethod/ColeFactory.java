package cc.fs.design_pattern.design.create.factory.FactoryMethod;

import cc.fs.design_pattern.design.create.factory.SimpleFactory.Cole;
import cc.fs.design_pattern.design.create.factory.SimpleFactory.Drink;

public class ColeFactory implements FactoryMethod {
    @Override
    public Drink crate() {
        return new Cole();
    }
}
