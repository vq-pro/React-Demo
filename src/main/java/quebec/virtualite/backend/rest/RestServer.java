package quebec.virtualite.backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import quebec.virtualite.backend.domain.Greeting;

import static java.lang.String.format;

@RestController
public class RestServer
{
    @GetMapping("/greeting/{name}")
    public Greeting greet(@PathVariable String name)
    {
        Greeting greeting = new Greeting();
        greeting.content = format("Hello %s!", name);

        return greeting;
    }
}
