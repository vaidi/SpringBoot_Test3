//package com.cn.service.testHystrix;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
///**
// * @Auther: YUANEL
// * @Date: 2018/11/20 19:15
// * @Description:
// */
//public class TestHystrixCommand {
//    private final static Logger LOGGER = LoggerFactory.getLogger(TestHystrixCommand.class);
//
//    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//        CommandOrder commandPhone = new CommandOrder("手机");
//        CommandOrder command = new CommandOrder("电视");
//        //阻塞方式执行
//        String execute = commandPhone.execute();
//        LOGGER.info("execute=[{}]", execute);
//        System.out.println("execute=[{}]"+ execute);
//        //异步非阻塞方式
//        Future<String> queue = command.queue();
//        String value = queue.get(1000, TimeUnit.MILLISECONDS);
//        LOGGER.info("value=[{}]", value);
//        System.out.println("value=[{}]"+ value+"val:"+queue.get());
//        CommandUser commandUser = new CommandUser("张三");
//        String name = commandUser.execute();
//        LOGGER.info("name=[{}]", name);
//        System.out.println("name=[{}]"+ name);
//        System.out.println("main  end!!!");
//    }
//
//
//
//
//
//
//
//}
