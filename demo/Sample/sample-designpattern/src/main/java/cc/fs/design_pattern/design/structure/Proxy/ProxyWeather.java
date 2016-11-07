package cc.fs.design_pattern.design.structure.Proxy;

public class ProxyWeather implements Weater {

    private WeatherImpl weatherImpl;

    public ProxyWeather(){
        this.weatherImpl = new WeatherImpl();
    }

    @Override
    public void getWeater() {
        System.out.println("获取Weather信息前做事情");
        weatherImpl.getWeater();
        System.out.println("获取Weather信息后做事情");
    }

    @Override
    public void showWeater() {
        System.out.println("显示Weather信息前做事情");
        weatherImpl.showWeater();
        System.out.println("显示Weather信息后做事情");
    }
}
