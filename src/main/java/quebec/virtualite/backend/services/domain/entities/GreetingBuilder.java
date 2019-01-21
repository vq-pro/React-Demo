package quebec.virtualite.backend.services.domain.entities;

public class GreetingBuilder
{
    public static Greeting greeting(String name)
    {
        return new Greeting()
            .setName(name);
    }
}
