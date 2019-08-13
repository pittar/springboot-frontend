package integration;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Test;

public class GoogleIT {

	private SeleniumConfig config;
	private String url = "http://frontend-app-dev.apps-bfc1.generic.opentlc.com";

	public GoogleIT() throws MalformedURLException {
		config = new SeleniumConfig();
		config.getDriver().get(url);
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
		config.getDriver().get(url);
		assertEquals("Title not as expected: ", "Incredible Random Number Generator", getTitle());
	}

}
