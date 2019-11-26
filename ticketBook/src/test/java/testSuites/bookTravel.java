package testSuites;

import org.testng.annotations.Test;

import com.mongodb.MapReduceCommand.OutputType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@SuppressWarnings("unused")
public class bookTravel {
	public static WebDriver driver = null;
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	  
//	  ChromeOptions options  = new ChromeOptions();
//	    //options.addArguments("incognito");
//	    options.addArguments("--disable-popup-blocking");
	    
      driver = new ChromeDriver();
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  @Test(priority=0)
  //Check page opens rightly
  public void login() {
	  String myTitle = "CheapAir";
	  
	 // implicit wait to load the page
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	  
	  //maximize the Browser 
	  driver.manage().window().maximize();
	  driver.get("https://www.cheapair.com/");
	  String title = driver.getTitle();
	  Assert.assertTrue(title.contains(myTitle));
  }
  
  @Test(priority=1)
  public void searchFlights() throws InterruptedException
  {
	  List<WebElement> triptype = driver.findElements(By.xpath("//*[@class='tgl-btn']"));
	  
	  //Select one way
	  triptype.get(1).click();
	  
	  //From location
	  WebElement fromPlace = driver.findElement(By.id("from1"));
	  fromPlace.clear();
	  //fromPlace.sendKeys("Madras, India");
	  fromPlace.sendKeys("Madras");
	  Thread.sleep(3000);
	  fromPlace.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	  
	  //To location
	  WebElement toPlace = driver.findElement(By.id("to1"));
	  toPlace.clear();
//	  toPlace.sendKeys("Trivandrum, India");
	  toPlace.sendKeys("Trivandrum");
	  Thread.sleep(3000);
	  toPlace.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	  Thread.sleep(3000);
	  
	  //Select date
//	  driver.findElement(By.xpath("//*[text()='Select Date']")).click();
//	  driver.findElement(By.cssSelector("div#dates")).click();

//	  driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	  
//	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
//	  //Get current date
//	  LocalDate today = LocalDate.now();
//	  int currentD= today.getDayOfMonth();
////	  
//	  //Select day after tomorrow
//	  int selectD = currentD+2;
//	  String xPathDate = "//a[@class='ui-state-default'])["+selectD+"])";
//	  String xPathDate = "(//a[@href='#' and text()='"+selectD+"'])";
//	  String xPathDate = "(//td[@data-year='2019']//a)["+selectD+"'])";
//	  WebElement date2 = wait.until(
//		        ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathDate)));
//	  driver.findElement(By.xpath(xPathDate)).click();
	  
//	  driver.findElement(By.xpath("(//td[@data-year='2019']//a)[2]")).click();
	 driver.findElement(By.xpath("(//a[@class='ui-state-default'])[2]")).click();
	  
	  //Set Number of adults, senior and children
	  //Setting Adults as 3
	  WebElement adult = driver.findElement(By.id("adults"));
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
	  js.executeScript(scriptSetAttrValue, adult, "value", 3);
	  driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	  
	  //Setting Seniors as 2
	  WebElement senior = driver.findElement(By.id("seniors"));
	  scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
	  js.executeScript(scriptSetAttrValue, senior, "value", 2);
	  
	  //Setting children as 1
	  WebElement children = driver.findElement(By.id("children"));
	  scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
	  js.executeScript(scriptSetAttrValue, children, "value", 1);
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	  
	  //Click Search flights
	  //driver.findElement(By.xpath("//button[text()='Search Flights']")).click();
	  driver.findElement(By.xpath("//button[contains(@class,'btn large')]")).click();
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	  
	 //get window handlers as list
	  List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
	  //switch to new tab
	  driver.switchTo().window(browserTabs .get(1));
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	  
	  //handle popup
	  driver.findElement(By.xpath("//*[text()='No Thanks']")).click();
	  
//	  //Handle alert
//	  Alert alertCancel = driver.switchTo().alert();
//	  alertCancel.dismiss();
//	  
	  //Check flight results opened
	  String title = driver.getTitle();
	  Assert.assertTrue(title.contains("Flight Results"));
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	  
//	  //Take a screenshot
//	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	  FileUtils.copyFile(scrFile, new File("C:\\Users\\CSS\\Desktop\\screenshot.png"));
  }
  
  @Test(priority=2)
  public void selectTickets() throws InterruptedException, IOException
  {
	  List<Double> price = new ArrayList<Double>();
	  List<WebElement> findElements = driver.findElements(By.xpath("//div[@data-fare-amount]"));
	  for(WebElement element : findElements)
	  {
		  String s = element.getAttribute("data-fare-amount");
		  Double d = Double.valueOf(s);
		  price.add(d);
	  }
	  
	  Collections.sort(price);
	  System.out.println("Available prices of flights available (in $) :"+price);
	  
	  //Click cheapest element
	  Double cheapest = price.get(0);
	  for(WebElement element : findElements)
	  {
		  String s2 = element.getAttribute("data-fare-amount");
		  Double d2 = Double.valueOf(s2);
		  if(d2.equals(cheapest)) {
			  System.out.println("Booking flight..Please wait");
			  Thread.sleep(2000);
			  element.click();
			  Thread.sleep(5000);
			  
		  }
		  
	  }
	  
	  Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
      ImageIO.write(myScreenshot.getImage(),"PNG",new File("C:\\Users\\CSS\\Desktop\\Screenshot.png"));
	  
  }

}
