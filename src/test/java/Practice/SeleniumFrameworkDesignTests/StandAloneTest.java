package Practice.SeleniumFrameworkDesignTests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Practice.SeleniumFrameworkDesign.pageObject.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//String[] Shopping_list = { "Brocolli", "Cauliflower", "Brinjal" };
		//
		String item_to_buy = "ADIDAS ORIGINAL";
		String country = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		
		Landingpage landingpage = new Landingpage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("utkarshsingh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Utkarsh@12345");
		driver.findElement(By.id("login")).click();

		//To add Single item
		List<WebElement> products_list = driver.findElements(By.xpath("//div[contains(@class,'col-lg-4 col-md-6')]"));

		WebElement prod = products_list.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(item_to_buy)).findFirst()
				.orElse(null);
		 prod.findElement(By.xpath("//button[contains(@class,'btn w-10 rounded')]")).click();

		// To add 2-3 items together
//		String[] items_to_buy = { "ZARA COAT 3", "ADIDAS ORIGINAL" };
//		List<WebElement> All_product_name = driver.findElements(By.tagName("h5"));
//		List items_to_buy_list = Arrays.asList(items_to_buy);
//    
//		int j=0;
//		
//		for (int i = 0; i < All_product_name.size(); i++) {
//			String name = All_product_name.get(i).getText();
//			
//			if (items_to_buy_list.contains(name)) {
//				j++;
//				System.out.println(name);
//				driver.findElements(By.xpath("//button[contains(@class,'btn w-10 rounded')]")).get(i).click();
//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
//				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//				if (j>=items_to_buy.length) {
//					break;
//				}
//			}
//		}
		

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("(//button[contains(@class,'btn btn-custom')])[3]")).click();

		System.out.println(driver.findElement(By.xpath("(//span)[2]")).getText());
		
		List<WebElement> cart_items = driver.findElements(By.xpath("//div[contains(@class,'infoWrap')]//h3"));
		Boolean val = cart_items.stream().anyMatch(cart_item -> cart_item.getText().equals(item_to_buy));
		Assert.assertTrue(val);

		driver.findElement(By.xpath("(//button[contains(@class,'btn btn-primary')])[3]")).click();

		driver.findElement(By.xpath("//div[contains(@class,'payment__type payment__type--cc active')]")).click();

		driver.findElement(By.xpath("(//input[contains(@class,'input txt')])[2]")).sendKeys("123");
		driver.findElement(By.xpath("(//input[contains(@class,'input txt')])[3]")).sendKeys("Bank of Utkarsh");

		driver.findElement(By.xpath("(//input[contains(@class,'input txt text-validated')])[3]")).sendKeys("Ind");

		List<WebElement> suggested_countrys = driver
				.findElements(By.xpath("//button[contains(@class,'ta-item list-group-item ng-star-inserted')]"));
//		suggested_countrys.stream().filter(suggested_country -> suggested_country.getText().equalsIgnoreCase(country));

		// to select india in country
		for (WebElement option : suggested_countrys) {
			if (option.getText().equalsIgnoreCase(country)) {
				option.click();
				break;
			}
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500);");
		driver.findElement(By.cssSelector(".action__submit")).click();
		Thread.sleep(2000);
		String Comfirm_mss = driver.findElement(By.xpath("(//h1[contains(@class,'hero-primary')])")).getText();
		System.out.println(Comfirm_mss);
		Assert.assertTrue(Comfirm_mss.equalsIgnoreCase("Thankyou for the order."), "THANKYOU FOR THE ORDER.");
		driver.close();

	}
}
