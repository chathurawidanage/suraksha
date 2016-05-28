package com.romankaarayo.services;

import com.neurotec.biometrics.*;
import com.neurotec.biometrics.client.NBiometricClient;

import java.util.*;

import com.romankaarayo.db.Person;
import com.romankaarayo.util.AppConst;
import com.romankaarayo.util.LibraryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by krv on 5/28/2016.
 */
public class BiometricMultFace {
    private final Logger logger = LogManager.getLogger(BiometricMultFace.class);

    public BiometricMultFace() {
        LibraryManager.initLibraryPath();
    }

    // ===========================================================
    // Public methods
    // ===========================================================
    public List<Person> enrollMultiFaceImage(String imagePath){
        return enrollFromMultiImage(imagePath);
    }
    // ===========================================================
    // Private methods
    // ===========================================================

    private List<Person> enrollFromMultiImage(String imagePath) {
        NBiometricClient biometricClient = FaceTools.getInstance().getClient();
        NSubject subject = new NSubject();
        subject.setId(imagePath);
        subject.setMultipleSubjects(true);
        NFace face = new NFace();
        face.setFileName(imagePath);
        subject.getFaces().add(face);

        NBiometricStatus status = biometricClient.createTemplate(subject);
        if (status != NBiometricStatus.OK) {
            System.out.format("Template creation was unsuccessful. Status: %s\n.", status);
            return null;
        }

        NBiometricTask enrollTask = biometricClient.createTask(EnumSet.of(NBiometricOperation.ENROLL), null);
        //// HACK
        UUID  uid = UUID.randomUUID();

        System.out.println();
        Long i = Long.parseLong(uid.toString());

        subject.setId(new Long(i++).toString());
        enrollTask.getSubjects().add(subject);
        for (NSubject relatedSubject : subject.getRelatedSubjects()) {
            relatedSubject.setId(new Long(i++).toString());
            enrollTask.getSubjects().add(relatedSubject);
        }
        List<Person> persons = convertSubjectsToPersons(enrollTask.getSubjects());
        biometricClient.performTask(enrollTask);

        if (enrollTask.getStatus() != NBiometricStatus.OK) {
            System.out.format("Enrollment was unsuccessful. Status: %s.\n", status);
            return null;
        }
        return persons;
    }

    private List<Person> convertSubjectsToPersons(NBiometricTask.SubjectCollection subjects){
        List<Person> persons = new ArrayList<>();
        try {
            for (NSubject sub : subjects) {
                Person person = new Person();
                person.setId(Long.parseLong(sub.getId()));
                sub.getFaces().get(0).getImage().save(AppConst.getImageLocation() + sub.getId());
                person.setImage(sub.getId());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return persons;
    }
}
