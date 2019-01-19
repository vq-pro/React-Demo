package quebec.virtualite.backend.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import quebec.virtualite.backend.domain.Greeting;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RestServerTest
{
    private static final String NAME = "name";

    @InjectMocks
    private RestServer server;

    @Test
    public void getGreeting()
    {
        // When
        Greeting greeting = server.greet(NAME);

        // Then
        assertThat(greeting.content, is("Hello " + NAME + "!"));
    }
}
