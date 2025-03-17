package ascentBusiness;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import baseTest.BaseTest;

public class ExtentReportDemo extends BaseTest {

	static ExtentReports report;

	 @BeforeTest
	public void config1() {

		String file = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		reporter.config().setReportName("Test Demo");
		reporter.config().setDocumentTitle("ExtentReport");

		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Abdul Rahaman");

	}

	@Test
	public void scr() throws IOException {
		report.createTest("src");
		initialization();
		
		System.out.println("This is extent report demo");
		report.flush();
	}

}
