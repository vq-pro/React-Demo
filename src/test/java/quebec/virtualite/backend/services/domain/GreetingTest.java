package quebec.virtualite.backend.services.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static quebec.virtualite.backend.services.domain.GreetingBuilder.greeting;

public class GreetingTest
{
    @Test
    public void getters()
    {
        Greeting a = greeting("a");

        assertThat(a.getId(), is(0L));
        assertThat(a.getName(), is("a"));
    }

    @Test
    public void equals()
    {
        Greeting a = greeting("a");
        Greeting a2 = greeting("a");
        Greeting b = greeting("b");

        assertEquals(a, a2);
        assertNotEquals(a, b);
    }

    @Test
    public void hashCode_()
    {
        assertThat(greeting("a").hashCode(), not(0));
    }
}
