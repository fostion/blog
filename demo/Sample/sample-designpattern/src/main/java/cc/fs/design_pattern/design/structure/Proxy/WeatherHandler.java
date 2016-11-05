package cc.fs.design_pattern.design.structure.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WeatherHandler implements InvocationHandler {

    private Object proxy;

    public WeatherHandler(Object proxy){
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object object = null;
        try{
            System.out.println("动态代理执行前做事情");
            object = method.invoke(proxy,args);
            System.out.println("动态代理执行后做事情");
        } catch (Exception e){
            System.out.println("代理执行失败");
        }

        return object;
    }
}
