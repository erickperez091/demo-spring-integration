package com.integration.handler;

import com.integration.entity.Person;
import com.integration.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonHandler {

    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonRepository repository;

    public Optional<Person> process(Person person) {
        try {
            this.LOGGER.info("BEGIN: Start to save a Person: [{}]", person);
            Optional<Person> resultOptional = Optional.of(repository.save(person));
            this.LOGGER.info("END: Finished to save a Person: [{}]", person);
            return resultOptional;
        } catch (DataIntegrityViolationException dive) {
            this.LOGGER.error("ERROR: Identity Card [{}] is already stored.", person.getIdentityCard());
            return Optional.empty();
        } catch (Exception ex) {
            this.LOGGER.error("ERROR: Some error happened.", ex.getMessage());
            return Optional.empty();
        }
    }
}
