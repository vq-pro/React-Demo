package quebec.virtualite.backend.services.domain;

import quebec.virtualite.backend.services.domain.entities.Greeting;

import java.util.List;

public interface Domain
{
    List<Greeting> getGreetings();

    void recordGreeting(String name);
}
