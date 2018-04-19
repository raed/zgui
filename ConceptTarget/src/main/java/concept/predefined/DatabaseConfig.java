package concept.predefined;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

	@Bean
	@Profile("default")
	public DataSource getDataSourceDefault() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://xenocraft.de/concept?useSSL=true");
		dataSource.setUsername("uniproject");
		dataSource.setPassword("OBBXezH5V4LWmSqc");
		return dataSource;
	}
	
	@Bean
	@Profile("local")
	public DataSource getDataSourceLocal() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/concept?useSSL=true");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
}
