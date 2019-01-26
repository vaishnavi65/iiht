package com.programcreek.helloworld.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.programcreek.helloworld.repository")
public class DataConfig {

	private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
	private final String PROPERTY_DIALECT = "hibernate.dialect";


	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(dataSource());
		lfb.setPersistenceProviderClass(HibernatePersistence.class);
		lfb.setPackagesToScan("com.programcreek.helloworld.model");
		lfb.setJpaProperties(hibernateProps());
		return lfb;
	}

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("SYSTEM");
		ds.setPassword("admin");
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		return ds;
	}

	Properties hibernateProps() {
		Properties properties = new Properties();
		properties.setProperty(PROPERTY_DIALECT, "org.hibernate.dialect.Oracle10gDialect");
		properties.setProperty(PROPERTY_SHOW_SQL,"true");
		return properties;
	}

	@Bean
	JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}

