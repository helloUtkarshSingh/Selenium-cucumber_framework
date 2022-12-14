package Practice.SeleniumFrameworkDesign.stepDefinitions;

import org.testng.Assert;

import Practice.SeleniumFrameworkDesign.pageObject.*;
import Practice.SeleniumFrameworkDesignTestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionimplemention extends BaseTest {

	public Landingpage landingpage;
	public ProductCatalogue menu;
	public MyCartpage Cartdetail;
	public CheckOutPage checkoutpage;
	public ConfirmationPage ConfirmPage;
	public OrderHistoryrPage orderHistory;

	@Given("I landed on E-commerce Website")
	public void i_landed_on_ecommerce_website() throws Throwable {
		landingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) throws Throwable {
		menu = landingpage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart and Checkout$")
	public void i_add_product_to_cart_and_checkout(String product) throws Throwable {
		menu.addproductTocart(product);
		Cartdetail = menu.viewcart();
		Boolean match = Cartdetail.Validate_the_item(product);
		Assert.assertTrue(match);
		checkoutpage = Cartdetail.checkout();
	}

	@Then("^Provide the Cvv number (.+) and Bank name (.+)$")
	public void provide_the_cvv_number_and_bank_name(String cvvno, String bankname) throws Throwable {
		checkoutpage.payment_detail(cvvno, bankname);
	}

	@Then("^Select the country_initial (.+) and country (.+)$")
	public void select_the_country_and_countryinitial(String countryinitial, String country) throws Throwable {
		checkoutpage.selectCountry(countryinitial, country);
		ConfirmPage = checkoutpage.PlaceOrder();
	}

	@And("^\"([^\"]*)\" message is displayed on the Confirmation page$")
	public void something_message_is_displayed_on_the_confirmation_page(String string) throws Throwable {
		Boolean value = ConfirmPage.PrinConfirmation_mess();
		Assert.assertTrue(value, string);
		//driver.close();

	}
	
	@Then("Close the Browser")
	public void close_the_Browser(){
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String string) throws Throwable {
		Assert.assertEquals(string, landingpage.geterror_mss());
		//driver.close();
	}

	@Then("^Check the order history with product (.+)$")
	public void check_the_order_history_with_product(String product) throws Throwable {
	    orderHistory = menu.MyOrderHistory();
		Assert.assertTrue(orderHistory.verifyOrderHistory(product));
		//driver.close();
	}

}
