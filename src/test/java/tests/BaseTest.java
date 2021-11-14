package tests;

import com.utility.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.myPages.BasePage;
import com.myPages.Page;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

	WebDriver driver;
	public Page page;

	@BeforeSuite
	public void BeforeSuite() throws IOException {
		ConfigReader.ConfigFileReader();
	}
	
	@BeforeMethod
	@Parameters(value={"browser"})
	public void launchBrowser(String browser) throws Exception
	{
		if(browser.equalsIgnoreCase("Chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		else
		{
			throw new Exception("No browser is not  defined in xml file");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		page= new BasePage(driver);
	}
	
	@AfterMethod
	public void quiteBrowser()
	{
		driver.quit();
	}
}
