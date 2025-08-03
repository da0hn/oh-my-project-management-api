package dev.ghonda.project.management.configuration;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.stereotype.Component;

@Component
public class ModulithVerifier implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ApplicationModules.of(event.getSpringApplication().getMainApplicationClass())
            .verify();
    }

}
