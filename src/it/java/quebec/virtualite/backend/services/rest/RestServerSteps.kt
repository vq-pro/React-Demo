package quebec.virtualite.backend.services.rest

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.apache.http.HttpStatus
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration
import quebec.virtualite.backend.security.SecurityUsers
import quebec.virtualite.backend.services.domain.DomainService
import quebec.virtualite.backend.utils.RestClient
import quebec.virtualite.backend.utils.RestParam
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
class RestServerSteps
{
    @Autowired
    private lateinit var domainService: DomainService

    @Autowired
    private lateinit var rest: RestClient

    @Value("\${local.server.port}")
    private val serverPort = 0

    @PostConstruct
    fun init()
    {
        rest.init(serverPort)
    }

    @Before
    fun beforeEachScenario()
    {
        domainService.deleteGreetings()
    }

    @Given("we are logged in")
    fun givenWeAreLoggedIn()
    {
        rest.login(SecurityUsers.TEST_USER, SecurityUsers.TEST_PASSWORD)
    }

    @Given("we are not logged in")
    fun givenWeAreNotLoggedIn()
    {
        // Nothing to do here
    }

    @Then("we get a greeting message")
    fun thenWeGetAGreetingMessage(expectedJson: String)
    {
        assertThat(rest.response().statusCode(), equalTo(HttpStatus.SC_OK))
        assertThat(rest.response().asString(), equalTo(rest.trim(expectedJson)))
    }

    @Then("we should get a {int} error")
    fun thenWeShouldGetAError(errorCode: Int)
    {
        assertThat(rest.response().statusCode(), equalTo(errorCode))
    }

    /**
     * Server Unit Test: [RestServerTest.greet]
     */
    @When("we ask for a greeting for {string} [PUT {string}]")
    fun whenWeAskForAGreeting(name: String, url: String)
    {
        rest.put(url, RestParam("name", name))
    }
}