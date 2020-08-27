package quebec.virtualite.backend.services.rest

import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import quebec.virtualite.backend.services.domain.DomainService

@RestController
@RequestMapping("/v2")
class RestServer
{
    private val log = getLogger(javaClass)

    @Autowired
    private lateinit var domainService: DomainService

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/greetings/{name}")
    fun greet(@PathVariable name: String): GreetingResponse
    {
        log.warn("Greeting!")

        domainService.recordGreeting(name)

        return GreetingResponse("Hello $name!")
    }
}