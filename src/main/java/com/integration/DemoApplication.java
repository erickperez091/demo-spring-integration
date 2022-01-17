package com.integration;

import com.integration.configuration.PersonConfiguration;
import com.integration.entity.Person;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);
        List<Person> response = configurableApplicationContext.getBean(PersonConfiguration.PersonServiceGateway.class).processPerson();
        if (!response.isEmpty()) {
            response.forEach(System.out::println);
        } else {
            LOGGER.info("No Persons were recently added.");
        }
    }

}

