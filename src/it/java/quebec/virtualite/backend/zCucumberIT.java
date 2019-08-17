package quebec.virtualite.backend;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import static cucumber.api.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions
    (
        features = "src/features",
        plugin =
            {
                "html:target/cucumber-reports",
                "junit:target/cucumber-reports/cucumber.xml"
            },
        junit = "--step-notifications",
        monochrome = true,
        strict = true,
        snippets = CAMELCASE,
        tags = "not @Ignore"
    )
public class zCucumberIT
{
}
