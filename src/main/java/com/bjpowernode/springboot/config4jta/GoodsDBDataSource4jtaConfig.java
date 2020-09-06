package com.bjpowernode.springboot.config4jta;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // == xml
@MapperScan(basePackages = {"com.bjpowernode.springboot.mapper.goods"}, sqlSessionFactoryRef = "goodsdbSqlSessionFactory")
public class GoodsDBDataSource4jtaConfig {

    @Autowired
    MybatisPlusInterceptor mybatisPlusInterceptor;


    @Value("${spring.datasource.goodsdb.username}")
    private String username;

    @Value("${spring.datasource.goodsdb.password}")
    private String password;

    @Value("${spring.datasource.goodsdb.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.goodsdb.jdbcUrl}")
    private String jdbcUrl;

    /**
     * 配置一个数据源的bean
     *
     * @return
     */
    @Bean(name = "goodsdbDataSource")
    public DataSource goodsdbDataSource() {
        //创建一个XA数据源
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(jdbcUrl);
        xaDataSource.setUser(username);
        xaDataSource.setPassword(password);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("goodsdbDataSource");
        atomikosDataSourceBean.setMaxPoolSize(30);
        atomikosDataSourceBean.setMinPoolSize(5);
        return atomikosDataSourceBean;
    }

    @Bean(name = "goodsdbSqlSessionFactory")
    public SqlSessionFactory goodsdbSqlSessionFactory(@Qualifier("goodsdbDataSource") DataSource goodsdbDataSource) throws Exception {
        //  如果使用mybatis-plus  就换成下面这个  切记如果不替换的话 可能找到欲哭无泪
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //  如果使用mybatis 就换成下面这个
        //SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(goodsdbDataSource);
        Interceptor[] plugins = {mybatisPlusInterceptor};
        mybatisSqlSessionFactoryBean.setPlugins(plugins);
        return mybatisSqlSessionFactoryBean.getObject();
    }

    @Bean(name = "goodsdbSqlSessionTemplate")
    public SqlSessionTemplate goodsdbSqlSessionTemplate(@Qualifier("goodsdbSqlSessionFactory") SqlSessionFactory goodsdbSqlSessionFactory) {
        return new SqlSessionTemplate(goodsdbSqlSessionFactory);
    }
}