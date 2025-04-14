package ascentBusiness;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(baseTest.ListenersTest.class)

public class StandAloneCode {
	
	@Test
	public void standAloneCode() {

		String productName = "ZARA COAT 3";

		// Setup WebDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Login
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();

		// Explicit wait for products to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']")));

		// Get all product containers
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']/h5"));

		WebElement prod = products.stream().filter(product -> product.getText().equals(productName)).findFirst()
				.orElse(null);

		WebElement addToCartBtn = prod.findElement(By.xpath(".//following-sibling::button[text()=' Add To Cart']"));
		// wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartBtn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		WebElement cartButton = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));

		// wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cartButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));

		Assert.assertTrue(match);

		WebElement checkoutButton = driver
				.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']/ul/li/button"));

		// wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkoutButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);

		driver.findElement(By.xpath("//div[@class='field small'] //input[@class='input txt']")).sendKeys("1234");

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");

		List<WebElement> countryList = driver
				.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		WebElement countryButton = countryList.stream()
				.filter(countryLis -> countryLis.getText().equalsIgnoreCase("India")).findFirst().orElse(null);

		// wait.until(ExpectedConditions.elementToBeClickable(countryButton)).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", countryButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", countryButton);

		// countryButton.click();

		WebElement placeOrder = driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));
		// wait.until(ExpectedConditions.elementToBeClickable(placeOrder)).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", placeOrder);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrder);

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);

	}

}
