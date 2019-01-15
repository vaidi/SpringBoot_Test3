package com.cn.service.testProxy;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

import static java.lang.Thread.sleep;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/14 16:17
 * @Description:
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("you can said:"+ Arrays.toString(objects));
        return methodProxy.invokeSuper(o,objects);
    }

    public static void main(String[] args) throws InterruptedException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new MyMethodInterceptor());
        HelloConcrete helloConcrete =(HelloConcrete) enhancer.create();
        System.out.println( helloConcrete.sayHello("hello word!!"));
        sleep(1000);
    }

    /**
     * 注意：对于从 Object 中继承的方法，CGLIB 代理也会进行代理，如hashCode()、equals()、toString() 等，
     * 但是 getClass()、wait() 等方法不会，因为它是 final 方法，CGLIB 无法代理。
     *
     * JDK 原生动态代理是 Java 原生支持的，不需要任何外部依赖，但是它只能基于接口进行代理；CGLIB 通过继承的方式进行代理，
     * 无论目标对象有没有实现接口都可以代理，但是无法处理 final 的情况。
     */

}
