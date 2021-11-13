package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//after clicking on login in qaclickacademy we get this page where we have to provide email and password

public class LoginPage {
	
	//page object concept
	
	WebDriver objdriver;
	
	By email = By.cssSelector("#user_email");
	By password = By.cssSelector("[type='password']");
	By login = By.cssSelector("[type='submit']");
	By forgotPassword = By.cssSelector("[href*='password/']");
	

	public LoginPage(WebDriver objdriver) {
		// TODO Auto-generated constructor stub
		
		this.objdriver = objdriver;
	}


	public WebElement getEmail()
	{
		return objdriver.findElement(email);
	}
	
	public WebElement getPassword()
	{
		return objdriver.findElement(password);
	}
	
	public WebElement getLogin()
	{
		return objdriver.findElement(login);
	}
	
	public ForgotPassword getForgotPassword()
	{
		objdriver.findElement(forgotPassword).click();
		ForgotPassword fp = new ForgotPassword(objdriver);
		return fp;
	}

}
