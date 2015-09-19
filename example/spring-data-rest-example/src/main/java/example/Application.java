package example;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@EnableAutoConfiguration
// @Import(value = EmbeddedServletContainerAutoConfiguration.class)
// @enable
@PropertySource(value = { "classpath:/config/db.properties" })
public class Application extends RepositoryRestMvcConfiguration {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		assert context != null;
	}

	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		try {
			config.setBaseUri(new URI("/"));
		} catch (URISyntaxException exception) {
			throw new RuntimeException("Cannot set base uri on REST configuration", exception);
		}
	}
	
	@Bean
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
		return new OpenEntityManagerInViewFilter();
	}
	
	//does not work. 
//	@Autowired
//	private EntityManagerFactory emf;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		super.addInterceptors(registry);
//		OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
//		interceptor.setEntityManagerFactory(emf);
//		registry.addInterceptor(new WebRequestHandlerInterceptorAdapter(interceptor));
//	}
	
	
}
