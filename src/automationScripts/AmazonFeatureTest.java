package automationScripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
	
	@Test(priority = 200)
	public void getNthProduct() {
		int productItemNumber = 20;
		
		String xpathExpression = String.format("//div[@data-component-type='s-search-result'][%d]", productItemNumber);
		
		WebElement nthProduct = driver.findElement(By.xpath(xpathExpression));
		
		String nthProductResult = nthProduct.getText();
		
		System.out.println(nthProductResult);
	}
	
	@Test(priority = 300, enabled = false)
	public void getAllProducts() {
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
	
		String productResult;
		
		for(WebElement product : allProducts) {
			
			productResult = product.getText();
			
			System.out.println(productResult);
			
			System.out.println("---------------------------------------------");
			
		}
	}
	
	@Test(priority = 400, enabled = false)
	public void searchAllProductsViaScrollDown() {
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
		
		String productResult;
		
		Actions action = new Actions(driver);
		
		for(WebElement product : allProducts) {
			
			action.moveToElement(product).build().perform();
			
			productResult = product.getText();
			
			System.out.println(productResult);
			
			System.out.println("---------------------------------------------");
		}
	}
	
	@Test(priority = 500)
	public void getAllProductsViaScrollDownUsingJS() {
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
	
		String productResult;
		
		int xCordinate, yCordinate;
		
		for(WebElement product : allProducts) {
			
			xCordinate = product.getLocation().x;
			yCordinate = product.getLocation().y;
			
			scrollDown(xCordinate, yCordinate);
			
			productResult = product.getText();
			
			System.out.println(productResult);
			
			System.out.println("---------------------------------------------");
			
		}
	}
	
	private void scrollDown(int x, int y) {
		JavascriptExecutor jsEngine;
		jsEngine = (JavascriptExecutor) driver;
		
		String jsCommand;
		
		jsCommand = String.format("window.scrollBy(%d,%d)", x, y);
		
		jsEngine.executeScript(jsCommand);
	}
	
	@AfterClass
	public void closeBrowser() {
		
		driver.quit();
	}
}
