package quebec.virtualite.backend.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quebec.virtualite.backend.domain.Greeting;

import java.util.ArrayList;
import java.util.List;

import static quebec.virtualite.backend.domain.GreetingBuilder.greeting;

@Service
public class DatabaseImpl implements Database
{
    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public List<Greeting> getGreetings()
    {
        return list(greetingRepository.findAll());
    }

    @Override
    public void recordGreet(String name)
    {
        greetingRepository.save(greeting(name));
    }

    private <T> List<T> list(Iterable<T> iterable)
    {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);

        return list;
    }
}
