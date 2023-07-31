package Html.ExtentReoprt;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportwithTestng {

    ExtentSparkReporter htmlReporter;
    ExtentReports report;
    WebDriver driver;

    @BeforeSuite
    public void configReport() {
        htmlReporter = new ExtentSparkReporter("ExtentAmazonReport.html");
        report = new ExtentReports();
        // attach the report
        report.attachReporter(htmlReporter);

        // adding environment info
        report.setSystemInfo("Machine", "TestPC1");
        report.setSystemInfo("OS", "Windows");
        report.setSystemInfo("Username", "Tanishka");
        report.setSystemInfo("Browser", "Chrome");

        // Configuration for look and feel of Report
        htmlReporter.config().setDocumentTitle("Amazon Extent Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @BeforeTest
    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test(priority=0,enabled=true)
    public void launchBrowserandLoginAmazon() {
        ExtentTest test = report.createTest("Launching Amazon URL", "This Test will launch Amazon")
                .assignAuthor("Tanishka").assignCategory("Sanity Test").assignDevice("Windows");
        test.info("Amazon Login TestCase is Started");
        driver.get("https://www.amazon.in");
        boolean logodisplay = driver.findElement(By.id("nav-logo-sprites")).isDisplayed();
        Assert.assertEquals(logodisplay, true);
        driver.findElement(By.xpath("//span[text()=\"Hello, sign in\"]")).click();
        driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys("8126921207");
        driver.findElement(By.xpath("//input[@id=\"continue\"]")).click();
        test.info("Enter the phone number");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("Amazon@1234");
        driver.findElement(By.xpath("//input[@id=\"signInSubmit\"]")).click();
        test.info("Enter the password");
        try {
        boolean address = driver.findElement(By.xpath("//span[normalize-space(text())=\"Kaladhungi 26313‌9\"]"))
                .isDisplayed();
        if(address) {
       	 test.pass("Login is successful");	
       	 test.info("Login of Amazon is completed");
       	 Assert.assertTrue(address);
       }
       else {
       	test.fail("Login is unsuccessful");
       	Assert.assertTrue(address);
       }
        }
        catch(Exception e) {
        	
        	test.fail("An error occurred during login: " + e.getMessage());
            Assert.fail("An error occurred during login: " + e.getMessage());
        }
        
          
    }

    @Test(priority=1)
    public void verifyGoogle() {
        ExtentTest test = report.createTest("Google Search Test one", "This is a test to validate google functionality");
        test.log(Status.INFO, "Starting Test Case");
        driver.get("https://www.google.com");
        test.pass("Navigated to google.com");

        driver.findElement(By.name("q")).sendKeys("Automation");
        test.pass("Entered the Text in the searchbox");

        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        test.pass("Pressed Enter from the keyboard");

        String automationTechopedia = driver.findElement(By.xpath("//h3[text()=\"What is Automation? - Definition from Techopedia\"]")).getText();
       boolean flag;
        if (automationTechopedia.equals("What is Automation? - Definition from Techopedia")) {
        	test.pass("What is Automation? - Definition from Techopedia");
        	test.info("Test is passed");
        	flag=true;
        Assert.assertTrue(flag);
        }
        else
        {
        	test.fail("This Test is failed as it doesn't recongnize the text");
        	 test.info("This Test case failed");
        	 flag=false;
        	 Assert.assertTrue(flag);
        }
        
       
    }
    
    @Test(priority=2,enabled=true)
    public void verifyGoogle2() throws InterruptedException {
        ExtentTest test = report.createTest("Google Search Test two", "This is a test to validate google functionality");
        test.log(Status.INFO, "Starting Test Case");
        driver.get("https://www.google.com");
        test.pass("Navigated to google.com");

        driver.findElement(By.name("q")).sendKeys("Selenium");
        test.pass("Entered the Text in the searchbox");

        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        test.pass("Pressed Enter from the keyboard");
        
        driver.findElement(By.xpath("//h3[text()=\"Selenium\"]")).click();
       
       boolean softwaredisplay= driver.findElement(By.xpath("//p[text()=\"What you do with that power is entirely up to you.\"]")).isDisplayed();
        if(softwaredisplay) {
        	test.pass("Software is displayed");
        	Assert.assertTrue(softwaredisplay);
        }
        
        else {
        	test.fail("Software is not Displayed");
        	Assert.assertTrue(softwaredisplay);
        }
       
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void closeReport() {
        report.flush();
    }
}
