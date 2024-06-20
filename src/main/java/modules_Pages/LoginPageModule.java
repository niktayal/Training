package modules_Pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.BaseClass;

public class LoginPageModule extends BaseClass{
	
	//WebDriverWait wait = new WebDriverWait(driver, 15);
	
	//Description: To be used for fetching data of login credentials from test data file 
	//Input = data records from test data file
	//Output = NA 

	public  void loginEnterDataFields(WebDriver driver , int sheetnumber , int row) throws FileNotFoundException, IOException
		{
			try
				{
				WebDriverWait wait = new WebDriverWait(driver, 15);

				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BaseClass.GetPropertyFileData(driver, "signin"))));
					
				BaseClass.click(driver, "xpath", "signin");
				
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				String WH = tabs.get(0);
				String WH1 = tabs.get(1);
				driver.switchTo().window(WH1);
				
				System.out.println("Switched to the window");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "userid"))));
				
				System.out.println("Enter Value");
				BaseClass.enterData(driver, "userid", "xpath", BaseClass.testdata(sheetnumber, row, 0));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "next"))));
				
				BaseClass.click(driver, "xpath", "next");				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "password"))));
				
				BaseClass.enterData(driver, "password", "xpath", BaseClass.testdata(sheetnumber, row ,1));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "next"))));
				
				BaseClass.click(driver, "xpath", "next");
					
				System.out.println("The user has been logged in successfully");
				
				driver.switchTo().window(WH);
					
				}
			catch( Exception e)
			{
				e.printStackTrace();
			}
		}
	
	//Description: To be used for click on login button after fetching data of login credentials from test data file 
	//Input = NA
	//Output = NA 

	public  void loginClick(WebDriver driver) throws IOException, InterruptedException
		{
			BaseClass.click(driver, "id", "loginbutton");
			
			wait(driver);
			System.out.println("Successful Login");
			wait(driver);
			
		}
	
	
		//Description: To be used for selection of city and agency
		//Input = NA
		//Output = NA 

	public void selectCity(WebDriver driver, int sheetnumber, int row) throws FileNotFoundException, IOException, InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		 if(driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "cityDropdown"))).isDisplayed()) {
			 
			 driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "cityDropdown"))).click();
			 
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "citySelect"))));
			 driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "citySelect"))).sendKeys(BaseClass.testdata(sheetnumber, row, 2));
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "citySelect"))));
			 
			 driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "citySelect"))).sendKeys(Keys.ENTER);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "agencyDropdown"))));
			 
			 driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "agencyDropdown"))).click();
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "agencySelect"))));
			 
			 driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "agencySelect"))).sendKeys(BaseClass.testdata(sheetnumber, row, 3));
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "agencySelect"))));
			 
			 driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "agencySelect"))).sendKeys(Keys.ENTER);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "accountSubmit"))));
			 
			 
			 BaseClass.click(driver, "xpath", "accountSubmit");
			 wait(driver);
			 
			 System.out.println("The city has been selected successfully");
			 
			 
			 }
			 
			 else {
				 System.out.println("The account is of single city");
			 }
	}
	

	//Description: To be used for click on logout button after successful login  
	//Input = NA
	//Output = NA

	 public  void logOut(WebDriver driver) throws IOException, InterruptedException
		{
		 WebDriverWait wait = new WebDriverWait(driver, 15);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "logoutButton"))));
/*		 Actions actions = new Actions(driver);
		  actions.moveToElement(driver.findElement(By.xpath(BaseClass.
		  GetPropertyFileData(driver, "logoutButton")))).perform();*/
		  
		  WebElement element = driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, "logoutButton")));
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
		  executor.executeScript("arguments[0].click();", element);
		 	
/*		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "logoutButton"))));
		 BaseClass.click(driver, "xpath", "logoutButton");
		 	 */
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "logoutAccept"))));
		 BaseClass.click(driver, "xpath", "logoutAccept");
		 
		 wait(driver);
		 
		 System.out.println("The user has been logged out successfully");
		}
	
}

