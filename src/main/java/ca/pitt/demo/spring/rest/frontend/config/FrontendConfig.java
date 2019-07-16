package ca.pitt.demo.spring.rest.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Main configuration class.
 * 
 * @author Andrew Pitt
 * @since 1.0.0
 */
@Configuration
public class FrontendConfig {

	/**
	 * RestTemplate.
	 * 
	 * @return <code>RestTemplate</code>
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
