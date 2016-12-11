package cc.fs.design_pattern.design.create.builder;

/**
 * Builder 建造者模式
 * 介绍：将创建对象各种参数收集管理，然后创建，有利于建造时修改参数
 */
class Car {
    private String name;
    private String price;

    private Car(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public void call(){
        System.out.print("我是宝马，价格"+price);
    }

    static class Builder {
        private String name;
        private String price;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Car create() {
            return new Car(name, price);
        }
    }

}
