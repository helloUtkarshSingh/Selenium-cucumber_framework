package Practice.SeleniumFrameworkDesignTestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import Practice.SeleniumFrameworkData.DataProviders;
import Practice.SeleniumFrameworkDesign.pageObject.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Landingpage landingpage;

	public WebDriver initialized_driver() throws IOException {

		Properties prop = new Properties();
		FileInputStream input = new FileInputStream("C:\\Users\\vi\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\Practice\\SeleniumFrameworkDesign\\Global_resource\\Global_Data.properties");
		prop.load(input);

		//String Browser_name = prop.getProperty("Browser");

//		if (Browser_name.equalsIgnoreCase("Chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		}
//		} else if (Browser_name.equalsIgnoreCase("FireFox")) {
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		} else {
//			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\Drivers\\operadriver.exe");
//			driver = new InternetExplorerDriver();
//
//		}
        
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}
	
	@BeforeMethod
	public Landingpage launchApplication() throws IOException {
		
		driver = initialized_driver();
		landingpage = new Landingpage(driver);
		landingpage.gotoUrl();
		return landingpage;
		
	}
	
	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
	
	public String TakeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot) driver;
		File source =  ss.getScreenshotAs(OutputType.FILE);
		
		//Storing the screen shot at file
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName +".png";
		
	}
	

}
