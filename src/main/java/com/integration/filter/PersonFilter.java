package com.integration.filter;

import com.integration.entity.Person;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Component
public class PersonFilter {

    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${required.age}")
    private int requiredAge;

    public boolean filter(Person person) {
        if (Objects.nonNull(person.getDob())) {
            this.LOGGER.info("BEGIN: Start to filter if the person is adult: Person Name: [{}]", person.getName());
            LocalDate now = LocalDate.now();
            long yearsElapsed = ChronoUnit.YEARS.between(person.getDob(), now);
            boolean result = yearsElapsed >= requiredAge;
            this.LOGGER.info("END: Finish to filter if the person is adult: Person Name: [{}]", person.getName());
            return result;
        }
        return false;
    }
}
