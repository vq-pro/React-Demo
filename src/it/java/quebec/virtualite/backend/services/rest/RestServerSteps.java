package quebec.virtualite.backend.services.rest;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import quebec.virtualite.backend.services.domain.DomainService;
import quebec.virtualite.backend.services.domain.entities.Greeting;
import quebec.virtualite.backend.utils.RestClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static quebec.virtualite.backend.Application.TEST_PASSWORD;
import static quebec.virtualite.backend.Application.TEST_USER;
import static quebec.virtualite.backend.utils.RestParam.param;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
public class RestServerSteps
{
    private static final String[] GREETINGS_LIST_HEADER = {"Name"};

    @Autowired
    private DomainService domainService;

    @Autowired
    private RestClient rest;

    @Value("${local.server.port}")
    private int serverPort;

    @PostConstruct
    public void _init()
    {
        rest._init(serverPort);
    }

    @Before
    public void beforeEachScenario()
    {
        domainService.deleteGreetings();
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

    @When("^we ask for a greeting for \"([^\"]*)\" \\[POST \"([^\"]*)\"\\]$")
    public void weAskForAGreetingFor(String nameValue, String url)
    {
        rest.post(url, param("name", nameValue));
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
        return greetingsTable(domainService.getGreetings());
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
