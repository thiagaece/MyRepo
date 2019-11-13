package samplePackage;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class SampleTest1 {
	
	public WebDriver driver;
	
  @Test
  public void test1() {
	  driver.get("https://www.espncricinfo.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  String path =  System.getProperty("user.dir");
	  System.setProperty("webdriver.chrome."
	  		+ "driver", path+"/src/main/resources/chromedriver.exe");
	  System.out.println(path);
	  driver = new ChromeDriver();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
