package automationScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonFeatureTest {
	ChromeDriver driver;
	
	@BeforeClass
	public void invokeBrowser() {
			
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium Testing\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://amazon.com");
	}
	
	@Test
	public void verifyTextOfThatPage () {
		
		String expectedTitle = "Amazon.com. Spend less. Smile more.";
		String actualTitle;
		
		actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@AfterClass
	public void closeBrowser() {
		
		driver.quit();
	}
}
