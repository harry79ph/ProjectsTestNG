package testcases;


import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.BaseTest;

public class KenkoTNG extends BaseTest {
	
	public static String baseURL = "https://kenkoglobal.com/";
	
	@BeforeTest
	public void navigate() {
		
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
	}
	
	
	@Test(priority = 1)
	private void kenko() {
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/a/div"))).click();
		
		driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/div[1]/div/div/a")).click();
		

		WebElement block = driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/div[1]/div/div/div"));

		List<WebElement> options = block.findElements(By.tagName("a"));

		System.out.println("Total Links: "+options.size());

		for (WebElement option : options) {

			System.out.println("Link of option: " + option.getAttribute("href"));
		}

		System.out.println("---Generating window ids from first window---");

		Set<String> winids = driver.getWindowHandles();
		Iterator<String> iterate = winids.iterator();

		String first_window = iterate.next();
		System.out.println(first_window);

		driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/div[1]/div/div/div/a[1]")).click();

		System.out.println("---Generating window ids from second window---");

		winids = driver.getWindowHandles();
		iterate = winids.iterator();

		System.out.println("first window: " + iterate.next());// first window
		String second_window = iterate.next();// second window
		System.out.println("second window: " + second_window);
		
		driver.switchTo().window(second_window);
		
		
		
	}
	@Test(priority = 2)//the method is exluded in xml file if you run by it
	public void secondPage() {
 
		WebElement filter = driver.findElement(By.xpath("//li[@id=\"menu-item-326\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(filter)).click();
		WebElement uv = driver.findElement(By.xpath("//*[@id=\"archive-product\"]/div[2]/div[3]/div[2]/div/div[2]/div[1]/span/a"));
		wait.until(ExpectedConditions.elementToBeClickable(uv)).click(); 
		if(browser.equals("chrome")) {
			WebElement selection=driver.findElement(By.id("pa_lens-size"));
			wait.until(ExpectedConditions.elementToBeClickable(selection));
			Select select = new Select(selection);
			select.selectByValue("58mm");
		} else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pa_lens-size"))).sendKeys("58mm");
		}
		
		
		WebElement description=driver.findElement(By.id("tab-description"));
		System.out.println(description.getText());
	}

}
