package com.cn.service.testProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/14 15:19
 * @Description: //java proxy
 *
 * java d的动态代理是基于接口的
 * CGLIB 通过继承方式实现代理
 * 1：首先实现一个LogInvocationHandler ，方法调用会自动转发到invoke方法上
 */
public class LogInvocationHandler implements InvocationHandler {

    private Hello hello;

    public LogInvocationHandler(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sayHello".equals(method.getName())) {
            System.out.println("来进入了动态代理"+ Arrays.toString(args));
        }
        return  method.invoke(hello, args);
    }
    public static void main(String[] args) {
        //然后在需要使用hello的时候，通过jdk动态代理使用hello的代理对象
        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), //1,类加载器
                new Class<?>[]{Hello.class}, //2，代理需求实现的接口，可以有多个
                new LogInvocationHandler(new HelloImpl()));//方法调用的实际处理者
        System.out.println(hello.sayHello("你是不是一个二傻子"));
        //1：代理对象是是在运行时产生的，而不是在编译的时候产生
        //2：对代理对象的所有接口方法调用都会转发到Involcation.invoke（）方法里，在invoke（）里我们可以加人任何逻辑
        //比如修改方法参数，加人日志，安全检查等辅助功能，之后我们通过某种真正的方法体，我们的demo是通过反射来实现
        //还可以通过RPC调用远程方法
        /**
         * 注意：对于从 Object 中继承的方法，JDK Proxy 会把 hashCode()、equals()、toString()
         * 这三个非接口方法转发给 InvocationHandler，其余的 Object 方法则不会转发，详见 JDK Proxy 官方文档。
         */

    }




}
