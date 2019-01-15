package com.cn.service.testProxy;

import java.lang.reflect.Proxy;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/14 15:12
 * @Description:
 */
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String sth) {
        return "hello impl:"+sth;
    }



}
