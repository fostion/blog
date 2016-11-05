package cc.fs.design_pattern.design.create.factory.AbstractFactory;

/**
 * 抽象工厂
 * 介绍：用来生产不同产品族的全部产品
 * http://blog.csdn.net/superbeck/article/details/4446177
 * http://bbs.csdn.net/topics/340257167
 * 比较难理解
 */
interface AbstractFactory {
    PC makePC();
    Server makeServer();
}
