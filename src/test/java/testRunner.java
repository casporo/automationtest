import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
               features = "src/test/java/features",//#Path for the Feature files Folder.
                // plugin ={"pretty","html:reports/test-report"},#Path for the Reports Html Folder#
        //glue = {"stepDefinitions"},
               tags= {"@Scenario3"})//#Declaring multiple Feature names of files#

public class testRunner {
}
