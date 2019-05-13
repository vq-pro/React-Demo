package quebec.virtualite.backend.services.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import quebec.virtualite.backend.services.domain.DomainService;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Consumer;

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
    private DomainService mockedDomainService;

    @Test
    public void greet()
    {
        // When
        checkMono(server.greet(NAME), output -> {

            // Then
            assertThat(output.content, is("Hello " + NAME + "!"));

            verify(mockedDomainService).recordGreeting(NAME);
        });
    }

    private static <T> void checkMono(Mono<T> mono, Consumer<T> verifications)
    {
        StepVerifier.create(mono)
            .assertNext(verifications)
            .verifyComplete();
    }
}
