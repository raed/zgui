package de.xenocraft.zgui.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
	
	@Bean
	@Profile("default")
	public DataSource dataSourceLocal() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://xenocraft.de/uniproject?useSSL=true");
		dataSource.setUsername("uniproject");
		dataSource.setPassword("20Uniproject18");
		return dataSource;
	}
}
