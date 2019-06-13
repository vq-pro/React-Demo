package quebec.virtualite.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import quebec.virtualite.backend.security.SecurityUserManager;

import javax.annotation.PostConstruct;

import static quebec.virtualite.backend.security.SecurityUsers.TEST_PASSWORD;
import static quebec.virtualite.backend.security.SecurityUsers.TEST_USER;

@SpringBootApplication(scanBasePackages = {"quebec.virtualite.*"})
public class Application
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SecurityUserManager userManager;

    static
    {
        log.warn("STARTING...");
    }

    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init()
    {
        userManager.defineUser(TEST_USER, TEST_PASSWORD);

        log.warn("STARTED");
    }
}
