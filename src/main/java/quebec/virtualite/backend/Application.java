package quebec.virtualite.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import quebec.virtualite.backend.security.SecurityUserManager;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"quebec.virtualite.*"})
public class Application extends SpringBootServletInitializer
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static final String TEST_USER = "joe_user";
    public static final String TEST_PASSWORD = "123456";

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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(Application.class);
    }
}
