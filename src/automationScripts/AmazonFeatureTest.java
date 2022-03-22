package automationScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
	
	@Test(priority = 0)
	public void verifyTextOfThatPage () {
		
		String expectedTitle = "Amazon.com. Spend less. Smile more.";
		String actualTitle;
		
		actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority = 100)
	public void searchProduct() {
		String productItem = "Sylvanian Families";
		
		String category = "Toys & Games";
		
		WebElement selDropdown = driver.findElement(By.id("searchDropdownBox"));
		Select selCategory = new Select(selDropdown);
		
		selCategory.selectByVisibleText(category);
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(productItem);
		
		driver.findElement(By.xpath("//input[@value='Go']")).click();
	}
	
	@Test
	public void getNthProduct() {
		int productItemNumber = 2;
		
		String xpathExpression = String.format("//span[@data-component-type='s-product-image'][@data-component-id = '7']", productItemNumber);
		
		WebElement nthProduct = driver.findElement(By.xpath(xpathExpression));
	}
	
	@AfterClass
	public void closeBrowser() {
		
		driver.quit();
	}
}
