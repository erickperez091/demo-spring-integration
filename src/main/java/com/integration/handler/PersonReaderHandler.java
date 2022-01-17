package com.integration.handler;

import com.integration.entity.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonReaderHandler {

    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${file.name:person.csv}")
    private String fileName;

    public List<Person> read() {
        List<Person> personList = new ArrayList<>();
        this.LOGGER.info("BEGIN: Start to read data from file");
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            csvParser.getRecords().forEach(record -> {
                this.LOGGER.info("BEGIN: Start to transform line into a Person");
                Person person = new Person();
                person.setIdentityCard(record.get("identityCard"));
                person.setName(record.get("name"));
                person.setLastName(record.get("lastName"));
                person.setMiddleName(record.get("middleName"));
                person.setDob(LocalDate.parse(record.get("dob")));
                person.setGenre(record.get("genre").charAt(0));
                personList.add(person);
                this.LOGGER.info("END: Finished to transform line into a Person");
            });
            this.LOGGER.info("END: Finished to read data from file");
        } catch (IOException e) {
            this.LOGGER.error("Some error while reading the file", e.getMessage());
        } finally {
            return personList;
        }
    }
}
