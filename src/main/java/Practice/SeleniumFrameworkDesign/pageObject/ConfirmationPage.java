package Practice.SeleniumFrameworkDesign.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SeleniumFrameworkDesign.BaseClass.BaseClass;

public class ConfirmationPage extends BaseClass {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);

		// initialization
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//h1[contains(@class,'hero-primary')])")
	WebElement confirmation_mess;
	
	public Boolean PrinConfirmation_mess() {
		
		String Confirm_mss = confirmation_mess.getText();
		System.out.println(Confirm_mss);
		Boolean value =  Confirm_mss.equalsIgnoreCase("Thankyou for the order.");
		return value;
	}
}
