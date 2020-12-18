import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
strict = true,
features = {"H:/git/com.rulerelationship.project/Features/Smoke/QA_Smoke.feature:23"},
plugin = {"json:H:/git/com.rulerelationship.project/target/cucumber-parallel/1.json"},
monochrome = true,
glue = {"project.features.steps\",\"project.features"})
public class Parallel01IT {
}