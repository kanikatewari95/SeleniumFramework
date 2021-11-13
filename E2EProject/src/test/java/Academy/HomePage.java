package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;



public class HomePage extends Base{
	
	public WebDriver objdriver; // defining it globally so that we can perform parallel execution
	
	//necessary step while using log4j....initializing log object
		private static Logger log = LogManager.getLogger(Base.class.getName());//here Base is className
		

		@BeforeTest
		public void start() throws IOException
		{
			objdriver= initializeDriver();
		}
		
	@Test(dataProvider="getData")
	public void basePageNavigation(String username, String password, String text) throws IOException
	{

		//objdriver.get("https://qaclickacademy.com/");.....instead of writing this code write the below mentioned code, both will give same result
		objdriver.get(prop.getProperty("url")); //to take url from data.properties file...prop is taken from Base class
		
		// to access the methods of another class i.e. LandingPage... 1. inheritance  2. create methods to that class and invoke it
		 LandingPage l = new LandingPage(objdriver);
		 
		 LoginPage lp = l.getLogin(); // getLogin method is in LandingPage class
		 
		 //to access the methods of Login page we have to create the object for that particular class
		// LoginPage lp = new LoginPage(objdriver);...but we have created it inside landing page
		 
		 lp.getEmail().sendKeys(username);
		 lp.getPassword().sendKeys(password);
		 //System.out.println(text);	//just to test restricted or unrestricted user
		 
		 log.info(text);
		 
		 lp.getLogin().click();
		 
		 ForgotPassword fp=lp.getForgotPassword();
		 
		 fp.getEmail().sendKeys("kkk");
		 fp.getSendMeInstruction().click();
		
	}
	
	@AfterTest
	public void tearDown()
	{
		objdriver.close();
	}
	
	 @DataProvider
	 public Object[][] getData()
	 {
		 //row stands for how many different data type test should run
		 //column stands for no.of value for each test
		 
		 Object[][] data = new Object[2][3];
		 
		 //0th row
		 data[0][0]="abc12@hjbn";
		 data[0][1]="qwe1234";
		 data[0][2]="Restricted user";
				 
		//1st row
		 data[1][0]="hgdf23@ewkj";
		 data[1][1]="asd8756";
		 data[1][2]="Non-Restricted user";	
		 
		 return data;
	 }

}
