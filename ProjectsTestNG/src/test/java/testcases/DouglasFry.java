package testcases;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DouglasFry {

	public String baseURL = "https://www.douglasfry.com/";
		public static WebDriver driver;

		@BeforeSuite
		public void launch() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		
		@AfterSuite
		public void endSession() {
			driver.quit();
		}
		
		@Test(retryAnalyzer = Retry.class)
		private void douglas() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[1]/div/a")).click();
		WebElement button = driver.findElement(By.xpath("/html/body/section[6]/div/div/a/span"));
		Actions action = new Actions(driver);
		action.moveToElement(button).perform();
		driver.findElement(By.xpath("/html/body/header/div")).click();
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[7]/a")).click();
		
		
		WebElement name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		//action.moveToElement(name).click().sendKeys("Automation test").keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").build().perform();
		name.sendKeys("Automation test");
		action.keyDown(name, Keys.CONTROL).sendKeys("a").sendKeys("c").build().perform();
		WebElement message = driver.findElement(By.xpath("//*[@id=\"message\"]"));
		action.keyDown(message, Keys.CONTROL).sendKeys("a").sendKeys("v").build().perform();
		
	}

}
