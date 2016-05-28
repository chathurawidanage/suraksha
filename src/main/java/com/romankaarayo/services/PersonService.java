package com.romankaarayo.services;

import com.romankaarayo.db.Person;
import com.romankaarayo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Context;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author Chathura Widanage
 */
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> all() {
        List<Person> personList = new ArrayList<>();
        Iterator<Person> personIterator = this.personRepository.findAll().iterator();
        personIterator.forEachRemaining(person -> personList.add(person));
        return personList;
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public Person findById(Long id) {
        return this.personRepository.findOne(id);
    }

    public Person createPersonByImage(InputStream inputStream) throws IOException {
        String fileName = saveImage(inputStream);
        Person person = new Person();
        person.setImage(fileName);
        return personRepository.save(person);
    }

    private String saveImage(InputStream inputStream) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString() + ".jpeg";
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/opt/" + fileName));
        int read = 0;
        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            fileOutputStream.write(bytes);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        return fileName;
    }
}
