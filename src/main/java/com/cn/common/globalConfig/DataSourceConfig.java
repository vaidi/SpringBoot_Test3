package com.cn.common.globalConfig;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Auther: YUANEL
 * @Date: 2018/11/16 16:10
 * @Description:
 */
//@Configuration
//public class DataSourceConfig {
//
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.one")
//    public DataSource dataSourceOne(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//}
