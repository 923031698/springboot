package com.bjpowernode.springboot.config4jta;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // == xml
@MapperScan(basePackages = {"com.bjpowernode.springboot.mapper.orders"}, sqlSessionFactoryRef = "orderdbSqlSessionFactory")
public class OrderDBDataSource4jtaConfig {

    @Value("${spring.datasource.orderdb.username}")
    private String username;

    @Value("${spring.datasource.orderdb.password}")
    private String password;

    @Value("${spring.datasource.orderdb.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.orderdb.jdbcUrl}")
    private String jdbcUrl;

    /**
     * 配置一个数据源的bean
     *
     * @return
     */
    @Bean(name = "orderdbDataSource")
    public DataSource orderdbDataSource() {
        //创建一个XA数据源
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(jdbcUrl);
        xaDataSource.setUser(username);
        xaDataSource.setPassword(password);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("orderdbDataSource");
        atomikosDataSourceBean.setMaxPoolSize(30);
        atomikosDataSourceBean.setMinPoolSize(5);
        return atomikosDataSourceBean;
    }

    @Bean(name = "orderdbSqlSessionFactory")
    public SqlSessionFactory orderdbSqlSessionFactory(@Qualifier("orderdbDataSource") DataSource orderdbDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(orderdbDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "orderdbSqlSessionTemplate")
    public SqlSessionTemplate orderdbSqlSessionTemplate(@Qualifier("orderdbSqlSessionFactory") SqlSessionFactory orderdbSqlSessionFactory) {
        return new SqlSessionTemplate(orderdbSqlSessionFactory);
    }
}