package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		assert context != null;
	}

//	@Bean
//	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
//		return new OpenEntityManagerInViewFilter();
//	}

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
