package com.bjpowernode.springboot.configreadwrite;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @description: 数据库读写分离配置MybatisPlus
 * @author: xb
 * @create: 2020-12-01 11:34
 */
@SuppressWarnings("ConstantConditions")
@org.springframework.context.annotation.Configuration
@ConditionalOnClass({SqlSessionFactory.class, MybatisSqlSessionFactoryBean.class})
@ConditionalOnBean(DataSource.class)//容器中有DataSource类就可以调用该配置类的方法了
@EnableConfigurationProperties(MybatisPlusProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisPlusAutoConfiguration {

    /*
    将自己配置的动态数据源放入容器中，容器会自动注入到该方法的入参。
    由于容器中有多个DataSource类，所以要将自己的动态数据源设置为默认@Primary
    */
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        return factory.getObject();
    }
}