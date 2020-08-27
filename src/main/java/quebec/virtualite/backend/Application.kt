package quebec.virtualite.backend

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import quebec.virtualite.backend.security.SecurityUserManager
import quebec.virtualite.backend.security.SecurityUsers.TEST_PASSWORD
import quebec.virtualite.backend.security.SecurityUsers.TEST_USER
import javax.annotation.PostConstruct

private val log = LoggerFactory.getLogger(Application::class.java)

@SpringBootApplication(scanBasePackages = ["quebec.virtualite.*"])
open class Application
{
    @Autowired
    private lateinit var userManager: SecurityUserManager

    @PostConstruct
    fun init()
    {
        userManager.defineUser(TEST_USER, TEST_PASSWORD)
        log.warn("STARTED")
    }
}

fun main(args: Array<String>)
{
    runApplication<Application>(*args)
}
