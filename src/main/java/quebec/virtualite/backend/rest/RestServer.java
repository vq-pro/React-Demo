package quebec.virtualite.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import quebec.virtualite.backend.data.Database;

import static java.lang.String.format;

@RestController
public class RestServer
{
    @Autowired
    private Database db;

    @GetMapping("/greeting/{name}")
    public GreetingResponse greet(@PathVariable String name)
    {
        db.recordGreet(name);

        GreetingResponse greeting = new GreetingResponse();
        greeting.content = format("Hello %s!", name);

        return greeting;
    }
}
