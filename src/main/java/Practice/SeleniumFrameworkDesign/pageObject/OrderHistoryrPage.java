package Practice.SeleniumFrameworkDesign.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SeleniumFrameworkDesign.BaseClass.BaseClass;

public class OrderHistoryrPage extends BaseClass {

	WebDriver driver;

	public OrderHistoryrPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> order_items;

	public Boolean verifyOrderHistory(String product_name) {

		Boolean val = order_items.stream().anyMatch(cart_item -> cart_item.getText().equalsIgnoreCase(product_name));
		return val;
	}
}
