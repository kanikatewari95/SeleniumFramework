package Academy;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class ValidateNavigationBar extends Base{
	
	public WebDriver objdriver; // defining it globally so that we can perform parallel execution
	
	//necessary step while using log4j....initializing log object
		private static Logger log = LogManager.getLogger(Base.class.getName());//here Base is className
	
	@BeforeTest
	public void start() throws IOException
	{
		objdriver= initializeDriver();
		objdriver.get(prop.getProperty("url")); //to take url from data.properties file...prop is taken from Base class
		
	}
	
	@Test
	public void validateAppNavBar() throws IOException
	{
	
		// to access the methods of another class i.e. LandingPage... 1. inheritance  2. create methods to that class and invoke it
		 LandingPage l = new LandingPage(objdriver);
		 
		l.getNavBar().isDisplayed(); //to check whether the bar displayed or not
		System.out.println("Displaying the navigation bar ? " + l.getNavBar().isDisplayed());
		
		Assert.assertTrue(l.getNavBar().isDisplayed()); //it is expecting true value if the is displayed gives FALSE then program will show error
		log.info("Navigation bar is displayed");
	}
	
	@AfterTest
	public void tearDown()
	{
		objdriver.close();
	}
	
}
