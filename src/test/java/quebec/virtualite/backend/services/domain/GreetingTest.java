package quebec.virtualite.backend.services.domain;

import org.junit.Test;
import quebec.virtualite.backend.services.domain.entities.Greeting;

import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static quebec.virtualite.backend.services.domain.entities.GreetingBuilder.greeting;

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

    @Test
    public void toString_()
    {
        assertThat(greeting("a"), hasToString("Greeting[id=0,name=a]"));
    }
}
