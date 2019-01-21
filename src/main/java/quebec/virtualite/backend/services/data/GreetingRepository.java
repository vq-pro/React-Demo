package quebec.virtualite.backend.services.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quebec.virtualite.backend.services.domain.Greeting;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long>
{
}
