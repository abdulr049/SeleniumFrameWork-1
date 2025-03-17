package ascentBusinessAbstractComponants;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ascentBusinessPogeObject.CartPage;

public class AbstractComponants {
	
	WebDriver driver;
	
	public AbstractComponants(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;

	public void waitForElementToAppear(By findBY)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
	}
	
	public void waitForElementToDisAppear() throws InterruptedException
	{
		Thread.sleep(1000);
	}
	
	public CartPage goToCart()
	{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cartButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
		
		CartPage cartPage=new CartPage(driver);
		
		return cartPage;		

	}
	

}
