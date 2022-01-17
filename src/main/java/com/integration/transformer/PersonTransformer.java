package com.integration.transformer;

import com.integration.entity.Person;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonTransformer {

    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    public List<Person> transform(List<Object> payloads){
        this.LOGGER.info("BEGIN: Start to transform List of Optional into List of Person");
        List<Person> result = new ArrayList<>();
        payloads.forEach(payload -> {
            Optional<Person> optionalPerson = (Optional<Person>) payload;
            if (optionalPerson.isPresent()) {
                result.add(optionalPerson.get());
            }
        });
        this.LOGGER.info("END: Finished to transform List of Optional into List of Person");
        return result;
    }
}
