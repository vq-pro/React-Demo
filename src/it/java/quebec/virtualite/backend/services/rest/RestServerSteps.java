package quebec.virtualite.backend.services.rest;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import quebec.virtualite.backend.services.domain.Domain;
import quebec.virtualite.backend.services.domain.entities.Greeting;
import quebec.virtualite.backend.utils.RestClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static quebec.virtualite.backend.Application.TEST_PASSWORD;
import static quebec.virtualite.backend.Application.TEST_USER;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
public class RestServerSteps
{
    private static final String[] GREETINGS_LIST_HEADER = {"Name"};

    @Autowired
    private Domain domain;

    @Autowired
    private Environment environment;

    @Autowired
    private RestClient rest;

    @PostConstruct
    public void _init()
    {
        rest._init(getServerPort());
    }

    @Before
    public void beforeEachScenario()
    {
        domain.deleteGreetings();
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

    @Then("^we should have a record of greetings for:$")
    public void weShouldHaveARecordOfGreetingsFor(DataTable expectedGreetings)
    {
        expectedGreetings.diff(actualGreetings());
    }

    private DataTable actualGreetings()
    {
        return greetingsTable(domain.getGreetings());
    }

    private int getServerPort()
    {
        String serverPort = environment.getProperty("local.server.port");
        assertThat(serverPort, not(nullValue()));

        return Integer.valueOf(serverPort);
    }

    private DataTable greetingsTable(List<Greeting> greetings)
    {
        List<List<String>> raw = new ArrayList<>();
        raw.add(asList(GREETINGS_LIST_HEADER));

        greetings.forEach(greeting ->
            raw.add(singletonList(greeting.getName())));

        return DataTable.create(raw);
    }
}
