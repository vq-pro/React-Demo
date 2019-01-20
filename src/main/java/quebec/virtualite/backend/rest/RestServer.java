package quebec.virtualite.backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
public class RestServer
{
    @GetMapping("/greeting/{name}")
    public GreetingResponse greet(@PathVariable String name)
    {
        GreetingResponse greeting = new GreetingResponse();
        greeting.content = format("Hello %s!", name);

        return greeting;
    }
}
