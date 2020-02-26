package quebec.virtualite.backend.services.domain.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quebec.virtualite.backend.services.domain.entities.GreetingEntity;

import java.util.Optional;

@Repository
public interface GreetingRepository extends CrudRepository<GreetingEntity, Long>
{
    Optional<GreetingEntity> findByName(String name);
}
