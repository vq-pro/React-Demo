package quebec.virtualite.backend.services.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import quebec.virtualite.backend.services.domain.database.GreetingRepository;
import quebec.virtualite.backend.services.domain.entities.Greeting;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static quebec.virtualite.backend.services.domain.entities.GreetingBuilder.greeting;

@RunWith(MockitoJUnitRunner.class)
public class DomainImplTest
{
    private static final String NAME = "name";

    @InjectMocks
    private Domain domain = new DomainImpl();

    @Mock
    private GreetingRepository mockedGreetingRepository;

    @Test
    public void deleteGreetings()
    {
        // When
        domain.deleteGreetings();

        // Then
        verify(mockedGreetingRepository).deleteAll();
    }

    @Test
    public void getGreetings()
    {
        // When
        List<Greeting> greetings = domain.getGreetings();

        // Then
        verify(mockedGreetingRepository).findAll();

        assertThat(greetings, not(nullValue()));
    }

    @Test
    public void recordGreeting()
    {
        // When
        domain.recordGreeting(NAME);

        // Then
        verify(mockedGreetingRepository).findByName(NAME);
        verify(mockedGreetingRepository).save(greeting(NAME));
    }
}
