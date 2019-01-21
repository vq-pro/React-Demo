package quebec.virtualite.backend.services.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quebec.virtualite.backend.services.domain.Greeting;

import java.util.Optional;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long>
{
    Optional<Greeting> findByName(String name);
}
