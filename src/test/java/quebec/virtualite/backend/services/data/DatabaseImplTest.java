package quebec.virtualite.backend.services.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import quebec.virtualite.backend.services.domain.Greeting;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static quebec.virtualite.backend.services.domain.GreetingBuilder.greeting;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseImplTest
{
    private static final String NAME = "name";

    @InjectMocks
    private Database db = new DatabaseImpl();

    @Mock
    private GreetingRepository mockedGreetingRepository;

    @Test
    public void getGreetings()
    {
        // When
        List<Greeting> greetings = db.getGreetings();

        // Then
        verify(mockedGreetingRepository).findAll();

        assertThat(greetings, not(nullValue()));
    }

    @Test
    public void recordGreeting()
    {
        // When
        db.recordGreet(NAME);

        // Then
        verify(mockedGreetingRepository).findByName(NAME);
        verify(mockedGreetingRepository).save(greeting(NAME));
    }
}
