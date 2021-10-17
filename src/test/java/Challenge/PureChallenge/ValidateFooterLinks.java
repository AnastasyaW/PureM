package Challenge.PureChallenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import resources.base;

public class ValidateFooterLinks extends base {

	LandingPage l;

	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();

		driver.get(prop.getProperty("url"));
	}

	@Test
	public void validLandPage() {
		Assert.assertEquals(driver.getTitle(), "Divercity - Recruiting Tools for Diverse Hires");
	}

	@Test
	public void validateLinks() {
	
		WebElement foot = driver.findElement(By.xpath("//footer[@class = 'bg-blue-900']/div/div[1]"));
	
		List<WebElement> links = foot.findElements(By.tagName("a"));
	
		List<String> listUrl = new ArrayList<String>();
	
		for (WebElement a : links) {
	
			String url = a.getAttribute("href");
			listUrl.add(url);
	
		}
		listUrl.parallelStream().forEach(i -> checkBrokenLink(i));
	}

	@AfterTest
	public void teadDown() {
		driver.quit();
	}

}
