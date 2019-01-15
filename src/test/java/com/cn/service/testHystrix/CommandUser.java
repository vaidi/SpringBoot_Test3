//package com.cn.service.testHystrix;
//
//import com.netflix.hystrix.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Auther: YUANEL
// * @Date: 2018/11/20 19:11
// * @Description:
// */
//public class CommandUser extends HystrixCommand<String> {
//
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(CommandUser.class);
//
//    private String userName;
//
//    public CommandUser(String userName) {
//        super(Setter.withGroupKey(
//                //服务分组
//                HystrixCommandGroupKey.Factory.asKey("UserGroup"))
//                //线程分组
//                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))
//                //线程池配置
//                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
//                        .withCoreSize(10)
//                        .withKeepAliveTimeMinutes(5)
//                        .withMaxQueueSize(10)
//                        .withQueueSizeRejectionThreshold(10000))
//                //线程池隔离
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter()
//                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
//        );
//        this.userName = userName;
//    }
//
//
//    @Override
//    public String run() throws Exception {
//
//        System.out.println("当前线程名字："+Thread.currentThread().getName()+",userName:"+userName);
//
//        TimeUnit.MILLISECONDS.sleep(100);
//        return "userName=" + userName;
//    }
//
//
//
//
//
//
//
//}
