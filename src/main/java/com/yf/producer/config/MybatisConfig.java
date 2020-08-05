package com.yf.producer.config;

import com.yf.producer.interceptor.MybatisInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yf
 * @date: 2020/04/27  16:44
 * @desc:
 */
@Configuration
public class MybatisConfig {

    @Bean
    public Interceptor getInterceptor(){
        return new MybatisInterceptor();
    }



}
