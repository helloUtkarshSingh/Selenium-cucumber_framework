package Practice.SeleniumFrameworkDesign.pageObject;

import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Practice.SeleniumFrameworkDesign.BaseClass.BaseClass;
import net.bytebuddy.asm.Advice.Return;

public class MyCartpage extends BaseClass{
	WebDriver driver;

	public MyCartpage(WebDriver driver) {
		super(driver);

		// initialization
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//span)[2]")
	WebElement total_amount;
	
	@FindBy(xpath="(//button[contains(@class,'btn btn-primary')])[3]")
	WebElement checkoutButton;
	
	@FindBy(xpath="//div[contains(@class,'infoWrap')]//h3")
	List<WebElement> cart_items;
	
	public List<WebElement> Item_in_cart() {
		return cart_items;
	}
	
	public Boolean Validate_the_item(String product_name) {
		System.out.println("The Total Amount:- "+total_amount.getText());

		Boolean val =  cart_items.stream().anyMatch(cart_item -> cart_item.getText().equalsIgnoreCase(product_name));
		return val;
	}
	
	public CheckOutPage checkout() {
		checkoutButton.click();
		return new CheckOutPage(driver);
	}

}
