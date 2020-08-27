package quebec.virtualite.backend.services.domain

import quebec.virtualite.backend.services.domain.entities.GreetingEntity

interface DomainService
{
    fun deleteGreetings()
    fun getGreetings(): List<GreetingEntity>
    fun recordGreeting(name: String)
}