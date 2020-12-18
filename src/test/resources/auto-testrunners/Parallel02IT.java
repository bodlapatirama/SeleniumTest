import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
strict = true,
features = {"H:/git/com.rulerelationship.project/Features/Smoke/QA_Smoke.feature:45"},
plugin = {"json:H:/git/com.rulerelationship.project/target/cucumber-parallel/2.json"},
monochrome = true,
glue = {"project.features.steps\",\"project.features"})
public class Parallel02IT {
}