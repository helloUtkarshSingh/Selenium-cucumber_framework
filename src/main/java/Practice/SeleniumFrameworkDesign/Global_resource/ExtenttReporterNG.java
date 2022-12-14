package Practice.SeleniumFrameworkDesign.Global_resource;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenttReporterNG {

	public static ExtentReports getReportObject() {

		// Need two class
		// 1.ExtentSparkReporter :- It is responsible for creating html file
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		// To set the name of the html reports i.e DemoAutomationReport
		reporter.config().setReportName("DemoAutomationReport");
		// To set the document title(i.e name show in the tab) of the html reports i.e
		// DemoReport
		reporter.config().setDocumentTitle("DemoReport");

		// 2.ExtentReports:- It is responsible to create and consolidate all the test
		// case
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Utkarsh Singh");
		
		return extent;

	}
}
