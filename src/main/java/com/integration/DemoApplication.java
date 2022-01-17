package com.integration;

import com.integration.configuration.PersonConfiguration;
import com.integration.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);
        List<Person> response = configurableApplicationContext.getBean(PersonConfiguration.PersonServiceGateway.class).processPerson();
        response.forEach(System.out::println);
    }

}

