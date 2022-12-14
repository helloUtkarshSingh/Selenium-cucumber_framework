package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "Practice.SeleniumFrameworkDesign.stepDefinitions", monochrome = true, plugin = {
		"html:cucumberTarget/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
