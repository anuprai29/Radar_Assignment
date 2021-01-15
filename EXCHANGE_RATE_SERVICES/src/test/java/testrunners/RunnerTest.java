package testrunners;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
/**
 *
 * @author Anup Rai
 * 
 */

@RunWith(Cucumber.class)
@CucumberOptions(
					features = { "classpath:features" }, // Directory holds the information for feature files
					glue = { "stepDefinitions", "myHooks" }, // Directory holds the information for steps definition & Hooks
					//tags = "@latest,@specificdate,@futuredate",
					plugin = { "pretty:STDOUT", "rerun:target/rerun/rerun.txt",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
					dryRun = false
				)

public class RunnerTest {
}