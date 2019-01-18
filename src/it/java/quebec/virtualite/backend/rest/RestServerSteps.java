package quebec.virtualite.backend.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
public class RestServerSteps
{
    private static final char NON_BREAKING_SPACE = (char) 0x00A0;

    @Autowired
    private Environment environment;

    private Response response;

    @PostConstruct
    public void _init()
    {
        RestAssured.port = getServerPort();
    }

    @When("^we ask for a greeting for \"([^\"]*)\" \\[GET \"([^\"]*)\"\\]$")
    public void weAskForAGreetingFor(String name, String url)
    {
        url = url.replace("{name}", name);

        // FIXME1 Add security
        response =
            given()
                .expect()
            .when()
                .get(url);

        response.then()
            .statusCode(200);
    }

    @Then("^we get a greeting message$")
    public void weGetAGreetingMessage(String expectedJson)
    {
        assertThat(response.asString(), is(trim(expectedJson)));
    }

    private Integer getServerPort()
    {
        String sPort = environment.getProperty("local.server.port");
        return Integer.valueOf(sPort);
    }

    private String trim(String json)
    {
        return json
            .replace(" ", "")
            .replace("\r", "")
            .replace("\n", "")
            .replace(NON_BREAKING_SPACE, ' ');
    }
}
