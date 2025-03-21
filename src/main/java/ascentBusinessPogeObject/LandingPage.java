package ascentBusinessPogeObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	
	
	public ProductCatalog loginPage(String userName,String Password)
	{
		
		userEmail.sendKeys(userName);
		userPassword.sendKeys(Password);
		login.click();
		ProductCatalog productCatalog=new ProductCatalog(driver);
		return productCatalog;
	
	}

}
