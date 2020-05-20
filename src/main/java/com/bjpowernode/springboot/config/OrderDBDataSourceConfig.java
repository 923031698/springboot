package com.bjpowernode.springboot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration // == xml
//@MapperScan(basePackages = {"com.bjpowernode.springboot.mapper.orders"}, sqlSessionFactoryRef = "orderdbSqlSessionFactory")
public class OrderDBDataSourceConfig {

    /**
     * 配置一个数据源的bean
     *
     * @return
     */
    @Bean(name="orderdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.orderdb")
    public DataSource orderdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="orderdbSqlSessionFactory")
    public SqlSessionFactory orderdbSqlSessionFactory(@Qualifier("orderdbDataSource") DataSource orderdbDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(orderdbDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="orderdbSqlSessionTemplate")
    public SqlSessionTemplate orderdbSqlSessionTemplate(@Qualifier("orderdbSqlSessionFactory") SqlSessionFactory orderdbSqlSessionFactory) {
        return new SqlSessionTemplate(orderdbSqlSessionFactory);
    }

    /**
     * 如果需要对此数据源进行事务管理，需要配置该事务管理器
     *
     * @param orderdbDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager orderdbDataSourceTransactionManager(@Qualifier("orderdbDataSource") DataSource orderdbDataSource) {
        return new DataSourceTransactionManager(orderdbDataSource);
    }
}