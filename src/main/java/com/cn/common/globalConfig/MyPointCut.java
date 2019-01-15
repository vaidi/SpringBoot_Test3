//package com.cn.common.globalConfig;
//
//import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
//import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
//import org.springframework.aop.support.JdkRegexpMethodPointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Auther: YUANEL
// * @Date: 2018/11/16 17:24
// * @Description:
// */
//@Configuration
//public class MyPointCut extends BeanTypeAutoProxyCreator {
//
//    @Autowired
//    private DruidStatInterceptor druidStatInterceptor;
//
//    @Bean("druidStatInterceptor")
//    public DruidStatInterceptor getDruidStatInterceptor(){
//        return  new DruidStatInterceptor();
//    }
//
//    public void initMenthod(){
//      this.setTargetBeanType("com.cn.controller");
//
//
//
//    }
//
//
//
//
//
//
//
//}
