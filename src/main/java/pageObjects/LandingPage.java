package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.base;

public class LandingPage extends base {

	public WebDriver driver;

	private By title = By.xpath("//a[@class = 'flex']/span");

	public LandingPage(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement getTitle() {

		return driver.findElement(title);

	}
	
}
