package quebec.virtualite.backend.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import quebec.virtualite.backend.services.domain.Domain;

import static java.lang.String.format;

@RestController
public class RestServer
{
    @Autowired
    private Domain domain;

    @GetMapping("/greeting/{name}")
    public GreetingResponse greet(@PathVariable String name)
    {
        domain.recordGreeting(name);

        GreetingResponse greeting = new GreetingResponse();
        greeting.content = format("Hello %s!", name);

        return greeting;
    }
}
