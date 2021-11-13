package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
// after clicking on qaclickacademy.com we get this page
public class LandingPage {
	
	//page object concept
	
	WebDriver objdriver;
	
	By SignIn = By.cssSelector("a[href*='sign_in']");
	
	By title = By.cssSelector("div[class='text-center'] h2"); //to take title Featured courses from ValidateTitle class
	
	By navBar = By.cssSelector("nav[class*='navbar'] >ul"); // to take the display box in rightmost corner
	
	By header = By.cssSelector("[class*='hidden']>h3"); // to take the header
	
	//since in homepage loadingPage object is expecting a argument hence we have to create a constructor in LandingPage file to accept it
	public LandingPage(WebDriver objdriver) {
		// TODO Auto-generated constructor stub
		
		this.objdriver = objdriver;
	}


	public LoginPage getLogin()
	{
	     objdriver.findElement(SignIn).click();
	     LoginPage lp = new LoginPage(objdriver);
	     return lp;
	}

	public WebElement getTitle()
	{
		return objdriver.findElement(title);
	}
	
	public WebElement getNavBar()
	{
		return objdriver.findElement(navBar);
	}
	
	public WebElement getHeader()
	{
		return objdriver.findElement(header);
	}
}
