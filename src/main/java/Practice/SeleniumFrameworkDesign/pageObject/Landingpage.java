package Practice.SeleniumFrameworkDesign.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SeleniumFrameworkDesign.BaseClass.BaseClass;

public class Landingpage extends BaseClass {

	WebDriver driver;

	public Landingpage(WebDriver driver) {
		super(driver);

		// initialization
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(id="toast-container")
	WebElement invalid_warning;
	
	public void gotoUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String email, String password) {
		// TODO Auto-generated method stub
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		return new ProductCatalogue(driver);
	}
	
	public String geterror_mss() {
		
		waitforelementtoapper(invalid_warning);
		String mss = invalid_warning.getText();
		return mss;
	}
	


}
