package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageIT {

	private SeleniumConfig config;
	private String url = "http://frontend-app-dev.apps-bfc1.generic.opentlc.com";

	public MainPageIT() throws MalformedURLException {
		config = new SeleniumConfig();
	}

	public String getTitle() {
		return this.config.getDriver().getTitle();
	}

	@After
	public void closeConnection() {
		config.getDriver().quit();
	}

	@Test
	public void googleTitleIT() {
		WebDriver driver = config.getDriver();
		driver.get(url);
		assertEquals("Title not as expected: ", "Incredible Random Number Generator", getTitle());
		// Save the random value from the page.
		String value1 = driver.findElement(By.id("random-value")).getText();
		// Reload the page and get a new random value.
		driver.get(url);
		String value2 = driver.findElement(By.id("random-value")).getText();
		// Values should not be the same.
		assertFalse("Values should not be the same.", value1.contentEquals(value2));
		System.out.println("Values: " + value1 + ", " + value2);
	}

}
