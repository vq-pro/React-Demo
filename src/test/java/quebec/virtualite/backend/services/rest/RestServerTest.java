package quebec.virtualite.backend.services.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import quebec.virtualite.backend.services.domain.Domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RestServerTest
{
    private static final String NAME = "name";

    @InjectMocks
    private RestServer server;

    @Mock
    private Domain mockedDomain;

    @Test
    public void getGreeting()
    {
        // When
        GreetingResponse greeting = server.greet(NAME);

        // Then
        verify(mockedDomain).recordGreeting(NAME);

        assertThat(greeting.content, is("Hello " + NAME + "!"));
    }
}
