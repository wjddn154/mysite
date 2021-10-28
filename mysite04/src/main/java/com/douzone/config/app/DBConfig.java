package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8");
		dataSource.setUsername("webdb");
		dataSource.setPassword("webdb");
		dataSource.setInitialSize(100);	//connection pool에 connection을 몇개 줄 것인가
		dataSource.setMaxActive(200);	//최대 connection?
		
		return dataSource;
	}
	//AppConfig.java에서 땡겨서 작업함
	
	//XML 버전 동일 작업
//	<!-- Connection Pool DataSource-->
//	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
//		<property name="driverClassName" value="org.mariadb.jdbc.Driver" />
//		<property name="url" value="jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8" />
//		<property name="username" value="webdb" />
//		<property name="password" value="webdb" />
//	</bean>
	
	
}
