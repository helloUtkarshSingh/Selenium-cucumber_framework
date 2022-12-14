package Practice.SeleniumFrameworkDesign.pageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SeleniumFrameworkDesign.BaseClass.BaseClass;

public class CheckOutPage extends BaseClass{
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);

		// initialization
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'payment__type payment__type--cc active')]")
	WebElement cardoption;
	
	@FindBy(xpath="(//input[contains(@class,'input txt')])[2]")
	WebElement CVV_number;
	
	@FindBy(xpath="(//input[contains(@class,'input txt')])[3]")
	WebElement Card_name;
	
	@FindBy(xpath="(//input[contains(@class,'input txt text-validated')])[3]")
	WebElement Country_name;
	
	@FindBy(css =".action__submit")
	WebElement orderPlace_button;
	
	@FindBy(xpath="//button[contains(@class,'ta-item list-group-item ng-star-inserted')]")
	List<WebElement> Country_drop_down;
	
	public void payment_detail(String cvv_no, String name) {
		cardoption.click();
		CVV_number.sendKeys(cvv_no);
		Card_name.sendKeys(name);
		
	}
	
	public List<WebElement> country_dropDown() {
		return Country_drop_down;
	}
	
	public void selectCountry(String Country_initials, String Country_full_name) {
		Country_name.sendKeys(Country_initials);
		for (WebElement option : country_dropDown()) {
			if (option.getText().equalsIgnoreCase(Country_full_name)) {
			option.click();
			break;
		}
			}
	}
	
	public ConfirmationPage PlaceOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500);");
		orderPlace_button.click();
		return new  ConfirmationPage(driver);
	}

	
	
}
