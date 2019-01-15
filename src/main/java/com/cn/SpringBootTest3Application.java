package com.cn;

import com.cn.entity.User;
import com.cn.service.UserService;
import com.cn.service.impl.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableEurekaServer
@SpringBootApplication
//注解事物管理
@EnableTransactionManagement
@MapperScan("com.cn.Mapper")
public class SpringBootTest3Application {
//    @Autowired
//    private  UserService userService;
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootTest3Application.class, args);
    }


//    // 其中 dataSource 框架会自动为我们注入
//    @Bean
//    public PlatformTransactionManager txManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }


}
