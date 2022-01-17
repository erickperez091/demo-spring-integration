package com.integration.configuration;

import com.integration.entity.Person;
import com.integration.handler.PersonHandler;
import com.integration.handler.PersonReaderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@ComponentScan
@EnableIntegration
@EnableIntegrationManagement
@EnableTransactionManagement
@IntegrationComponentScan({"com.integration"})
public class PersonConfiguration {

    @Autowired
    private PersonReaderHandler personReaderHandler;

    @Autowired
    private PersonHandler personHandler;

    @MessagingGateway
    public interface PersonServiceGateway {

        @Gateway(payloadExpression = "' '", requestChannel = "process.input")
        List<Person> processPerson();
    }

    @Bean
    public IntegrationFlow process() {
        return flow -> flow
                .handle(personReaderHandler)
                .split()
                .handle(personHandler)
                .aggregate();
    }
}
