package dev.ghonda.project.management.configuration;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

@Configuration
public class ValidatorConfiguration {

    @Bean
    @Primary
    public LocalValidatorFactoryBean validator(final AutowireCapableBeanFactory autowireCapableBeanFactory) {
        final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setConstraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory));
        return bean;
    }


}
