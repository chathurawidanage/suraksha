package com.romankaarayo.services;
/**
 * @author KRV
 */

import com.neurotec.biometrics.*;
import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.images.NImage;
import com.romankaarayo.db.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;


public class BiometricService {
    private final Logger logger = LogManager.getLogger(BiometricService.class);

    public BiometricService() {
    }

    // ===========================================================
    // Public methods
    // ===========================================================
    public void enrollPerson(Person person) {
        Long result = enrollFromImage(person.getId(), person.getImage());
    }

    public ArrayList<Long> matchFace(Person person) {
         ArrayList<Long> result = matchFace(person.getImage());
        return result;
    }


    // ===========================================================
    // Private methods
    // ===========================================================

    private Long enrollFromImage(Long id, String imagePath) {
        NBiometricClient client = FaceTools.getInstance().getClient();
        NSubject subject = new NSubject();
        try {
            NImage image = NImage.fromFile(imagePath);
            NFace face = new NFace();
            face.setImage(image);
            subject.setId(id.toString());
            subject.getFaces().add(face);
            NBiometricTask enrollTask = client.createTask(EnumSet.of(NBiometricOperation.ENROLL), subject);

            client.performTask(enrollTask);

            if (enrollTask.getStatus() != NBiometricStatus.OK) {
                System.out.format("Enrollment was unsuccessful. Status: %s.\n", enrollTask.getStatus());
                logger.debug(String.format("Enrollment was unsuccessful. Status: %s.\n", enrollTask.getStatus()));
                return -1L;
            } else {
                logger.debug("Enrollment successful");
                return Long.parseLong(subject.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug(String.format(e.toString());
            return -1L;
        }
    }

    private ArrayList<Long> matchFace(String imagePath) {
        NBiometricClient client = FaceTools.getInstance().getClient();
        NSubject probeSubject = new NSubject();
        ArrayList<Long> matchingResults = new ArrayList<>();
        try {
            NImage image = NImage.fromFile(imagePath);

            NFace face = new NFace();
            face.setImage(image);
            probeSubject.getFaces().add(face);
            NBiometricStatus status = client.identify(probeSubject);

            if (status == NBiometricStatus.OK) {

                for (NMatchingResult result : probeSubject.getMatchingResults()) {
                    System.out.format("Matched with ID: '%s' with score %d\n", result.getId(), result.getScore());
                    matchingResults.add(Long.parseLong(result.getId()));
                }
            } else if (status == NBiometricStatus.MATCH_NOT_FOUND) {
                System.out.format("Match not found");
            } else {
                System.out.format("Identification failed. Status: %s\n", status);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return matchingResults;
        }
        return matchingResults;
    }
}