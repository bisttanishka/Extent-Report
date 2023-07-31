package Html.ExtentReoprt;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportBasic {
     static WebDriver driver;
	public static void main(String[] args) {
		
	ExtentSparkReporter htmlReport=new ExtentSparkReporter("extent.html");
	ExtentReports extent =new ExtentReports();
	extent.attachReporter(htmlReport);
	//create test now 
	ExtentTest test1=extent.createTest("Google Search Test one", "This is a test to validate google functionality");
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	
	test1.log(Status.INFO, "Starting Test Case");
	driver.get("https://www.google.com");
	test1.pass("Navigated to google.com");
	
	driver.findElement(By.name("q")).sendKeys("Automation");
	test1.pass("Entered the Text in the searchbox");
	
	driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
	test1.pass("Pressed Enter from keyboard");
	
	driver.close();
	test1.pass("Close the browser");
	
	test1.info("Test Completed");
	extent.flush();
	
	

	}

}
