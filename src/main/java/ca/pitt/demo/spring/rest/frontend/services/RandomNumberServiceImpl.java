package ca.pitt.demo.spring.rest.frontend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ca.pitt.demo.spring.rest.frontend.values.RandomValue;

/**
 * RandomNumberServiceImpl.
 * 
 * @author Andrew Pitt
 * @since 1.0.0
 */
@Service
public class RandomNumberServiceImpl implements RandomNumberService {

	/** RestTemplate. */
	@Autowired
	private RestTemplate restTemplate;

	/** Service url. */
	@Value("${service.random.url}")
	private String serviceUrl = "";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RandomValue getRandomValue() {
		ResponseEntity<RandomValue> response = restTemplate.getForEntity(serviceUrl + "/rand", RandomValue.class);
		return response.getBody();
	}

}
