package com.cn.seckill.acitvemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 * @Auther: YUANEL
 * @Date: 2019/1/8 16:57
 * @Description:
 */
@Component
public class ActiveMQSender {


   // @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /*
	 * 发送消息，destination是发送到的队列，message是待发送的消息
	 */
    public void sendChannelMess(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }
}
