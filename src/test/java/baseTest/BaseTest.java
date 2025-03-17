package baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	static WebDriver driver;

	static Properties prop;
	
	static ExtentReports report;

	public static void initialization() throws IOException {
		prop = new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\ascentBusinessResources\\GlobalResources.properties");

		prop.load(fis);
		
		//if we fetch the browser from Maven commend if null then use prop.getProperty("browser") otherwise maven one
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browser = prop.getProperty("browser");

		
		if (browserName.contains("chrome")) 
		{
			ChromeOptions options=new ChromeOptions();
			
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
			driver.manage().window().maximize();
			
		}
	}

	public static WebDriver goToLandingPage() throws IOException {
		initialization();
		String url = prop.getProperty("url");
		driver.get(url);
		return driver;
	}
	
	
	public static ExtentReports config() {

		String file = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		reporter.config().setReportName("Test Demo");
		reporter.config().setDocumentTitle("ExtentReport");

		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Abdul Rahaman");

		return report;
	}
	
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}

	
	@AfterTest
	public void tearDown() {

		driver.close();
		
	}
}
