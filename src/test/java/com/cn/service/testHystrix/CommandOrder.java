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
// * @Date: 2018/11/20 19:07
// * @Description:
// */
//public class CommandOrder  extends HystrixCommand<String> {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(CommandOrder.class);
//
//    private String orderName;
//
//    public CommandOrder(String orderName) {
//        super(Setter.withGroupKey(
//                //服务分组
//                HystrixCommandGroupKey.Factory.asKey("OrderGroup"))
//                //线程分组
//                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("OrderPool"))
//                //线程池配置
//                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
//                        .withCoreSize(10)
//                        .withKeepAliveTimeMinutes(5)
//                        .withMaxQueueSize(10)
//                        .withQueueSizeRejectionThreshold(10000))
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter()
//                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
//        );
//        this.orderName = orderName;
//    }
//
//    @Override
//    protected String run() throws Exception {
//        System.out.println("当前线程名字："+Thread.currentThread().getName()+",orderName:"+orderName);
//        TimeUnit.MILLISECONDS.sleep(100);
//        return "OrderName=" + orderName;
//    }
//}
