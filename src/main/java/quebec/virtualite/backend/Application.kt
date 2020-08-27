package quebec.virtualite.backend

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import quebec.virtualite.backend.security.SecurityUserManager
import quebec.virtualite.backend.security.SecurityUsers.TEST_PASSWORD
import quebec.virtualite.backend.security.SecurityUsers.TEST_USER
import javax.annotation.PostConstruct

@SpringBootApplication(scanBasePackages = ["quebec.virtualite.*"])
open class Application
{
    @Autowired
    private lateinit var userManager: SecurityUserManager

    companion object
    {
        private val log = LoggerFactory.getLogger(Application::class.java)

        @JvmStatic
        fun main(args: Array<String>)
        {
            SpringApplication.run(Application::class.java, *args)
        }

        init
        {
            log.warn("STARTING...")
        }
    }

    @PostConstruct
    fun init()
    {
        userManager.defineUser(TEST_USER, TEST_PASSWORD)
        log.warn("STARTED")
    }
}
