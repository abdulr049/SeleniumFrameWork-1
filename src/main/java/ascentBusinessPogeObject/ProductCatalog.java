package ascentBusinessPogeObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ascentBusinessAbstractComponants.AbstractComponants;

public class ProductCatalog extends AbstractComponants {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card-body']/h5")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productBy = By.xpath("//div[@class='card-body']/h5");
	By addToCart = By.xpath(".//following-sibling::button[text()=' Add To Cart']");

	By waitTovisibleElemtn = By.cssSelector("#toast-container");

	By waitTocardBody = By.xpath("//div[@class='card-body']");

	@FindBy(xpath = "//div[@class='subtotal cf ng-star-inserted']/ul/li/button")
	WebElement checkoutButton;

	public List<WebElement> getProductsList() {

		waitForElementToAppear(productBy);
		return products;

	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream().filter(product -> product.getText().equals(productName))
				.findFirst().orElse(null);

		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		WebElement addToCartBtn = prod.findElement(addToCart);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartBtn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

		waitForElementToAppear(waitTocardBody);

		waitForElementToAppear(waitTovisibleElemtn);
		waitForElementToDisAppear();

	}

	public CheckOutPage goToCheckOutPage() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkoutButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);
		
		CheckOutPage checkOutPage=new CheckOutPage(driver);
		return checkOutPage;

	}

}
