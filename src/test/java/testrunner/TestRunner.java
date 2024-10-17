package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = "src/test/java/features",
		glue= {"stepdefs", "hooks"},
		plugin = {"pretty", "html:target/cucumber-reports.html"},
		monochrome = true,
		tags = "@Smoke"
//	    features = "src/test/java/features", // Adjust the path as needed
//	    glue = "features", // mention only folder name for step definitions package
//	    plugin = {"pretty", "html:target/cucumber-reports.html"}, // Optional for reporting
//	    monochrome = true, // Optional for better console output
//	    tags = "@Smoke and @Login" // tags are used to run specific group of scenario's
		
		)
public class TestRunner {
	
	

}
