package com.integration.handler;

import com.integration.entity.Person;
import com.integration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonHandler {

    @Autowired
    private PersonRepository repository;

    public Person process(Person person) {
        try {
            return repository.save(person);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
