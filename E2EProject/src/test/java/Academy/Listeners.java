package Academy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReporterNG;

public class Listeners extends Base implements ITestListener {

	ExtentTest test;
	
	ExtentReports objExtent=ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//use it for parallel execution...the object of thread local will store all the test values in it
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test= objExtent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//test.fail(result.getThrowable());//to get all the failures...replace test with extentTest.get()
		extentTest.get().fail(result.getThrowable());
		
		WebDriver objdriver = null;
		//perform it whenever run failure will see in the execution
		
		//1. capture the name of test which is failing //2. this time while failure ScreenShot code is written
		String testMethodName = result.getMethod().getMethodName();
		
		//to access the fields of other class
		try {
			objdriver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("objdriver").get(result.getInstance());
		} 
		catch (Exception e) {
			
		}
			
		try {
			
			//to get the screenshot attach in report
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(testMethodName,objdriver),result.getMethod().getMethodName());
			
			getScreenshotPath(testMethodName,objdriver);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //call the method that is in Base class. Hence to execute that method we have to extends that class
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test PASSED");
	}
	
	public void onFinish(ITestResult result) {
		// TODO Auto-generated method stub
		objExtent.flush();
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		objExtent.flush();
		
	}

}
