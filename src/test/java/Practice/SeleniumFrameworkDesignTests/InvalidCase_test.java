package Practice.SeleniumFrameworkDesignTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Practice.SeleniumFrameworkDesign.pageObject.MyCartpage;
import Practice.SeleniumFrameworkDesign.pageObject.ProductCatalogue;
import Practice.SeleniumFrameworkDesignTestComponent.BaseTest;
import Practice.SeleniumFrameworkDesignTestComponent.Retest;

public class InvalidCase_test extends BaseTest {

	@Test(retryAnalyzer=Retest.class)
	public void InvalidCredentials() throws IOException {
		//String item_to_buy = "ZARA COAT 3";
		
		landingpage.loginApplication("Rajsingh@gmail.com", "Utkarsh@1345");
		Assert.assertEquals("Incorrect email o password.", landingpage.geterror_mss());

}
	@Test
	public void InValidProductErro() throws InterruptedException {
		String item_to_buy = "ZARA COAT 3";
		String productToBuy = "ZARA COAT 3#";
		
		ProductCatalogue menu = landingpage.loginApplication("RahulSingh@gmail.com", "Rahul@12345");

		menu.addproductTocart(item_to_buy);

		MyCartpage Cartdetail = menu.viewcart();
		Boolean match = Cartdetail.Validate_the_item(productToBuy);
		Assert.assertFalse(match);
	}
}
