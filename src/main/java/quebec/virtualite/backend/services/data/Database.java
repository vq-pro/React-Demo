package quebec.virtualite.backend.services.data;

import quebec.virtualite.backend.services.domain.Greeting;

import java.util.List;

public interface Database
{
    List<Greeting> getGreetings();

    void recordGreet(String name);
}
