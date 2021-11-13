package Academy;

import java.io.IOException;

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

public class ValidateTitle extends Base{
	
	public WebDriver objdriver; // defining it globally so that we can perform parallel execution
	LandingPage l;
	
	//necessary step while using log4j....initializing log object
	private static Logger log = LogManager.getLogger(Base.class.getName());//here Base is className
	
	@BeforeTest
	public void start() throws IOException
	{
		objdriver= initializeDriver();
		log.info("Driver is initialized");
		
		//objdriver.get("https://qaclickacademy.com/");.....instead of writing this code write the below mentioned code, both will give same result
		objdriver.get(prop.getProperty("url")); //to take url from data.properties file...prop is taken from Base class
		log.info("Navigated to url");
		
		// to access the methods of another class i.e. LandingPage... 1. inheritance  2. create methods to that class and invoke it
		  l = new LandingPage(objdriver);
		
	}
	
	@Test
	public void validateAppTitle() throws IOException
	{
		
		 l.getTitle().getText(); //to get the text of title
		 
		 //to compare the text from browser with actual text
		 Assert.assertEquals( l.getTitle().getText(), "FEATURED COURSES123"); //it will fail here
		 log.info("Successfully validated Text message");
		 
	}
	
	@Test
	public void validateHeader()
	{
		 Assert.assertEquals( l.getHeader().getText(), "AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING"); 
		 log.info("Header has been validated");
	}
	
	@AfterTest
	public void tearDown()
	{
		objdriver.close();
	}
	
}
