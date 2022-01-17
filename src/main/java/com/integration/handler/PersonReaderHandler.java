package com.integration.handler;

import com.integration.entity.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
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


    public List<Person> read() {
        List<Person> personList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get("person.csv"))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            csvParser.getRecords().forEach(record -> {
                Person person = new Person();
                person.setIdentityCard(record.get("identityCard"));
                person.setName(record.get("name"));
                person.setLastName(record.get("lastName"));
                person.setMiddleName(record.get("middleName"));
                person.setDob(LocalDate.parse(record.get("dob")));
                personList.add(person);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
