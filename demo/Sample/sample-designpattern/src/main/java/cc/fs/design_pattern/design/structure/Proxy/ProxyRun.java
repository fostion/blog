package cc.fs.design_pattern.design.structure.Proxy;

import java.lang.reflect.Proxy;

/**
 * 代理模式
 * 介绍：使用代理类，替原来对象进行一些操作，同时可以这个方法进行添加属性
 */
public class ProxyRun {

    //静态代理
    public void runCodeProxy() {
        ProxyWeather proxyWeather = new ProxyWeather();
        proxyWeather.getWeater();
        proxyWeather.showWeater();
    }

    //动态代理
    public void runDynamicProxy() {
        WeatherHandler handler = new WeatherHandler(new WeatherImpl());
        WeatherImpl proxy = (WeatherImpl) Proxy.newProxyInstance(
                WeatherImpl.class.getClassLoader(), WeatherImpl.class.getInterfaces(), handler);
        proxy.getWeater();
        proxy.showWeater();
    }

}
