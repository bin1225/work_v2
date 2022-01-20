package com.work.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan({"com.work.**.mapper"})
@EnableTransactionManagement
public class DbConfiguration {

	// @Value("${db.driverClassName}")
	// private String driverClassName;
	//
	// @Value("${db.url}")
	// private String url;
	//
	// @Value("${db.userName}")
	// private String username;
	//
	// @Value("${db.password}")
	// private String password;
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private String url ="jdbc:mysql://localhost:3306/work?useUnicode=true&characterEncoding=utf8";
	private String username = "root";
	private String password = "kkb001225";

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws
		Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);

		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		factoryBean.setConfiguration(configuration);

		factoryBean.setTypeAliasesPackage("com.**.dto");
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:com/**/mapper/*.xml"));
		return factoryBean.getObject();
	}
}



