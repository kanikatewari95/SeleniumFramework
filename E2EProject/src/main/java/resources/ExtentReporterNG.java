package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports objExtent;
	
	public static ExtentReports getReportObject()
	{
		System.out.println("kanika");
		//create the path file to store reports
		 String path = System.getProperty("user.dir")+"\\reports\\index.html";
		 
		 ExtentSparkReporter objReporter = new ExtentSparkReporter(path);
		 objReporter.config().setReportName("Web Automation Details");
		 objReporter.config().setDocumentTitle("Test Report");
		 
		//extent report is the one that is responsible to drive all our reporting execution
		 
		 objExtent = new ExtentReports();
		 objExtent.attachReporter(objReporter);
		 objExtent.setSystemInfo("Tester", "Kanika");
		 
		 return objExtent;
	}
	
	@Test
	public void initialDemo()
	{
		
		System.setProperty("webdriver.chrome.driver", "D:\\KANIKA\\chromedriver.exe");
		WebDriver objdriver = new ChromeDriver();
		objdriver.get("https://rahulshettyacademy.com");
		System.out.println(objdriver.getTitle());
	//	test.fail("Results do not match");
		
		
		//objExtent.flush();
	}

}
