package quebec.virtualite.backend.data;

import quebec.virtualite.backend.domain.Greeting;

import java.util.List;

public interface Database
{
    List<Greeting> getGreetings();

    void recordGreet(String name);
}
