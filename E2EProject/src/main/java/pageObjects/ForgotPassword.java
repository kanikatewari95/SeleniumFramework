package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//after clicking on login in qaclickacademy we get this page where we have to provide email and password

public class ForgotPassword {
	
	//page object concept
	
	WebDriver objdriver;
	
	By email = By.cssSelector("input[type='email']");
	By sendMeInstruction = By.cssSelector("input[name='commit']");
	
	public ForgotPassword(WebDriver objdriver) {
		// TODO Auto-generated constructor stub
		
		this.objdriver = objdriver;
	}


	public WebElement getEmail()
	{
		return objdriver.findElement(email);
	}
	
	public WebElement getSendMeInstruction()
	{
		return objdriver.findElement(sendMeInstruction);
	}
	

}
