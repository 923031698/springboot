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
//@MapperScan(basePackages = {"com.bjpowernode.springboot.mapper.goods"}, sqlSessionFactoryRef = "goodsdbSqlSessionFactory")
public class GoodsDBDataSourceConfig {

    /**
     * 配置一个数据源的bean
     *
     * @return
     */
    @Bean(name="goodsdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.goodsdb")
    public DataSource goodsdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="goodsdbSqlSessionFactory")
    public SqlSessionFactory goodsdbSqlSessionFactory(@Qualifier("goodsdbDataSource") DataSource goodsdbDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(goodsdbDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="goodsdbSqlSessionTemplate")
    public SqlSessionTemplate goodsdbSqlSessionTemplate(@Qualifier("goodsdbSqlSessionFactory") SqlSessionFactory goodsdbSqlSessionFactory) {
        return new SqlSessionTemplate(goodsdbSqlSessionFactory);
    }

    /**
     * 如果需要对此数据源进行事务管理，需要配置该事务管理器
     *
     * @param goodsdbDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager goodsdbDataSourceTransactionManager(@Qualifier("goodsdbDataSource") DataSource goodsdbDataSource) {
        return new DataSourceTransactionManager(goodsdbDataSource);
    }
}