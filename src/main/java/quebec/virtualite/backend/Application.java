package quebec.virtualite.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages =
    {
        "quebec.virtualite.*"
    })
public class Application extends SpringBootServletInitializer
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

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
        log.warn("STARTED");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(Application.class);
    }
}
