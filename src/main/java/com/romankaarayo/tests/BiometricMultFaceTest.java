package com.romankaarayo.tests;

import com.romankaarayo.db.Person;
import com.romankaarayo.services.BiometricMultFace;
import com.romankaarayo.services.BiometricService;
import com.romankaarayo.util.LibraryManager;
import com.sun.jna.Platform;
import junit.framework.TestCase;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by krv on 5/29/2016.
 */
public class BiometricMultFaceTest extends TestCase {

    private BiometricMultFace biometricService = new BiometricMultFace();

    @Test
    public void testEnrollment() throws Exception {
        LibraryManager.initLibraryPath();
        List<Person> persons = null ;
        if (Platform.isWindows()) {
            persons = biometricService.enrollMultiFaceImage("E:\\SDK\\suraksha\\src\\main\\resources\\images\\multi3.jpg");
        } else if (Platform.isLinux()) {
            persons = biometricService.enrollMultiFaceImage("multi2.jpg");
        }

/*        for (Person person : persons) {
            System.out.println(person.toString());
        }*/
    }
}