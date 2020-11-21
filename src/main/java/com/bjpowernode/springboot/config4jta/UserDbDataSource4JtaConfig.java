package com.bjpowernode.springboot.config4jta;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.bjpowernode.springboot.config.BaseMetaObjectHandler;
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

/**
 * @Author: xb
 * @Description: 该数据源集成mybatis-plus (注意 mybatis-plus和mybatis不能同时存在 jar只能2选其一)
 * @Date: 2020/9/14 12:08
 */
@Configuration
@MapperScan(basePackages = {"com.bjpowernode.springboot.mapper.users"}, sqlSessionFactoryRef = "userdbSqlSessionFactory")
public class UserDbDataSource4JtaConfig {

    @Autowired
    MybatisPlusInterceptor mybatisPlusInterceptor;

    @Value("${spring.datasource.userdb.username}")
    private String username;

    @Value("${spring.datasource.userdb.password}")
    private String password;

    @Value("${spring.datasource.userdb.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.userdb.jdbcUrl}")
    private String jdbcUrl;

    /**
     * 配置一个数据源的bean
     */
    @Bean(name = "userdbDataSource")
    public DataSource userdbDataSource() {
        //创建一个XA数据源
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(jdbcUrl);
        xaDataSource.setUser(username);
        xaDataSource.setPassword(password);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("userdbDataSource");
        atomikosDataSourceBean.setMaxPoolSize(30);
        atomikosDataSourceBean.setMinPoolSize(5);
        return atomikosDataSourceBean;
    }

    @Bean(name = "userdbSqlSessionFactory")
    public SqlSessionFactory userdbSqlSessionFactory(@Qualifier("userdbDataSource") DataSource userdbDataSource) throws Exception {
        //  如果使用mybatis-plus  就换成下面这个  切记如果不替换的话 可能找到欲哭无泪
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //  如果使用mybatis 就换成下面这个
        // SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(userdbDataSource);

        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig());
        //定义枚举转化(扫描该包下的枚举映射到数据库中)
        mybatisSqlSessionFactoryBean.setTypeEnumsPackage("com.bjpowernode.springboot.common.enums");

        //MyBaits 别名包扫描路径，通过该属性可以给包中的类注册别名，注册后在 Mapper 对应的 XML 文件中可以直接使用类名，
        // 而不用使用全限定的类名(即 XML 中调用的时候不用包含包名)
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage("com.bjpowernode.springboot.model.domian.*");

        //  mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration());
        Interceptor[] plugins = {mybatisPlusInterceptor};
        mybatisSqlSessionFactoryBean.setPlugins(plugins);
        return mybatisSqlSessionFactoryBean.getObject();
    }

    @Bean(name = "userdbSqlSessionTemplate")
    public SqlSessionTemplate userdbSqlSessionTemplate(@Qualifier("userdbSqlSessionFactory") SqlSessionFactory userdbSqlSessionFactory) {
        return new SqlSessionTemplate(userdbSqlSessionFactory);
    }


    /**
     * 配置类
     */
    //
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();

        return mybatisConfiguration;
    }


    /**
     * 全局配置
     */
    //  多数据时这样配置  mybatis-plus   全局配置在配置文件中只试用于单数据源（单节点）
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // dbConfig.setTablePrefix("tbl_");  // 关键
        dbConfig.setInsertStrategy((FieldStrategy.NOT_EMPTY));
        globalConfig.setDbConfig(dbConfig);
        globalConfig.setMetaObjectHandler(new BaseMetaObjectHandler());
        return globalConfig;
    }

}