package ca.pitt.demo.spring.rest.frontend.services;

import ca.pitt.demo.spring.rest.frontend.values.RandomValue;

/**
 * RandomNumberService.
 * 
 * @author Andrew Pitt
 * @since 1.0.0
 */
public interface RandomNumberService {

	/**
	 * Get a <code>RandomValue</code>
	 * 
	 * @return <code>RandomValue</code>
	 */
	RandomValue getRandomValue();

}
