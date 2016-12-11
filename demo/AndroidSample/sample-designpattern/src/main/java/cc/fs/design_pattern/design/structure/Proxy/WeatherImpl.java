package cc.fs.design_pattern.design.structure.Proxy;

public class WeatherImpl implements Weater {
    @Override
    public void getWeater() {
        System.out.println("获取Weather信息");
    }

    @Override
    public void showWeater() {
        System.out.println("显示Weather信息");
    }
}
