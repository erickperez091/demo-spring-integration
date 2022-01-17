package com.integration.handler;

import com.integration.entity.Person;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PersonMaleHandler {

    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    public Person process(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hi, I'm a male and this is my data: ");
        sb.append(person.toString());
        this.LOGGER.info(sb.toString());
        return person;
    }
}
