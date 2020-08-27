package quebec.virtualite.backend.services.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import quebec.virtualite.backend.services.domain.database.GreetingRepository
import quebec.virtualite.backend.services.domain.entities.GreetingEntity

@Service
class DomainServiceImpl : DomainService
{
    @Autowired
    private lateinit var greetingRepository: GreetingRepository

    override fun deleteGreetings()
    {
        greetingRepository.deleteAll()
    }

    override fun getGreetings(): List<GreetingEntity>
    {
        return list(greetingRepository.findAll())
    }

    override fun recordGreeting(name: String)
    {
        val greeting = greetingRepository.findByName(name) ?: GreetingEntity(name)

        greetingRepository.save(greeting)
    }

    private fun <T> list(iterable: Iterable<T>): List<T>
    {
        val list: ArrayList<T> = ArrayList()
        iterable.forEach(list::add)

        return list
    }
}