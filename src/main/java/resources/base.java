package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public WebDriver driver;
	public Properties prop;

	// I create a method that will invoke the browser
	// it will invoke the browser base on the option given in the properties file,
	// so this way i can run my test case in +
	// different browser just by changing the browser in the properties file

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(file);
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public void checkBrokenLink(String linkUrl) {

		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setConnectTimeout(500);
			httpConnection.setRequestMethod("GET");
			httpConnection.connect();

			if (httpConnection.getResponseCode() >= 400) {

				System.out.println(linkUrl + " --->" + httpConnection.getResponseMessage() + " ==> Is a broken link");
			} else {
				// System.out.println(linkUrl + " ----> " +
				// httpConnection.getResponseMessage());
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}
}
