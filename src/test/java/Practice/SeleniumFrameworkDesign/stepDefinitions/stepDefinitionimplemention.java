package Practice.SeleniumFrameworkDesign.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import Practice.SeleniumFrameworkDesign.pageObject.CheckOutPage;
import Practice.SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import Practice.SeleniumFrameworkDesign.pageObject.Landingpage;
import Practice.SeleniumFrameworkDesign.pageObject.MyCartpage;
import Practice.SeleniumFrameworkDesign.pageObject.ProductCatalogue;
import Practice.SeleniumFrameworkDesignTestComponent.BaseTest;
import io.cucumber.java.en.*;

public class stepDefinitionimplemention extends BaseTest {

	public Landingpage landingpage;
	public ProductCatalogue menu;
	public CheckOutPage checkoutpage;
	public MyCartpage Cartdetail;
	public ConfirmationPage ConfirmPage;

	@Given("I landed on E-commerce Website")
	public void I_landed_on_Ecommerce_Website() throws IOException {
		 landingpage = launchApplication();
		
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		menu = landingpage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart and Checkout$")
	public void I_add_product_to_Cart_and_Checkout(String product) throws InterruptedException {
		menu.addproductTocart(product);
		Cartdetail = menu.viewcart();
		Boolean match = Cartdetail.Validate_the_item(product);
		Assert.assertTrue(match);
		checkoutpage = Cartdetail.checkout();
	}

	@Then("^Provide the Cvv number (.+) and Bank name (.+)$")
	public void Provide_the_Cvv_number_and_Bankname(String cvv_no, String Bank_name) {
		checkoutpage.payment_detail(cvv_no, Bank_name);

	}

	@Then("^Select the country (.+) and country_initial (.+)$")
	public void Select_the_country_and_country_initial(String country, String country_initial) {
		checkoutpage.selectCountry(country_initial, country);
		ConfirmPage = checkoutpage.PlaceOrder();
	}

	@And("{string} message is displayed on the Confirmation page")
	public void message_Confirmation_page(String string) {
		Boolean value = ConfirmPage.PrinConfirmation_mess();
		Assert.assertTrue(value, string);

	}
}
