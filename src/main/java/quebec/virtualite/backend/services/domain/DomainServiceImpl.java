package quebec.virtualite.backend.services.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quebec.virtualite.backend.services.domain.database.GreetingRepository;
import quebec.virtualite.backend.services.domain.entities.Greeting;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainServiceImpl implements DomainService
{
    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public void deleteGreetings()
    {
        greetingRepository.deleteAll();
    }

    @Override
    public List<Greeting> getGreetings()
    {
        return list(greetingRepository.findAll());
    }

    @Override
    public void recordGreeting(String name)
    {
        Greeting greeting = greetingRepository.findByName(name)
            .orElse(new Greeting().name(name));

        greetingRepository.save(greeting);
    }

    private <T> List<T> list(Iterable<T> iterable)
    {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);

        return list;
    }
}
