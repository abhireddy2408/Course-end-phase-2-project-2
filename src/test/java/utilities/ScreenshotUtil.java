package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	public static String takeScreenShot(WebDriver driver, String screenshotName) {
	
	String filePath = System.getProperty("user.dir")+ "/screenhots/" + screenshotName +".png";
	
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	try {
		FileUtils.copyFile(src, new File(filePath));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return filePath;
	
	}
}
