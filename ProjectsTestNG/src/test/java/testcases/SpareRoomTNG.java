package testcases;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpareRoomTNG {
	
	public static String baseURL = "https://www.spareroom.co.uk/";
	public static String browser = "chrome";
	public static WebDriver driver;
	
	@Test(dataProvider = "list")
	private void launch(String browser, String name, String pass) throws InterruptedException {
		if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		driver.findElement(By.xpath("//*[@id=\"show-user-auth-popup\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginemail\"]"))).sendKeys(name);
		driver.findElement(By.xpath("//*[@id=\"loginpass\"]")).sendKeys(pass);
		//driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"user_auth\"]/div/div[1]/div[2]/form/div[2]/a")).getText();
		driver.quit();
	}
	
	@DataProvider(name = "list")
	public Object[][] dataSet1(){
		return new Object[][] {
			{"chrome","name1","pass1"},
			{"firefox","name2","pass2"},
			{"edge","name3","pass3"},
			};
		
		
	}

}
