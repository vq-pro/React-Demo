package quebec.virtualite.backend.rest;

import org.springframework.web.bind.annotation.RestController;
import quebec.virtualite.backend.domain.Greeting;

import static java.lang.String.format;

@RestController
public class RestServer
{
    public Greeting greet(String name)
    {
        Greeting greeting = new Greeting();
        greeting.content = format("Hello %s!", name);

        return greeting;
    }
}
