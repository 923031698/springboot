package com.bjpowernode.springboot.configreadwrite;

import com.alibaba.druid.pool.DruidDataSource;
import com.bjpowernode.springboot.common.enums.SourceNameEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 作者
 * @create: 2020-12-01 11:37
 */
@Configuration
public class DruidConfiguration {

    /**
     * 动态数据源配置**********************************↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     ***************************/

    @Bean(name = "write", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return druidDataSource();
    }

    @Bean(name = "read", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave() {
        return druidDataSource();
    }

    @Bean("dataSource")
    @Primary//自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
    public DataSource autoChooseDataSource() {
        AutoChooseDataSource autoChooseDataSource = new AutoChooseDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(SourceNameEnum.write.value(), master());
        dataSourceMap.put(SourceNameEnum.read.value(), slave());
        // 将 read 数据源作为默认指定的数据源
        autoChooseDataSource.setDefaultTargetDataSource(slave());
        // 将 read 和 write 数据源作为指定的数据源
        autoChooseDataSource.setTargetDataSources(dataSourceMap);
        return autoChooseDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(autoChooseDataSource());
    }

    /**
     * 动态数据源配置**********************************↑↑↑↑↑↑↑↑↑↑↑↑↑↑
     ***************************/

    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}