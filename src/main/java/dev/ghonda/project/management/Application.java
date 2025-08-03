package dev.ghonda.project.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.modulith.Modulith;

@Modulith
@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
