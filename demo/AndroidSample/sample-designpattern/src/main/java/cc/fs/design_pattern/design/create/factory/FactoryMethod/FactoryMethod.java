package cc.fs.design_pattern.design.create.factory.FactoryMethod;

import cc.fs.design_pattern.design.create.factory.SimpleFactory.Drink;

/**
 * 工厂方法
 * 介绍：工厂有一个共同接口，不同工厂只生产同一等级的一种产品，相比于普通工厂，解耦不用判断
 * 特点：多个工厂，多个产品，多对多
 */
interface FactoryMethod {
    Drink crate();
}
