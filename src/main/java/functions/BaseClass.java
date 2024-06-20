package functions;


	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;
	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	//import  com.aspose.cells.Workbook;     


	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.firefox.FirefoxProfile;
	import org.openqa.selenium.firefox.internal.ProfilesIni;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.safari.SafariDriver;
	import org.openqa.selenium.support.ui.Select;

import com.opencsv.CSVWriter;

import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	//import Functions.ExecutableJar; //DND For Package file
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	
	public class BaseClass {

		public static  WebDriver driver;
		public static int sheetnumber = 0;
		public static String ElementFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\resources\\Locators_Stg.properties";
		public static String ElementFilePath2 = System.getProperty("user.dir")+"\\src\\Resources\\ORProd.properties";
		public static String ElementFilePath3 = System.getProperty("user.dir")+"\\src\\Resources\\ORStgSoap.properties";
		public static String ElementFilePath4 = System.getProperty("user.dir")+"\\src\\Resources\\ORProdMySoap.properties";
		public static FileInputStream fis;
		public static String currenturl;
		//public static String  applicationUrl= "https://stg.sample-cube.com";
		public static String  applicationUrl= "https://dev-tms.chalo.com/user/login";
		public static String  applicationUrl1= "https://admin.sample-cube.com";
		//public static String applicationUrl2="https://devsaas.sample-cube.com/#/login";
		public static String applicationUrl2="https://stg.sample-cube.com"; 
		public static String  applicationUrl3= "https://sample-cube.com";
		public static String env;
		
		
		//Description: Waits for page load
		//Input= NA
		//Output= NA
		public static void wait(WebDriver driver)
			{
		
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
			}
		@SuppressWarnings("static-access")
		public  void environment(String env) 
		{
			this.env=env;
		}
		
		//Description: Reads data from OR Properties File
		//Input= Properties File
		//Output= locator values of elements
		
		public static String GetPropertyFileData(WebDriver driver,String label) throws FileNotFoundException, IOException 
		  {
//		    String path = ExecutableJar.OR;//DND For Package file
		    
		    if(env.contains("stg"))
		    {
		    Properties prop = new Properties(); 
		//  prop.load(new FileInputStream(path)); //DND For Package file
		    prop.load(new FileInputStream(ElementFilePath));
		    String FeildValue = prop.getProperty(label);
		    return FeildValue;
		    }
		    else if(env.contains("Soap"))
		    {
		    Properties prop = new Properties(); 
		//  prop.load(new FileInputStream(path)); //DND For Package file
		    prop.load(new FileInputStream(ElementFilePath3));
		    String FeildValue = prop.getProperty(label);
		    return FeildValue;
		    }else if(env.contains("MySoap"))
		    {
		    Properties prop = new Properties(); 
		//  prop.load(new FileInputStream(path)); //DND For Package file
		    prop.load(new FileInputStream(ElementFilePath4));
		    String FeildValue = prop.getProperty(label);
		    return FeildValue;
		    }
		    else
		    {
		     Properties prop = new Properties(); 
//		     prop.load(new FileInputStream(path)); //DND For Package file
		     prop.load(new FileInputStream(ElementFilePath2));
		     String FeildValue = prop.getProperty(label);
		     return FeildValue;
		    }
		  }

		//Description: Maximizes the Browser
		//Input = NA
		//Output = Maximizes the Browser
		
		public static void BrowserMaximize(WebDriver driver) throws InterruptedException 
			{
		
				Thread.sleep(500);	
				driver.manage().window().maximize();
				Thread.sleep(500);
			}

		//Description: To navigate the Application URL
		//Input = Application URL
		//Output = Displays login page of application
		
		public static void navigateUrl(WebDriver driver  )
				throws FileNotFoundException, IOException, InterruptedException 
			{
				driver.navigate().to(applicationUrl);
				Thread.sleep(500);
				currenturl =driver.getCurrentUrl();
			}
		public static void navigateSoapUrl(WebDriver driver  )
				throws FileNotFoundException, IOException, InterruptedException 
			{
				driver.navigate().to(applicationUrl2);
				Thread.sleep(500);
				currenturl =driver.getCurrentUrl();
			}
		public static void navigateMySoapUrl(WebDriver driver  )
				throws FileNotFoundException, IOException, InterruptedException 
			{
				driver.navigate().to(applicationUrl3);
				Thread.sleep(500);
				currenturl =driver.getCurrentUrl();
			}
		
		
		public static void navigateUrlPRod(WebDriver driver  )
				throws FileNotFoundException, IOException, InterruptedException 
			{
				driver.navigate().to(applicationUrl1);
				Thread.sleep(500);
				currenturl =driver.getCurrentUrl();
			}

		 
		public static WebDriver SetupBrowser( String browser ) throws IOException
		 {
			
				if(browser.equalsIgnoreCase("firefox"))
		  {
//				String path = ExecutableJar.CDE; //DND For Package file
//				System.setProperty("webdriver.chrome.driver",path); //DND For Package file
					
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\External_Jars\\libs\\geckodriver.exe");
				 System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
				   System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\logs.txt");
				FirefoxOptions Options = new FirefoxOptions();
				Options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"); //Location where Firefox is installed
				
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("moz:firefoxOptions", Options);
				ProfilesIni allProfiles = new ProfilesIni();
				FirefoxProfile ffProfile = allProfiles.getProfile("SeleniumProfile");
				capabilities.setCapability(FirefoxDriver.PROFILE, ffProfile);
				//set more capabilities as per your requirements
				
//				return new RemoteWebDriver(DesiredCapabilities.firefox());
				return new FirefoxDriver(capabilities);
				
				
		
		  }
		  else if ( browser.equalsIgnoreCase("chrome"))
		   {  
//			 String path = ExecutableJar.CDE; //DND For Package file
//			  System.setProperty("webdriver.chrome.driver",path); //DND For Package file
				
			  System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--disable-web-security");
			  //options.addArguments("--incognito");
			  options.addArguments("--no-proxy-server");
			  options.addArguments("disable-infobars");
			  //options.addArguments("headless");
			  Map<String, Object> prefs = new HashMap<String, Object>();
			  prefs.put("credentials_enable_service", false);
			  prefs.put("profile.password_manager_enabled", false);
			  options.setExperimentalOption("prefs", prefs);
			  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			  
//			 return new RemoteWebDriver(DesiredCapabilities.chrome());
			  
			  return new ChromeDriver(capabilities); 
		   }
		  else if (browser.equalsIgnoreCase("explorer"))
		  {
//			  String path = ExecutableJar.CDE; //DND For Package file
//			  System.setProperty("webdriver.chrome.driver",path); //DND For Package file 
			 
			  System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\External_Jars\\libs\\IEDriverServer.exe");
			  DesiredCapabilities  dc = DesiredCapabilities.internetExplorer();
			  dc.setJavascriptEnabled(true);
			  dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			  dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			  dc.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			  dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
			  dc.setCapability(InternetExplorerDriver.IE_SWITCHES, true);
			  dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			  dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	    	  return new InternetExplorerDriver(dc);
		  }
		  else if(browser.equalsIgnoreCase("safari"))
		  {
			  
			  //String path = ExecutableJar.CDE;//DND For Package file
			  	return new SafariDriver();
		  }
		  else if(browser.equalsIgnoreCase("incognito"))
		  {
			  // To Start the browser in incognito mode for chrome.
			  System.setProperty("webdriver.chrome.driver",".\\src\\External_Jars\\libs\\chromedriver.exe");
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--disable-web-security");
			  options.addArguments("--incognito");
			  options.addArguments("--no-proxy-server");
			  options.addArguments("disable-infobars");
			  Map<String, Object> prefs = new HashMap<String, Object>();
			  prefs.put("credentials_enable_service", false);
			  prefs.put("profile.password_manager_enabled", false);
			  options.setExperimentalOption("prefs", prefs);
			  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			  return new ChromeDriver(capabilities);			  
		  }
		  else
		  {
		    
//			 String path = ExecutableJar.CDE;//DND For Package file
//			 System.setProperty("webdriver.chrome.driver",path); //DND For Package file
			 
			 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\External_Jars\\libs\\chromedriver.exe");
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--disable-web-security");
			  //options.addArguments("--incognito");
			  options.addArguments("--no-proxy-server");
			  Map<String, Object> prefs = new HashMap<String, Object>();
			  prefs.put("credentials_enable_service", false);
			  prefs.put("profile.password_manager_enabled", false);
			  options.setExperimentalOption("prefs", prefs);
			  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			  return new ChromeDriver(capabilities);
		  }
		 }
		
		//Description: To verify if the element is enabled
		//Input = location of WebElement
		//Output = true if enabled, false if disabled
		
		public static boolean isEnabled(WebDriver driver, String LocatorType, String Element) throws IOException
		
			{
				boolean temp = false;
				try {
					String locator = GetPropertyFileData(driver ,Element);
		
					if (LocatorType.equalsIgnoreCase("xpath")) {
		
						temp = driver.findElement(By.xpath(locator)).isEnabled();
					} else if (LocatorType.equalsIgnoreCase("css")) {
		
						temp = driver.findElement(By.cssSelector(locator)).isEnabled();
					} else if (LocatorType.equalsIgnoreCase("id")) {
		
						temp = driver.findElement(By.id(locator)).isEnabled();
					} else if (LocatorType.equalsIgnoreCase("class")) {
		
						temp = driver.findElement(By.className(locator)).isEnabled();
					} else {
						System.out.println("invalid selector");
					}
		
				} catch (Exception e) {
					e.printStackTrace();
			}
		
			return temp;
		}

		public static boolean isSelected(WebDriver driver, String LocatorType, String Element) throws IOException
		
		{
			boolean temp = false;
			try {
				String locator = GetPropertyFileData(driver ,Element);

				if (LocatorType.equalsIgnoreCase("xpath")) {

					temp = driver.findElement(By.xpath(locator)).isSelected();
				} else if (LocatorType.equalsIgnoreCase("css")) {

					temp = driver.findElement(By.cssSelector(locator)).isSelected();
				} else if (LocatorType.equalsIgnoreCase("id")) {

					temp = driver.findElement(By.id(locator)).isSelected();
				} else {
					System.out.println("invalid selector");
				}

			} catch (Exception e) {
				e.printStackTrace();
		}

		return temp;
	}
		
		
		//Description: To enter data into the TextBox
		//Input = value to be entered
		//Output = writes data into the TextBox
		
		public static void enterData(WebDriver driver, String TextboxName, String LocatorType, String Value)
				throws IOException {
			try {
				String locator = GetPropertyFileData(driver, TextboxName);

				if (LocatorType.equalsIgnoreCase("xpath")) {

					driver.findElement(By.xpath(locator)).clear();
					Thread.sleep(1000);
					driver.findElement(By.xpath(locator)).sendKeys(Value);
				} 
				
				else if (LocatorType.equalsIgnoreCase("css")) {

					driver.findElement(By.cssSelector(locator)).clear();
					Thread.sleep(500);
					driver.findElement(By.cssSelector(locator)).sendKeys(Value);
				} 
				
				else if (LocatorType.equalsIgnoreCase("id")) {

					driver.findElement(By.id(locator)).clear();
					Thread.sleep(500);
					driver.findElement(By.id(locator)).sendKeys(Value);
				} 
				
				else if (LocatorType.equalsIgnoreCase("name")) {

					driver.findElement(By.name(locator)).clear();
					Thread.sleep(500);
					driver.findElement(By.name(locator)).sendKeys(Value);
				} 
				
				else if (LocatorType.equalsIgnoreCase("class")) {

					driver.findElement(By.name(locator)).clear();
					Thread.sleep(500);
					driver.findElement(By.name(locator)).sendKeys(Value);
				} 
				else {
					System.out.println("invalid selector");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//Description: To extract data 
		//Input = locator of WebElemet
		//Output = reads the text present in the WebElement
		public static String ExtractData(WebDriver driver, String TextboxName, String LocatorType) throws IOException
		{
			String temp = null;
			try {
				String locator = GetPropertyFileData(driver, TextboxName);

				if (LocatorType.equalsIgnoreCase("xpath")) {

					temp = driver.findElement(By.xpath(locator)).getText();

				} else if (LocatorType.equalsIgnoreCase("css")) {

					temp = driver.findElement(By.cssSelector(locator)).getText();

				} else if (LocatorType.equalsIgnoreCase("id")) {

					temp = driver.findElement(By.id(locator)).getText();

				} else {
					System.out.println("invalid selector");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return temp;
		}

		
		//Description: To read current Date and Time
		//Input = NA
		//Output = current Date and Time
		
		public static String currentDateTime()
		
			{
				Calendar currentDate = Calendar.getInstance(); // gets current date instance
																
				SimpleDateFormat formatter = new SimpleDateFormat("dd-mmm-yyyy HH:mm:ss");
				String dateNow = formatter.format(currentDate.getTime());
				return dateNow;
			}
		
		
		//Description: To read current Date
				//Input = NA
				//Output = current Date
				
				public static String currentDate()
				
					{
						Calendar currentDate = Calendar.getInstance(); // gets current date instance
																		
						//SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
						String dateNow = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
						//String dateNow = formatter.format(currentDate.getTime());
						//return dateNow;
						return dateNow;
					}
				
				
				//Description: To read current Time
				//Input = NA
				//Output = current Time
				
				public static String currentTime(int update)
				
					{
						Calendar currentTime = Calendar.getInstance(); // gets current time instance
																		
						SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
						currentTime.add(Calendar.HOUR, update);
						String timeNow = formatter.format(currentTime.getTime());
						return timeNow;
					}
				
		
		//Description: To select data in DropDown List by index
		//Input = locator of DropDown List  
		//Output = selects the particular option of DropDown List 
		
		public static void SelectDDLByIndex(WebDriver driver, String DropDownGroup, String LocatorType, int index)
				throws IOException, InterruptedException {
			try {
				String locator = GetPropertyFileData(driver, DropDownGroup);

				if (LocatorType.equalsIgnoreCase("xpath")) {

					Select DDL = new Select(driver.findElement(By.xpath(locator)));
					Thread.sleep(500);
					DDL.selectByIndex(index);
				} else if (LocatorType.equalsIgnoreCase("css")) {

					Select DDL = new Select(driver.findElement(By.cssSelector(locator)));
					Thread.sleep(500);
					DDL.selectByIndex(index);
				} else if (LocatorType.equalsIgnoreCase("id")) {

					Select DDL = new Select(driver.findElement(By.id(locator)));
					Thread.sleep(500);
					DDL.selectByIndex(index);
				} else {
					System.out.println("invalid selector");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		//Description: To select data in DropDown List by visible text
		//Input = locator of DropDown List  
		//Output = selects the particular option of DropDown List 
		
		public static void SelectDDLByVisibleText(WebDriver driver, String DropDownGroup, String LocatorType, String value)
				throws IOException, InterruptedException
		{

			try {
				String locator = GetPropertyFileData(driver, DropDownGroup);

				if (LocatorType.equalsIgnoreCase("xpath")) {

					Select DDL = new Select(driver.findElement(By.xpath(locator)));
					Thread.sleep(500);
					DDL.selectByVisibleText(value);
				} else if (LocatorType.equalsIgnoreCase("css")) {

					Select DDL = new Select(driver.findElement(By.cssSelector(locator)));
					Thread.sleep(500);
					DDL.selectByVisibleText(value);
				} else if (LocatorType.equalsIgnoreCase("id")) {

					Select DDL = new Select(driver.findElement(By.id(locator)));
					Thread.sleep(500);
					DDL.selectByVisibleText(value);
				} else {
					System.out.println("invalid selector");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		//Description: To accept the alert pop up
		//Input = alert pop-up 
		//Output = accepts alert
		
		public static void Alertaccept(WebDriver driver) throws IOException, InterruptedException
			{
				Alert A1 = driver.switchTo().alert();
				A1.accept();
				Thread.sleep(500);
			}
		
		
		//Description: To dismiss the alert pop up
		//Input = alert pop-up 
		//Output = dismisses alert
		
		public static void Alertdismiss(WebDriver driver) throws IOException, InterruptedException
			{
				 WebElement Alert = driver.findElement(By.className("modal-content"));
				 List<WebElement> buttons= Alert.findElements(By.tagName("button"));
				 for ( WebElement ele1 : buttons)
				 {
					 if(ele1.getText().equalsIgnoreCase("no"))
						 {
						 ele1.click();
						 break;
						 }
				 }
				 
				//click(driver,"xpath" , "NoButton");
				Thread.sleep(1000);
			}


		//Description: To select the check-box
		//Input = locator of check-box
		//Output = selected check-box
		
		public static void SelectCheckBox(WebDriver driver, String checkboxgroup, String LocatorType, String checkboxname)
				throws FileNotFoundException, IOException
		{
				List<WebElement> CheckBoxList = null;
				try {
					String locator = GetPropertyFileData(driver, checkboxgroup);
		
					if (LocatorType.equalsIgnoreCase("xpath")) {
		
						CheckBoxList = driver.findElements(By.xpath(locator));
		
					} else if (LocatorType.equalsIgnoreCase("css")) {
		
						CheckBoxList = driver.findElements(By.cssSelector(locator));
		
					} else if (LocatorType.equalsIgnoreCase("id")) {
		
						CheckBoxList = driver.findElements(By.id(locator));
		
					} else {
						System.out.println("invalid selector");
					}
		
					for (int i = 0; i < CheckBoxList.size(); i++) {
						if (CheckBoxList.get(i).getAttribute("value").equals(checkboxname)) {
							CheckBoxList.get(i).click();
						}
					}
		
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

		
		//Description: To select the radio button
		//Input = locator of radio button
		//Output = selected radio button
		
		public static void SelectRadioButton(WebDriver driver, String Radiogroup, String LocatorType, String Radioname)
				throws FileNotFoundException, IOException 
		{
			List<WebElement> RadioList = null;
			try {
				String locator = GetPropertyFileData(driver, Radiogroup);

				if (LocatorType.equalsIgnoreCase("xpath")) {

					RadioList = driver.findElements(By.xpath(locator));

				} else if (LocatorType.equalsIgnoreCase("css")) {

					RadioList = driver.findElements(By.cssSelector(locator));

				} else if (LocatorType.equalsIgnoreCase("id")) {

					RadioList = driver.findElements(By.id(locator));

				} else {
					System.out.println("invalid selector");
				}

				for (int i = 0; i < RadioList.size(); i++) {
					if (RadioList.get(i).getAttribute("value").equals(Radioname)) {
						RadioList.get(i).click();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		//Description: To click the WebElement
		//Input = locator of WebElement
		//Output = WebElement is clicked
		
		public static void click(WebDriver driver, String LocatorType, String buttonname) throws IOException 
		{
			try {
				String locator = GetPropertyFileData(driver, buttonname);

				if (LocatorType.equalsIgnoreCase("xpath"))
				{

					driver.findElement(By.xpath(locator)).click();
				}
				
				else if (LocatorType.equalsIgnoreCase("css")) 
				{

					driver.findElement(By.cssSelector(locator)).click();
				}
				
				else if (LocatorType.equalsIgnoreCase("id"))
				{

					driver.findElement(By.id(locator)).click();
				}
				else if (LocatorType.equalsIgnoreCase("class"))
				{

					driver.findElement(By.className(locator)).click();
				}
				else 
				{
					System.out.println("invalid selector");
				}

			} 
			
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

		//Description: To verify if the WebElement exists or not
		//Input = locator of WebElement
		//Output = true if exists, false if doesn't exist
		
		public static boolean Exist(WebDriver driver, String LocatorType, String Element) throws IOException

		{
			boolean temp = false;
			try {
				String locator = GetPropertyFileData(driver, Element);

				if (LocatorType.equalsIgnoreCase("xpath")) {

					temp = driver.findElement(By.xpath(locator)).isDisplayed();
				} else if (LocatorType.equalsIgnoreCase("css")) {

					temp = driver.findElement(By.cssSelector(locator)).isDisplayed();
				} else if (LocatorType.equalsIgnoreCase("id")) {

					temp = driver.findElement(By.id(locator)).isDisplayed();
				}else if (LocatorType.equalsIgnoreCase("class")) {

					temp = driver.findElement(By.className(locator)).isDisplayed();
				} else if (LocatorType.equalsIgnoreCase("link")) {

					temp = driver.findElement(By.linkText(locator)).isDisplayed();
				}
				else {
					System.out.println("invalid selector");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return temp;
		}
		
		//Description: To select date from calendar
		//Input = NA
		//Output = selects date from calendar
		
		public static void SelectDateFromCalender(WebDriver driver, String LocatorType, String Calender, String Date)
				throws IOException
		{
			int datetemp = 0;
			Boolean check = false;
			String locator = GetPropertyFileData(driver, Calender);
			WebElement dateWidget = driver.findElement(By.id(locator));
			List<WebElement> rows = dateWidget.findElements(By.tagName("tr"));
			for (WebElement row : rows) {
				// List<WebElement>
				// columns=dateWidget.findElements(By.tagName("td"));
				List<WebElement> columns = row.findElements(By.tagName("td"));
				for (WebElement cell : columns) {

					if (cell.getText().equals(Date)) {
						check = cell.findElement(By.linkText(Date)).isEnabled();
						if (check.equals(true)) {
							cell.findElement(By.linkText(Date)).click();
							datetemp = 1;
							break;
						}
					}

				}

				if (datetemp == 1) {
					break;
				}
			}

		}

		//Description: To close the browser
		
		public static void closeBrowser(WebDriver driver)
			{
				driver.close();
		
			}
		
		//Description: To verify the data in the table
		//Input = locator of table
		//Output = true if data exists in table else false
		
		public static Boolean verifyData(WebDriver driver, String tableloc, String element) throws FileNotFoundException, IOException 
		{
			String locator = GetPropertyFileData(driver, tableloc);
			WebElement Table = driver.findElement(By.xpath( locator));
			List<WebElement> options = Table.findElements(By.tagName("td"));
		
			for (int i = 0; i < options.size();i++) 
				{
		
					if (options.get(i).getText().equalsIgnoreCase(element))
						{
					
							return true;
						}
				
				}
				return false;
			}
		
		//Description: To click specific cell value in the table
		//Input = locator of table
		//Output = true if data exists in table else false
		public static void selectTableData(WebDriver driver, String tableloc, String element) throws FileNotFoundException, IOException, InterruptedException 
		{
			String locator = GetPropertyFileData(driver, tableloc);
			WebElement Table = driver.findElement(By.xpath( locator));
			List<WebElement> options = Table.findElements(By.tagName("td"));
		
			for (int i = 0; i < options.size();i++) 
				{
		
					if (options.get(i).getText().equalsIgnoreCase(element))
						{
					
							options.get(i).click();
						}
				
				}
			Thread.sleep(1000);
				
			}
		
		//Description: To capture screenshot 
		//Input = name -> Folder name, Test -> TestName
		//Output = screenshot in the form of .png file
		
		public static void captureScreenshot (WebDriver driver , String name , String Test) throws IOException, InterruptedException 

	    {
			Thread.sleep(500);
			File scrnsht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);    
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-HH-mm-ss-SS");
			Date date = new Date();
			String Time = dateFormat.format(date);
			FileUtils.copyFile(scrnsht, new File(System.getProperty("user.dir")+"\\src\\Test_Results\\"+name +"\\"+ Test+"\\"+Time+"testing.png"));
			//FileUtils.copyFile(scrnsht, new File("..\\..\\" +"\\"+"\\Desktop\\Test_Results"+"\\"+name+"\\"+ Test+"\\"+Time+"testing.png")); // DND for Package file only
	        
	    }
		
		
		// Description: To traverse the table
		//Input = Locator of the table
		//Output = clicks on required element
		
		public static void traverseTable(WebDriver driver, String tablelocator, String element, String elementlocator)
				throws IOException, InterruptedException
		{
		
			try {
				String tableid = GetPropertyFileData(driver, tablelocator);
				WebElement Webtable = driver.findElement(By.xpath(tableid));
				List<WebElement> rows = Webtable.findElements(By.tagName("tr"));
				for (WebElement row : rows) {
					List<WebElement> columns = row.findElements(By.tagName("td"));
					for (WebElement cell : columns) {
						String coldata = cell.getText().toString();

						if (coldata.equalsIgnoreCase(element)) {
							Thread.sleep(500);
							cell.click();
							break;
						}
					}

				}
				
			} catch (Exception e) {

			}

			// return temp ;
		}


		//Description: To write the test result to excel sheet
		//Input = row and column of excel sheet
		//Output = writes data to excel sheet
		
		public static void Test_result( int sheetno ,int row ,int coloumn , String output ) throws IOException ,FileNotFoundException
			{
				int i = row ;
				File file = new File(System.getProperty("user.dir")+"\\testOutputResult\\Automation Test Result.xlsx");
//				File file = new File("C:\\NewUIAutomationTrunk\\AutomationTests_SampleCube_NEWUI\\testOutputResult\\Automation Test Result.xlsx");
				FileInputStream filestream = new FileInputStream (file);
				XSSFWorkbook workbook = new XSSFWorkbook(filestream);
				XSSFSheet sht = workbook.getSheetAt(sheetno);
					
			try
				{
					XSSFCell cell = sht.getRow(i).createCell(coloumn);
					cell.setCellValue(output);
					filestream.close();
					     
					FileOutputStream outFile =new FileOutputStream(new File(System.getProperty("user.dir")+"\\testOutputResult\\\\Automation Test Result.xlsx"));
//					FileOutputStream outFile=new FileOutputStream(new File("C:\\NewUIAutomationTrunk\\AutomationTests_SampleCube_NEWUI\\testOutputResult\\Automation Test Result.xlsx"));
					workbook.write(outFile);
					outFile.close();

					  
				}
				catch (Exception e)
				{
					e.printStackTrace();	
					System.out.println("file not found");
				}
			workbook.close();
	}

	//Description: To read the data from excel sheet
	//Input = Row and column of excel sheet
	//Output = Reads data from excel sheet
		
		public static String testdata( int sheetno ,int row ,int coloumn) throws IOException ,FileNotFoundException
		  {
		   int i = row ;
		   String value = null ;
		   File file = null;
		// String path = ExecutableJar.testdata1; //DND For Package file
				   
		   
		   if( env.contains("stg"))
		   {
			  file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\test_Data\\testData.xlsx");
//			 file = new File (path); //DND For Package file
		   }
			else if(env.contains("Soap"))
		    {
			   file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataStgSoap.xlsx");
//				 file = new File (path); //DND For Package file
		    }
			else if(env.contains("MySoap"))
		    {
			   file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProdMySoap.xlsx");
//				 file = new File (path); //DND For Package file
		    }
		   else
		   {
			  file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProd.xlsx");
			// file = new File (path); //DND For Package file
		   }
		   FileInputStream filestream = new FileInputStream (file);
		   XSSFWorkbook workbook = new XSSFWorkbook(filestream);
		   XSSFSheet sheet = workbook.getSheetAt(sheetno);


			   System.out.println("This is a .XlSx file");
		       
		    try
		     {
		      XSSFCell cell = sheet.getRow(i).getCell(coloumn);
		      value =  cell.toString();
		       
		         
		     }
		    catch(Exception e)
		     {
		      e.printStackTrace();
		     }

		  workbook.close();  
		  return value;
		 }
		

		
	//Description: To find row count in excel sheet
	//Input = sheet no. of excel sheet
	//Output = count of rows in excel sheet
		
		public static int findrowscount( int sheetno ) throws IOException ,FileNotFoundException
		{
			int count = 0 ;
			 File file1 = null;
			try 
			{
				 if( env.contains("stg"))
				   {
				      file1 = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataStg.xlsx");
				   }
				 else if(env.contains("Soap"))
				    {
					   file1 = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataStgSoap.xlsx");
				    }
				 else if(env.contains("MySoap"))
				    {
					   file1 = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProdMySoap.xlsx");
				    }
				   else
				   {
				     file1 = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProd.xlsx");
				   }
				 Thread.sleep(1000);
				Workbook wb= WorkbookFactory.create(new FileInputStream(file1));		
				count = wb.getSheetAt(sheetno).getLastRowNum();
				wb.close();			
				}
				catch(Exception e)
				{
					System.out.println("The last row count has been failed and it is in catch field ");
					e.printStackTrace();
				}
				return count;		
			}
		/*
		 * the ChangeDownloadLocation method will change the location of download file to D drive 
		 * this method is applicable for the chrome and firefox browser.
		 */
		public static void ChangeDownloadLocation(WebDriver driver, String browser) throws InterruptedException
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
			driver.get("about:preferences");
			Thread.sleep(2000);
				try {
				Robot robot = new Robot();
				Thread.sleep(1000);
				robot.mouseWheel(20);
				Thread.sleep(2000);
				} 
				catch (AWTException e1) 
				{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			driver.findElement(By.id("chooseFolder")).click();
			try {
				Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\External_Jars\\libs\\DownloadChangeLoc.exe");
				Thread.sleep(2000);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
			else if(browser.equalsIgnoreCase("chrome")) 
			{
				driver.get("chrome://settings/?search=change");
				Robot robot;
				try {
					robot = new Robot();
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(500);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(500);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(500);
				} catch (AWTException e1) {
					e1.printStackTrace();}
			try {
				Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\External_Jars\\libs\\ChromeChangeLocDownload.exe");
				Thread.sleep(500);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			}
			
			// for any browser
			else 
			{
				driver.get("chrome://settings/?search=change");
				Robot robot;
				try {
					robot = new Robot();
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(500);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(500);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(500);
				} catch (AWTException e1) {
					e1.printStackTrace();}
			try {
				Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\External_Jars\\libs\\ChromeChangeLocDownload.exe");
				Thread.sleep(500);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			}
		
		}
		
	/*	public static void getScreenshot(WebDriver driver, String Filename) {
			
			EventFiringWebDriver efw=new EventFiringWebDriver(driver);
			File scrFile=efw.getScreenshotAs(OutputType.FILE);
			File desFile=new File ("C:\\NewUIAutomationTrunk\\AutomationTests_SampleCube_NEWUI\\screenshots"+Filename+".png");
			try {
				FileUtils.copyFile(scrFile, desFile);
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}  */
		
		public static void testReport(int rowCount,String result) throws FileNotFoundException, IOException
		{
			if( env.contains("stg"))
			   {
			   BaseClass.Test_result(0, rowCount,5, result);
			   System.out.println("The Result is successfully written in the excel sheet");
			   }
			else if(env.contains("Soap"))
				{
					 BaseClass.Test_result(2, rowCount,5, result);
					 System.out.println("The Result is successfully written in the Soap Excel Sheet");
				}
			else if(env.contains("MySoap"))
			{
				 BaseClass.Test_result(3, rowCount,5, result);
				 System.out.println("The Result is successfully written in the MySoap Excel Sheet");
			}
			else {
				BaseClass.Test_result(1, rowCount,5, result);
				System.out.println("The Result is successfully written in the excel sheet");
			}
			
		}
		
		public static void testTimeReport(int rowCount,long startTime,long endTime) throws FileNotFoundException, IOException
		{
			long executionTime = endTime-startTime;
			String execTime = Long.toString(executionTime);
			if( env.contains("stg"))
			   {
			   BaseClass.Test_result(0, rowCount,6, execTime);
			   System.out.println("The Result is successfully written in the Stg Excel Sheet");
			   }
			else if(env.contains("Soap")) 
			{
				 BaseClass.Test_result(2, rowCount,6, execTime);
				 System.out.println("The Result is successfully written in the Soap Excel Sheet");
			}
			else if(env.contains("MySoap")) 
			{
				 BaseClass.Test_result(3, rowCount,6, execTime);
				 System.out.println("The Result is successfully written in the MySoap Excel Sheet");
			}
			else {
				BaseClass.Test_result(1, rowCount,6, execTime);
				System.out.println("The Result is successfully written in the Prod Excel Sheet");
			}
			
		}
		
	/**
	 * 
	 * @param driver
	 * @param LocatorType is used to select the type of locator
	 * @param label is used to select the attribute value from the property file
	 * @param visualText is used which we need to select from the Drop Down
	 * @throws Exception
	 */
		/*
		public static void selectFromDropDown(WebDriver driver, String LocatorType, String label,String visualText) throws Exception 
		{
			try {
			
				if (LocatorType.equalsIgnoreCase("xpath"))
				{
					WebElement dropDown = driver.findElement(By.xpath(BaseClass.GetPropertyFileData(driver, label)));
					Thread.sleep(500);
					Select sel = new Select(dropDown);
					Thread.sleep(500);
					sel.selectByVisibleText(visualText);
					
				}
				
				else if (LocatorType.equalsIgnoreCase("id"))
				{
					WebElement dropDown = driver.findElement(By.id(BaseClass.GetPropertyFileData(driver, label)));
					Thread.sleep(500);
					Select sel = new Select(dropDown);
					Thread.sleep(500);
					sel.selectByVisibleText(visualText);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}*/
		//Description: To write the test result to excel sheet
			//Input = row and column of excel sheet
			//Output = writes data to excel sheet
			
			public static void WriteIntoExcelSheet( int sheetno ,int row ,int coloumn , String output ) throws IOException ,FileNotFoundException
				{
					/*
					 * Workbook workbook = new Workbook("input.csv"); workbook.save("Output.xlsx");
					 */
					   int i = row ;
					   File file = null;
					   FileOutputStream outFile=null;
					   if( env.contains("stg"))
					   {
						  file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TMS Files\\Valid Data.csv");
					   }
					   else if(env.contains("Soap")) 
					   {
						   file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataStgSoap.xlsx");
					   }
					   else if(env.contains("MySoap")) 
					   {
						   file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProdMySoap.xlsx");
					   }
					   else
					   {
						  file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProd.xlsx");
					   }
				
					FileInputStream filestream = new FileInputStream (file);
					XSSFWorkbook workbook = new XSSFWorkbook(filestream);
					XSSFSheet sht = workbook.getSheetAt(sheetno);
						
				try
					{
						XSSFCell cell = sht.getRow(i).createCell(coloumn);
						cell.setCellValue(output);
						filestream.close();
						     
						if( env.contains("stg"))
						   {
							outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TMS Files\\Valid Data.csv"));
						   }
						else if(env.contains("Soap")) 
						   {
							   outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataStgSoap.xlsx"));
						   }
						else if(env.contains("MySoap")) 
						   {
							   outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProdMySoap.xlsx"));
						   }
						   else
						   {
							  outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProd.xlsx"));
						   }
						
						workbook.write(outFile);
						outFile.close();
						  
					}
					catch (Exception e)
					{
						e.printStackTrace();	
						System.out.println("file not found");
					}
				workbook.close();	
		}

			//Description: To write the test result to excel sheet
			//Input = row and column of excel sheet
			//Output = writes data to excel sheet
			
			public static void WriteIntoCSVSheet( int sheetno ,int row ,int coloumn , String output ) throws IOException ,FileNotFoundException
				{
					/*
					 * Workbook workbook = new Workbook("input.csv"); workbook.save("Output.xlsx");
					 */
					   int i = row ;
					   File file = null;
					   FileOutputStream outFile=null;
					   if( env.contains("stg"))
					   {
						  file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TMS Files\\Valid Data.csv");
					   }
					   else if(env.contains("Soap")) 
					   {
						   file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataStgSoap.xlsx");
					   }
					   else if(env.contains("MySoap")) 
					   {
						   file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProdMySoap.xlsx");
					   }
					   else
					   {
						  file = new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProd.xlsx");
					   }
				
					FileInputStream filestream = new FileInputStream (file);
					XSSFWorkbook workbook = new XSSFWorkbook(filestream);
					XSSFSheet sht = workbook.getSheetAt(sheetno);
						
				try
					{
						XSSFCell cell = sht.getRow(i).createCell(coloumn);
						cell.setCellValue(output);
						filestream.close();
						
						//csvCell cell = sht.getRow(i).createCell(coloumn);
						     
						if( env.contains("stg"))
						   {
							outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TMS Files\\Valid Data.csv"));
						   }
						else if(env.contains("Soap")) 
						   {
							   outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataStgSoap.xlsx"));
						   }
						else if(env.contains("MySoap")) 
						   {
							   outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProdMySoap.xlsx"));
						   }
						   else
						   {
							  outFile=new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\Test_Data\\testDataProd.xlsx"));
						   }
						
						workbook.write(outFile);
						outFile.close();
						  
					}
					catch (Exception e)
					{
						e.printStackTrace();	
						System.out.println("file not found");
					}
				workbook.close();	
		}
				public static void WriteCSV (int sheetno ,String tripDate ,String tripTime) throws IOException {
				      //Instantiating the CSVWriter class
				      CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\test\\resources\\TMS Files\\Valid Data.csv"));
				      //Writing data to a csv file
				      String line1[] = {"RouteID", "Trip_Date (dd/mm/yyyy)", "Trip_Start_Time (24hr format)", "Trip Type (0 or 2)", "Max Vehicle Capacity", "Pre-Booking window days", "Pre-Booking window start-time (24hr format)", "Pre-Booking window closure (mins)", "Stop Arrival Times"};
				      String line2[] = {"nSxOUcTW", tripDate, tripTime, "0", "20", "2", "0:00", "10"};
				      
				      writer.writeNext(line1);
				      writer.writeNext(line2);
				      //Flushing data from writer to file
				      writer.flush();
				      System.out.println("Data entered");
				   }
	}

