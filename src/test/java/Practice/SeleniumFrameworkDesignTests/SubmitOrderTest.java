package Practice.SeleniumFrameworkDesignTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.SeleniumFrameworkData.DataProviders;
import Practice.SeleniumFrameworkData.DataReader;
import Practice.SeleniumFrameworkDesign.pageObject.CheckOutPage;
import Practice.SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import Practice.SeleniumFrameworkDesign.pageObject.MyCartpage;
import Practice.SeleniumFrameworkDesign.pageObject.OrderHistoryrPage;
import Practice.SeleniumFrameworkDesign.pageObject.ProductCatalogue;
import Practice.SeleniumFrameworkDesignTestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	//String item_to_buy = "ADIDAS ORIGINAL";

	// @Test(dataProvider = "getData", groups = { "purchase" })
//	@Test(dataProvider = "getJsonData", dataProviderClass = DataProviders.class)
//	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
//		ProductCatalogue menu = landingpage.loginApplication(input.get("email"), input.get("password"));
//
//		// List<WebElement> products = menu.getProductlist();
//		menu.addproductTocart(input.get("product"));
//
//		MyCartpage Cartdetail = menu.viewcart();
//		Boolean match = Cartdetail.Validate_the_item(input.get("product"));
//		Assert.assertTrue(match);
//		CheckOutPage checkoutpage = Cartdetail.checkout();
//
//		checkoutpage.payment_detail(input.get("cvv_no"), input.get("Bank_name"));
//		checkoutpage.selectCountry(input.get("country_initial"), input.get("country"));
//		ConfirmationPage ConfirmPage = checkoutpage.PlaceOrder();
//
//		Boolean value = ConfirmPage.PrinConfirmation_mess();
//		Assert.assertTrue(value, "THANKYOU FOR THE ORDER.");
//
//	}

	@Test(dataProvider = "getExcelData", dataProviderClass = DataProviders.class)
	public void submitOrder(String Sr_no, String Email, String PassWord, String Product, String Country, String Cvv_no,
			String Bank_Name, String Country_Initial, String Options) throws IOException, InterruptedException {

		// List<WebElement> products = menu.getProductlist();

			ProductCatalogue menu = landingpage.loginApplication(Email, PassWord);
			menu.addproductTocart(Product);

			MyCartpage Cartdetail = menu.viewcart();
			Boolean match = Cartdetail.Validate_the_item(Product);
			Assert.assertTrue(match);
			CheckOutPage checkoutpage = Cartdetail.checkout();

			checkoutpage.payment_detail(Cvv_no, Bank_Name);
			checkoutpage.selectCountry(Country_Initial, Country);
			ConfirmationPage ConfirmPage = checkoutpage.PlaceOrder();

			Boolean value = ConfirmPage.PrinConfirmation_mess();
			Assert.assertTrue(value, "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getExcelData", dataProviderClass = DataProviders.class)
	public void OrderHistroyTest(String Sr_no, String Email, String PassWord, String Product, String Country,
			String Cvv_no, String Bank_Name, String Country_Initial, String Options) {

		ProductCatalogue menu = landingpage.loginApplication(Email, PassWord);
		OrderHistoryrPage orderHistory = menu.MyOrderHistory();

		Assert.assertTrue(orderHistory.verifyOrderHistory(Product));
	}

}
