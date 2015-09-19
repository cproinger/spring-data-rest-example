package example.chinook.rest;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import example.Application;

@Configuration
@Import(Application.class)
public class RestConfig {

	@Bean
	@ConditionalOnClass(value = DataSource.class)
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:sql/create-tables.sql")
			.addScript("classpath:sql/insert-data.sql")
			.build();
	}
}
