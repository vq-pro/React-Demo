package quebec.virtualite.backend;

import cucumber.api.DataTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import quebec.virtualite.backend.services.domain.DomainService;
import quebec.virtualite.backend.services.rest.RestServerSteps;
import quebec.virtualite.backend.utils.RestClient;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
@RunWith(SpringRunner.class)
public class RestServerIT
{
    @Autowired
    private DomainService domainService;

    @Autowired
    private RestClient rest;

    private RestServerSteps steps = new RestServerSteps();

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void _init()
    {
        rest._init(serverPort);

        steps.domainService = domainService;
        steps.rest = rest;
    }

    @Test
    public void getGreeting()
    {
        // Given
        steps.weAreLoggedIn();

        // When
        steps.weAskForAGreetingFor("Toto", "/v1/greetings/{name}");

        // Then
        steps.weGetAGreetingMessage
            (
                "{" +
                "  \"content\": \"Hello Toto!\"" +
                "}"
            );
        steps.weShouldHaveARecordOfGreetingsFor(table("Toto"));
    }

    @Test
    public void getGreetingWhenNotLoggedIn()
    {
        // Given
        steps.weAreNotLoggedIn();

        // When
        steps.weAskForAGreetingFor("Toto", "/v1/greetings/{name}");

        // Then
        steps.weShouldGetAnError(401);
    }

    private DataTable table(String name)
    {
        List<List<String>> raw = new ArrayList<>();
        raw.add(singletonList("Name"));
        raw.add(singletonList(name));

        return DataTable.create(raw);
    }
}
