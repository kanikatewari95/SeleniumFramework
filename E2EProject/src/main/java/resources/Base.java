package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	
	public WebDriver objdriver; // define the object globally so that for different only 1 object is created
	
	public Properties prop; //defined globally so that we can easily use it on other classes wherever required
	
	public WebDriver initializeDriver() throws IOException
	{	
		
	//to initialize the browser since the browser is not defined hence we take it in data.properties file
	prop = new Properties();
	
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties"); //either this or below one ..both are same
//	FileInputStream fis = new FileInputStream("D:\\KANIKA\\Workspace\\E2EProject\\src\\main\\java\\resources\\data.properties"); // path of data.properties
	//providing wrong path will give File not found error
	
	prop.load(fis);
	
	//*********************** when we use prop.getProperty...use mvn test
	//String browserName = prop.getProperty("browser");
	//System.out.println("browser in use is : " + browserName);
	
	//when we want to take the browser valur from maven command and not by our code for business purpose
	String browserName = System.getProperty("browser");//for this use command mvn test -Dbrowser=chrome
	System.out.println("browser in use is : " + browserName);
	
	//if (browserName == "chrome") , it will give error hence use the below mentioned one as (browserName.equals("chrome"))
	if (browserName.contains("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "D:\\KANIKA\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
		objdriver = new ChromeDriver(options);
	}
	
	else if (browserName.equals("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "D:\\KANIKA\\geckodriver.exe");
		objdriver = new FirefoxDriver();
	}
	
	else if(browserName.equals("IE"))
	{
		System.setProperty("webdriver.ie.driver", "D:\\KANIKA\\IEDriverServer.exe");
		objdriver = new InternetExplorerDriver();
	}
	
	objdriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); // define that it should wait for 10 seconds before giving fail result
	return objdriver;
		
	}
	
	public String getScreenshotPath(String testCaseName, WebDriver objdriver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) objdriver;
		File source=ts.getScreenshotAs(OutputType.FILE); //where screenshot of failed test will be captured...this source file will be at a virtual place
		
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";  //user.dir will give the current Maven project path
		FileUtils.copyFile(source, new File(destinationFile));
		
		return destinationFile;
	
	}
}
