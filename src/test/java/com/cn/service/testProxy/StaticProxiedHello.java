package com.cn.service.testProxy;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/14 15:14
 * @Description:
 */
public class StaticProxiedHello implements Hello {

    private Hello hello = new HelloImpl();

    @Override
    public String sayHello(String sth) {
        return hello.sayHello(sth);
    }


    public static void main(String[] args) {
        Hello hello = new StaticProxiedHello();
        String sth = hello.sayHello("一个二傻子");
        System.out.println(sth);
    }


}
