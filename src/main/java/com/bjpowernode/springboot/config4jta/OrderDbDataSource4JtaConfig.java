//package com.bjpowernode.springboot.config4jta;
//
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.mysql.cj.jdbc.MysqlXADataSource;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * @Author: xb
// * @Description: 该数据源集成mybatis-plus (注意 mybatis-plus和mybatis不能同时存在 jar只能2选其一)
// * @Date: 2020/9/14 12:08
// */
//@Configuration // == xml
//@MapperScan(basePackages = {"com.bjpowernode.springboot.mapper.orders"}, sqlSessionFactoryRef = "orderdbSqlSessionFactory")
//public class OrderDbDataSource4JtaConfig {
//
//    @Autowired
//    MybatisPlusInterceptor mybatisPlusInterceptor;
//
//    @Value("${spring.datasource.orderdb.username}")
//    private String username;
//
//    @Value("${spring.datasource.orderdb.password}")
//    private String password;
//
//    @Value("${spring.datasource.orderdb.driverClassName}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.orderdb.jdbcUrl}")
//    private String jdbcUrl;
//
//    /**
//     * 配置一个数据源的bean
//     *
//     * @return
//     */
//    @Bean(name = "orderdbDataSource")
//    public DataSource orderdbDataSource() {
//        //创建一个XA数据源
//        MysqlXADataSource xaDataSource = new MysqlXADataSource();
//        xaDataSource.setUrl(jdbcUrl);
//        xaDataSource.setUser(username);
//        xaDataSource.setPassword(password);
//
//        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
//        atomikosDataSourceBean.setXaDataSource(xaDataSource);
//        atomikosDataSourceBean.setUniqueResourceName("orderdbDataSource");
//        atomikosDataSourceBean.setMaxPoolSize(30);
//        atomikosDataSourceBean.setMinPoolSize(5);
//        return atomikosDataSourceBean;
//    }
//
//    @Bean(name = "orderdbSqlSessionFactory")
//    public SqlSessionFactory orderdbSqlSessionFactory(@Qualifier("orderdbDataSource") DataSource orderdbDataSource) throws Exception {
//        //  如果使用mybatis-plus  就换成下面这个  切记如果不替换的话 可能找到欲哭无泪
//        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        //  如果使用mybatis 就换成下面这个
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        mybatisSqlSessionFactoryBean.setDataSource(orderdbDataSource);
//        Interceptor[] plugins = {mybatisPlusInterceptor};
//        mybatisSqlSessionFactoryBean.setPlugins(plugins);
//        return mybatisSqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "orderdbSqlSessionTemplate")
//    public SqlSessionTemplate orderdbSqlSessionTemplate(@Qualifier("orderdbSqlSessionFactory") SqlSessionFactory orderdbSqlSessionFactory) {
//        return new SqlSessionTemplate(orderdbSqlSessionFactory);
//    }
//}