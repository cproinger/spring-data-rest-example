package example;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.chinook.repository.GenreRepository;


@EnableJpaRepositories
@EnableTransactionManagement
@EnableAutoConfiguration
//@Import(value = EmbeddedServletContainerAutoConfiguration.class)
//@enable
public class Application extends RepositoryRestMvcConfiguration {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		assert context != null;
	}
	
//	@Bean
	public DataSource dataSource() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("./test");
		return ds;
	}
	
//	@Bean
	@ConditionalOnClass(value = DataSource.class)
	public DataSource h2DataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:sql/create-tables.sql")
			.addScript("classpath:sql/insert-data.sql")
			.build();
	}
	
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        try {
            config.setBaseUri(new URI("/"));
        } catch (URISyntaxException exception) {
            throw new RuntimeException("Cannot set base uri on REST configuration", exception);
        }
    }
}
