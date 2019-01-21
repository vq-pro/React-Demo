package quebec.virtualite.backend.services.domain.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quebec.virtualite.backend.services.domain.entities.Greeting;

import java.util.Optional;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long>
{
    Optional<Greeting> findByName(String name);
}
