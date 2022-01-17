package com.integration.configuration;

import com.integration.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@ComponentScan
@EnableIntegration
@EnableIntegrationManagement
@EnableTransactionManagement
@IntegrationComponentScan({"com.integration"})
public class PersonConfiguration extends PersonConfigurationDeclaration {

    @MessagingGateway
    public interface PersonServiceGateway {
        @Gateway(payloadExpression = "' '", requestChannel = "process.input")
        List<Person> processPerson();
    }

    @Bean
    public IntegrationFlow process() {
        return flow -> flow
                .handle(this.personReaderHandler)
                .split()
                .filter(this.personFilter)
                .<Person, Character>route(person -> person.getGenre(),
                        mapping -> mapping
                                .subFlowMapping('M', sbm -> sbm.gateway(this.processMaleFlow()))
                                .subFlowMapping('F', sbm -> sbm.gateway(this.processFemaleFlow())))
                .handle(this.personHandler)
                .aggregate(this.personAggregator)
                .transform(this.personTransformer);
    }

    @Bean
    public IntegrationFlow processMaleFlow() {
        return flow -> flow.handle(this.personMaleHandler);
        /*return flow -> flow
                .channel(c -> c.queue(10))
                .publishSubscribeChannel(c ->
                        c.subscribe(sub -> sub.handle(personMaleHandler)));*/
    }

    @Bean
    public IntegrationFlow processFemaleFlow() {
        return flow -> flow.handle(this.personFemaleHandler);
        /*return flow -> flow
                .channel(c -> c.queue(10))
                .publishSubscribeChannel(c ->
                        c.subscribe(sub -> sub.handle(personFemaleHandler)));*/
    }

    @Bean
    public QueueChannel errorChannel() {
        return MessageChannels.queue().get();
    }
}
