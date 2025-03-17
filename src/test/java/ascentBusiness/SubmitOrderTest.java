package ascentBusiness;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ascentBusinessPogeObject.CartPage;
import ascentBusinessPogeObject.CheckOutPage;
import ascentBusinessPogeObject.LandingPage;
import ascentBusinessPogeObject.PlaceOrder;
import ascentBusinessPogeObject.ProductCatalog;
import baseTest.BaseTest;
import baseTest.RetryAnalyzer;

@Listeners(baseTest.ListenersTest.class)

public class SubmitOrderTest extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getLoginData1", retryAnalyzer=RetryAnalyzer.class)
	public void submitOrderTest(HashMap<String, String> input)
			throws InterruptedException, IOException {

		WebDriver driver = goToLandingPage();

		LandingPage landingPage = new LandingPage(driver);
		ProductCatalog productCatalog = landingPage.loginPage(input.get("UserName"), input.get("Password"));

		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCart();

		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = productCatalog.goToCheckOutPage();

		checkOutPage.fillCheckOutPage("1234", "India");

		PlaceOrder placeOrder = new PlaceOrder(driver);
		placeOrder.placeOrder();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);

	}

//	@DataProvider
//	public Object[][] getLoginData() {
//		return new Object[][] { { "anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" },
//				{ "shetty@gmail.com", "Iamking@000", "ZARA COAT 3" } };
//	}
	
	@DataProvider
	public Object[][] getLoginData1()
	{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("UserName", "anshika@gmail.com");
		map.put("Password", "Iamking@000");
		map.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1=new HashMap<String, String>();
		map1.put("UserName", "shetty@gmail.com");
		map1.put("Password", "Iamking@000");
		map1.put("productName", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};
		
	}

}
