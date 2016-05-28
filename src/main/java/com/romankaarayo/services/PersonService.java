package com.romankaarayo.services;

import com.romankaarayo.db.Person;
import com.romankaarayo.repository.PersonRepository;
import com.romankaarayo.util.AppConst;
import jersey.repackaged.com.google.common.util.concurrent.ExecutionError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BiometricService biometricService;

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
        Person createdPerson = personRepository.save(person);
        try {
            this.biometricService.enrollPerson(person);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return createdPerson;
    }

    public Iterable<Person> matchPerson(InputStream inputStream) throws IOException {
        String filName = saveImage(inputStream);
        try {
            List<Long> peropleIds = this.biometricService.matchFace(AppConst.getImageLocation() + filName);
            return this.personRepository.findAll(peropleIds);
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }
    }

    public String saveImage(InputStream inputStream) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString() + ".jpeg";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(AppConst.getImageLocation() + fileName));
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
