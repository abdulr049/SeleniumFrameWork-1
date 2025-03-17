package ascentBusinessPogeObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='field small'] //input[@class='input txt']")
	WebElement cvvInputBox;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement inputCountryInputBox;

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> countryList;

	public void fillCheckOutPage(String cvv, String countryName) {
		cvvInputBox.sendKeys(cvv);
		inputCountryInputBox.sendKeys(countryName);

		WebElement countryButton = countryList.stream()
				.filter(countryLis -> countryLis.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);

		// wait.until(ExpectedConditions.elementToBeClickable(countryButton)).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", countryButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", countryButton);
	}

}
