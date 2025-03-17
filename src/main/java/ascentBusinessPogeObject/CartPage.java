package ascentBusinessPogeObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ascentBusinessAbstractComponants.AbstractComponants;

public class CartPage extends AbstractComponants {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;

	public List<WebElement> getCartProduct() {
		return cartProducts;
	}

	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return match;
	}

}
