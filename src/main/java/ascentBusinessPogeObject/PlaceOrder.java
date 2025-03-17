package ascentBusinessPogeObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrder {

	WebDriver driver;

	public PlaceOrder(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrder;

	public void placeOrder() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", placeOrder);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrder);

	}

}
