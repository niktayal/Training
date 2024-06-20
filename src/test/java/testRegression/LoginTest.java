package testRegression;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import functions.BaseClass;
import modules_Pages.LoginPageModule;

@Test
public class LoginTest {
	
	public static WebDriver driver;
	public static int sheetnumber = 0;
	LoginPageModule objLoginPage = new LoginPageModule();

	public static String result = "Fail";

	// Description : To launch the application under test

	@Parameters({ "browser", "env" })
	@BeforeMethod
	public void beforelogin(String browser, String env) throws InterruptedException, FileNotFoundException, IOException
	// public void beforelogin() throws InterruptedException, FileNotFoundException,
	 //IOException //DND For debugging

	{
		// String env = "stg"; //DND For debugging
		// System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\External_Jars\\libs\\chromedriver.exe");
		// driver = new ChromeDriver(); //DND For debugging

		BaseClass bc= new BaseClass();
		bc.environment(env);
		driver = BaseClass.SetupBrowser(browser); 
		BaseClass.wait(driver);
		BaseClass.BrowserMaximize(driver);
		BaseClass.navigateUrl(driver);
		/*
		 * if(env.contains("stg")) { BaseClass.navigateUrl(driver); } else
		 * if(env.contains("Soap")) { BaseClass.navigateSoapUrl(driver); } else
		 * if(env.contains("MySoap")) { BaseClass.navigateMySoapUrl(driver); } else {
		 * BaseClass.navigateUrlPRod(driver); } Thread.sleep(1000);
		 */

		/*
		 * String expectedTitle = "Login"; String actualTitle = driver.getTitle(); if
		 * (expectedTitle.equals(actualTitle)) { System.out.
		 * println("Verification Successful - The correct title is displayed on the web page."
		 * + actualTitle); } else { System.out.
		 * println("Verification Failed - An incorrect title is displayed on the web page."
		 * + actualTitle); }
		 */

	}

	// Description : To test login functionality with valid test data
	@Test
	public void Test1_loginValidCredential() throws InterruptedException, IOException

	{
		
		try {
			
			Thread.sleep(500); 
			
			WebDriverWait wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BaseClass.GetPropertyFileData(driver, "signin"))));
			
			
			  Assert.assertEquals(BaseClass.isEnabled(driver,
			  "xpath","signin"), true,
			  "Login button was not enabled"); Thread.sleep(500);
			 
			 
			 objLoginPage.loginEnterDataFields(driver, sheetnumber, 2); Thread.sleep(500);
			  
			  
			  Assert.assertEquals(BaseClass.Exist(driver, "xpath", "createTrips"), true,
			  " Dashboard did not verified"); Thread.sleep(500);
			  
			  result = "Pass";
			  
			  if (BaseClass.Exist(driver, "xpath", "createTrips")) { 
			  System.out.println(" User logged in successfully ");
			  } else { 
			  System.out.println("login failed"); 
			  Assert.fail(); 
			  Thread.sleep(500); 
			  BaseClass.captureScreenshot(driver,"Login_Class","Test1_loginValidCredential // passed"); 
			  Thread.sleep(500);
			  }
			 	} catch (Exception ep) {
			ep.printStackTrace();
			result = "False";
			BaseClass.captureScreenshot(driver,"Login" ,"Test1_loginValidCredential failed");
			Assert.fail();

		}
	}

	// Description : To test login functionality with invalid test data
	@Test(enabled = false)
	public void Test2_loginInValidCredential() throws IOException, InterruptedException {

		try {
			objLoginPage.loginEnterDataFields(driver, sheetnumber, 3);
			
			Assert.assertEquals(true, BaseClass.isEnabled(driver, "class", "Message"));
			result = "Pass";
			Thread.sleep(500);
			BaseClass.captureScreenshot(driver, "Login", "Test2_loginInValidCredential passed");

		} catch (Exception ep) {
			ep.printStackTrace();
			result = "Fail";
			Assert.fail();
			BaseClass.captureScreenshot(driver, " Login", "Test2_loginInValidCredential failed");
		} finally {
			System.out.println("Result successfully written on the excel sheet");
			BaseClass.Test_result(0, 2, 5, result);
		}

	}

	// Description : To logout the application in case of error handling
	@AfterMethod
	public void logOut(ITestResult result) {
		/*
		 * if (result.isSuccess()) { Reporter.log("script successfully executed", true);
		 * driver.quit(); } else { // String
		 * Filename=result.getMethod().getMethodName(); //
		 * BaseClass.getScreenshot(driver,Filename);
		 * Reporter.log("screenshot successfully taken"); driver.quit(); }
		 */
		
		driver.quit();

	}

}
