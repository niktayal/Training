package modules_Pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.BaseClass;

public class CreateTripsModule {

	public  void createTripsmethods(WebDriver driver) throws FileNotFoundException, IOException
	{
		try
			{
			
			String result;
			WebDriverWait wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BaseClass.GetPropertyFileData(driver, "createTrips"))));
				
			BaseClass.click(driver, "xpath", "createTrips");
			BaseClass.click(driver, "xpath", "fileUpload");
			//BaseClass.click(driver, "xpath", "selectFile");
			Thread.sleep(2000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "selectFile"))));
			
			/*
			 * WebElement elem = driver.findElement(By.xpath("//*[@class='wrapper']"));
			 * 
			 * WebElement fileInput =
			 * driver.findElement(By.cssSelector("input[type='file']"));
			 * 
			 * ((JavascriptExecutor)driver).
			 * executeScript("arguments[0].style.display = 'block';", fileInput);
			 * 
			 * String filePath = System.getProperty("user.dir")
			 * +"\\src\\test\\resource\\TMS Files\\Valid Data.csv";
			 * fileInput.sendKeys(filePath);
			 */
            
            //elem.click();
			
			/*
			 * JavascriptExecutor executor= (JavascriptExecutor)driver;
			 * executor.executeScript(
			 * "document.getElementByxpath('//*[@class='wrapper']').style.display='block';")
			 * ; Select select = new
			 * Select(driver.findElement(By.xpath("//*[@class='wrapper']")));
			 * select.selectByVisibleText(System.getProperty("user.dir")
			 * +"\\src\\test\\resource\\TMS Files\\Valid Data.csv"); Thread.sleep(6000);
			 */
			
			//JavascriptExecutor executor = (JavascriptExecutor)driver;

			/*
			 * String js =
			 * "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
			 * 
			 * ((JavascriptExecutor) driver).executeScript(js, elem);
			 */
			/*
			 * elem.sendKeys(System.getProperty("user.dir")
			 * +"\\src\\test\\resources\\TMS Files\\Valid Data.csv");
			 * 
			 * WebElement tmpElement =
			 * driver.findElement(By.xpath("//input[@class = 'mediaCapture']"));
			 * JavascriptExecutor executor = (JavascriptExecutor)driver;
			 * executor.executeScript("arguments[0].style.visibility", tmpElement);
			 * executor.executeScript("arguments[0].click();", tmpElement);
			 */

			
			  WebElement element =
			  driver.findElement(By.xpath("//input[@class = 'mediaCapture']")); //Actions
			  //actions = new Actions(driver);
			  
			  //actions.moveToElement(element);
			  
			  //actions.click();
			  
			  element.sendKeys(System.getProperty("user.dir")
			  +"\\src\\test\\resources\\TMS Files\\Valid Data.csv");
			 

			//actions.build().perform();
			
			/*
			 * WebElement browse = driver.findElement(By.xpath("//*[@class='wrapper']"));
			 * //click on‘Choose file’ to upload the desired file // //File uploadFile =
			 * newFile(System.getProperty("user.dir")
			 * +"\\src\\test\\resources\\TMS Files\\Valid Data.csv");
			 * browse.sendKeys(System.getProperty("user.dir")
			 * +"\\src\\test\\resources\\TMS Files\\Valid Data.csv"); //Uploading the
			 * fileusing sendKeys
			 */			  System.out.println("File is Uploaded Successfully");
			 
				
				
				/*  File uploadFile = new File(System.getProperty("user.dir")
				  +"\\src\\test\\resources\\TMS Files\\Valid Data.csv"); WebElement fileInput =
				  driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver,
				  "selectFile"))); fileInput.sendKeys(uploadFile.getAbsolutePath());
				  System.out.println("Upload the create trips file");*/
			 
			 Thread.sleep(1000);
				  
				  if(driver.findElement(By.xpath("//*[@class='cstm-button active']")).isDisplayed()) {
				  BaseClass.click(driver, "xpath", "saveFile");
				  }
				  
				  else {
					  System.out.println("Save button is not found");
				  }
				 
				  
				  Thread.sleep(2000);
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.GetPropertyFileData(driver, "saveFile"))));
			
			//BaseClass.click(driver, "xpath", "saveFile");	
				
			//System.out.println("The user has been logged in successfully");
							
			}
		catch( Exception e)
		{
			e.printStackTrace();
		}
	}
}
