package quebec.virtualite.backend.services.domain;

import quebec.virtualite.backend.services.domain.entities.GreetingEntity;

import java.util.List;

public interface DomainService
{
    void deleteGreetings();

    List<GreetingEntity> getGreetings();

    void recordGreeting(String name);
}
