package com.romankaarayo.services;

import com.neurotec.biometrics.*;
import com.neurotec.biometrics.client.NBiometricClient;

import java.util.*;

import com.neurotec.images.NImage;
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

        // Subject initialization
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
        System.out.println("Template Creation OK");

        NBiometricTask enrollTask = biometricClient.createTask(EnumSet.of(NBiometricOperation.ENROLL), null);
        //// HACK
        UUID  uid = UUID.randomUUID();

        System.out.println();
        //Long i = Long.parseLong(uid.toString());
        Long i = 50L;
        List<String> ids = new ArrayList<>();
        subject.setId(new Long(i++).toString());
        ids.add(i.toString());
        enrollTask.getSubjects().add(subject);
        for (NSubject relatedSubject : subject.getRelatedSubjects()) {
            relatedSubject.setId(new Long(i++).toString());
            ids.add(i.toString());
            enrollTask.getSubjects().add(relatedSubject);
        }

        //List<Person> persons = null;
        biometricClient.performTask(enrollTask);
        List<Person> persons = null;
        List<NSubject> personsSus = null;
        if (enrollTask.getStatus() != NBiometricStatus.OK) {
            System.out.format("Enrollment was unsuccessful. Status: %s.\n", status);
           // return persons;
        }

        for (String id : ids) {
            NSubject su = new NSubject();
            su.setId(id);
            NBiometricStatus stat = biometricClient.get(su);
            //stat.
            System.out.println(stat.toString());
            //personsSus.add();
        }

        //biometricClient.get()
        persons = convertSubjectsToPersons(enrollTask.getSubjects());
        return persons;
    }

    private List<Person> convertSubjectsToPersons(NBiometricTask.SubjectCollection subjects){
        List<Person> persons = new ArrayList<>();
        try {
            for (NSubject sub : subjects) {
                Person person = new Person();
                person.setId(Long.parseLong(sub.getId()));
                sub.getFaces().get(0).getImage().save(AppConst.getImageLocation() + sub.getId()+".jpg");
                sub.getFaces().get(0).getImage().save(AppConst.getImageLocation() + sub.getId()+"2.jpg");
                try {
                    int i = 0;
                    for (NSubject subi : sub.getRelatedSubjects()) {
                        NImage ni = subi.getFaces().get(i).getImage();

                        ni.save(AppConst.getImageLocation() + sub.getId() + "__5.jpg");
                        i++;
                    }
                    //NImage ni = sub.getRelatedSubjects().get(0).getFaces().get(0).getImage();
                    //ni.save(AppConst.getImageLocation() + sub.getId()+".jpg");
                    //NImage ni = NImage.fromMemory( sub.getTemplateBuffer());

                    NImage ni = sub.getFaces().get(0).getImage();
                    ni.save(AppConst.getImageLocation() + sub.getId() + "_1" + ".jpg");

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                //ni.save(AppConst.getImageLocation() + sub.getId()+".jpg");

                System.out.println(sub.getId());
                person.setImage(sub.getId());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return persons;
    }
}
