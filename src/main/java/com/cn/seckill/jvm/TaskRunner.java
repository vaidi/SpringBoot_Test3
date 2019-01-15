package com.cn.seckill.jvm;

import com.cn.seckill.model.SuccessKilled;
import com.cn.seckill.service.ISeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/8 15:05
 * @Description:
 */
@Component
public class TaskRunner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRunner.class);

    @Autowired
    private ISeckillService seckillService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        while (true){
            //会阻塞
            SuccessKilled killed = SeckillQueue.getSeckillMail().consume();
            LOGGER.info("开始消费："+(killed !=null?killed.toString():killed));
            if(killed != null){
                seckillService.startSeckil(killed.getSeckillId(),killed.getUserId());
            }
        }
    }
}
