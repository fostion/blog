package cc.fs.design_pattern.design.create.builder;

public class BuilderRun {
    public void run() {
        Car car = new Car.Builder()
                .setName("宝马")
                .setPrice("50万")
                .create();

        car.call();
    }
}
