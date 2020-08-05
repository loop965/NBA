package com.yf.producer.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author: yf
 * @date: 2020/08/05  11:38
 * @desc:
 */
@Configuration
@MapperScan(basePackages = "com.yf.producer.dao.modoo", sqlSessionTemplateRef  = "modooSqlSessionTemplate")
public class ModooDataSourceConfig {

    @Bean(name = "modooDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.modoo")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "modooSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("modooDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/modoo/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "modooTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("modooDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "modooSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("modooSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
