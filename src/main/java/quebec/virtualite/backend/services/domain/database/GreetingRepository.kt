package quebec.virtualite.backend.services.domain.database

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import quebec.virtualite.backend.services.domain.entities.GreetingEntity
import java.util.*

@Repository
interface GreetingRepository : CrudRepository<GreetingEntity, Long>
{
    fun findByName(name: String): GreetingEntity?
}