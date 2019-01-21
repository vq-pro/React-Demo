package quebec.virtualite.backend.services.domain;

public class GreetingBuilder
{
    public static Greeting greeting(String name)
    {
        Greeting greeting = new Greeting();
        greeting.name = name;

        return greeting;
    }
}
