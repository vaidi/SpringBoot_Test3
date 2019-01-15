package com.cn.common.aop;

import com.cn.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Auther: YUANEL
 * @Date: 2018/11/8 17:19
 * @Description:
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {


    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private ThreadLocal<User> threadLocalUser = new ThreadLocal<>();

    /**
     * 定义一个切入点.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */

    @Pointcut("execution(public * com.cn.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }
        User user = new User();
        user.setUserId("1");
        user.setMobile(Arrays.toString(joinPoint.getArgs()).toString());
        threadLocalUser.set(user);
        logger.info("请求url:" + request.getRequestURL().toString() + ",请求方式：" + request.getMethod() + ",请求ip:" + request.getRemoteAddr()
                + ",请求方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + ",请求参数：" + Arrays.toString(joinPoint.getArgs())
        );
    }


    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        User user = threadLocalUser.get();
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()，该方法结束！！！"+",访问的用户："+user.toString());
    }


}
