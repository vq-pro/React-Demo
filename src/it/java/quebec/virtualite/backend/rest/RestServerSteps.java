package quebec.virtualite.backend.rest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import quebec.virtualite.backend.utils.RestClient;
import quebec.virtualite.security.SecurityUserManager;

import javax.annotation.PostConstruct;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
public class RestServerSteps
{
    private static final String TEST_USER = "pat";
    private static final String TEST_PASSWORD = "{sha256}123456";

    @Autowired
    private Environment environment;

    @Autowired
    private RestClient rest;

    @Autowired
    private SecurityUserManager userManager;

    @PostConstruct
    public void _init()
    {
        userManager.defineUser(TEST_USER, TEST_PASSWORD);

        int serverPort = Integer.valueOf(environment.getProperty("local.server.port"));
        rest.init(serverPort);
    }

    @Given("^we are logged in$")
    public void weAreLoggedIn()
    {
        rest.login(TEST_USER, TEST_PASSWORD);
    }

    @Given("^we are not logged in$")
    public void weAreNotLoggedIn()
    {
        // Nothing to do here
    }

    @When("^we ask for a greeting for \"([^\"]*)\" \\[GET \"([^\"]*)\"\\]$")
    public void weAskForAGreetingFor(String name, String url)
    {
        rest.get(url
            .replace("{name}", name));
    }

    @Then("^we get a greeting message$")
    public void weGetAGreetingMessage(String expectedJson)
    {
        assertThat(rest.response().statusCode(), is(SC_OK));
        assertThat(rest.response().asString(), is(rest.trim(expectedJson)));
    }

    @Then("^we should get a (\\d+) error$")
    public void weShouldGetAnError(int errorCode)
    {
        assertThat(rest.response().statusCode(), is(errorCode));
    }
}
