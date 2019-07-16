package ca.pitt.demo.spring.rest.frontend.values;

/**
 * RandomValue.
 * 
 * @author Andrew Pitt
 * @since 1.0.0
 */
public class RandomValue {

	/** Value. */
	private Integer value;

	public RandomValue() {
		super();
	}

	/**
	 * Public constructor.
	 * 
	 * @param aValue <code>Integer</code>
	 */
	public RandomValue(Integer aValue) {
		this.value = aValue;
	}

	/**
	 * Get value.
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
