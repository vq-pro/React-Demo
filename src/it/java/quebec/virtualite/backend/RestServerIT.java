package quebec.virtualite.backend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import quebec.virtualite.backend.services.domain.DomainService;
import quebec.virtualite.backend.utils.RestClient;
import quebec.virtualite.backend.utils.RestParam;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static quebec.virtualite.backend.security.SecurityUsers.TEST_PASSWORD;
import static quebec.virtualite.backend.security.SecurityUsers.TEST_USER;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
@RunWith(SpringRunner.class)
public class RestServerIT
{
    @Autowired
    private DomainService domainService;

    @Autowired
    private RestClient rest;

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void _init()
    {
        rest._init(serverPort);
        domainService.deleteGreetings();
    }

    @Test
    public void getGreeting()
    {
        // Given
        weAreLoggedIn();

        // When
        weAskForAGreetingFor("Toto", "/v2/greetings/{name}");

        // Then
        weGetAGreetingMessage
            (
                "{" +
                "  \"content\": \"Hello Toto!\"" +
                "}"
            );
    }

    @Test
    public void getGreetingWhenNotLoggedIn()
    {
        // Given
        weAreNotLoggedIn();

        // When
        weAskForAGreetingFor("Toto", "/v2/greetings/{name}");

        // Then
        weShouldGetAnError(401);
    }

    private void weAreLoggedIn()
    {
        rest.login(TEST_USER, TEST_PASSWORD);
    }

    private void weAreNotLoggedIn()
    {
        // Do nothing
    }

    private void weAskForAGreetingFor(String nameValue, String url)
    {
        rest.get(url, new RestParam("name", nameValue));
    }

    private void weGetAGreetingMessage(String expectedJson)
    {
        assertThat(rest.response().statusCode(), is(SC_OK));
        assertThat(rest.response().asString(), is(rest.trim(expectedJson)));
    }

    private void weShouldGetAnError(int errorCode)
    {
        assertThat(rest.response().statusCode(), is(errorCode));
    }
}
