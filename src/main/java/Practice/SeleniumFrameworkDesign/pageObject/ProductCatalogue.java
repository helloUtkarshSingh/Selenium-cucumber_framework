package Practice.SeleniumFrameworkDesign.pageObject;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Practice.SeleniumFrameworkDesign.BaseClass.BaseClass;

public class ProductCatalogue extends BaseClass {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);

		// initialization
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

//	@FindBy(tagName = "h5")
//	List<WebElement> products_list;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(xpath = "(//button[contains(@class,'btn btn-custom')])[3]")
	WebElement viewcart;

	@FindBy(xpath = "(//button[contains(@class,'btn btn-custom')])[2]")
	WebElement vieworderhistory;
	
	@FindBy(css=".mb-3")
	List<WebElement> products_list;

	// @FindBy(xpath = "//button[contains(@class,'btn w-10 rounded')]")
	
	
	//By addToCart = By.xpath("//button[contains(@class,'btn w-10 rounded')]");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By productBy = By.cssSelector(".mb-3");
	By successfully_added = By.id("toast-container");

	public List<WebElement> getProductlist() {
		waitWhileElementIsVisibility(productBy);
		return products_list;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductlist().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		
		System.out.println(prod.getText());
		return prod;
	}

	public void addproductTocart(String productName) throws InterruptedException {

		WebElement prod = getProductByName(productName);
		System.out.println(prod.getText());
		prod.findElement(addToCart).click();
		waitWhileElementIsVisibility(successfully_added);
		waitWhileElementIsNotVisibility(spinner);
	}

	public MyCartpage viewcart() {
		viewcart.click();
		return new MyCartpage(driver);
	}

	public OrderHistoryrPage MyOrderHistory() {
		vieworderhistory.click();
		return new OrderHistoryrPage(driver);

	}

}
