package testRegression;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import functions.BaseClass;
import modules_Pages.CreateTripsModule;
import modules_Pages.LoginPageModule; 

public class CreateTrips {

	public static WebDriver driver;
	public static int sheetnumber = 0;
	public static int sheetno = 0;
	LoginPageModule objLoginPage = new LoginPageModule();
	CreateTripsModule objCreateTrips = new CreateTripsModule();

	public static String result = "Fail";

	// Description : To launch the application under test

	@Parameters({ "browser", "env" })
	@BeforeMethod
	public void beforelogin(String browser, String env) throws InterruptedException, FileNotFoundException, IOException

	{

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

	}

	// Description : To test login functionality with valid test data
	@Test
	public void Test1_createValidTrips() throws InterruptedException, IOException

	{
		
		try {
			
			Thread.sleep(500); 
			
			WebDriverWait wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BaseClass.GetPropertyFileData(driver, "signin"))));
			 
			 objLoginPage.loginEnterDataFields(driver, sheetnumber, 2); 
			 Thread.sleep(500);
			 
			 String currentDate = BaseClass.currentDate();
			 String currentTime = BaseClass.currentTime(1);
			 
			 System.out.println("Date:"+ currentDate +","+ "Time:" + currentTime );
			 
			 BaseClass.WriteCSV(sheetno,currentDate, currentTime);
			 
			 objCreateTrips.createTripsmethods(driver);
			  
			 String UploadValidations = BaseClass.ExtractData(driver, "UploadValidations", "xpath");
				
				if (UploadValidations.contains("Trip Creation Successful")) {
					System.out.println("The file uploaded successfully");
					result = "True";
				}
				
				else {
					System.out.println();
					result = "False";
					Assert.fail();
					Thread.sleep(500); 
					  BaseClass.captureScreenshot(driver,"CreateTrips","Test1_createValidTrips"); 
					  Thread.sleep(500);
				}
			
			 	} catch (Exception ep) {
			ep.printStackTrace();
			result = "False";
			  BaseClass.captureScreenshot(driver,"CreateTrips","Test1_createValidTrips"); 
			Assert.fail();

		}
	}
	
	// Description : To logout the application in case of error handling
		@AfterMethod
		public void logOut() {
			
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
