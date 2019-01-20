package quebec.virtualite.backend.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quebec.virtualite.backend.domain.Greeting;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long>
{
}
