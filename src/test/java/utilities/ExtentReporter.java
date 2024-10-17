package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	static ExtentReports extentReports;
	
	public static  ExtentReports extentRep() {
		
	String filePath = System.getProperty("user.dir") + "/reports/extentreport.html";
	ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(filePath);
	extentSparkReporter.config().setReportName("PizzaHut Automation Test Report");
	extentSparkReporter.config().setDocumentTitle("Automation Report");
	
	ExtentReports extentReports = new ExtentReports();
	extentReports.attachReporter(extentSparkReporter);
	extentReports.setSystemInfo("OS", "MAC");
	extentReports.setSystemInfo("Author", "Anonymous");
	extentReports.setSystemInfo("Environment", "QA");
	
	return extentReports;
	}
	
	 public static void endReport() {
		 
		 extentReports.flush();
		 
	 }
}
