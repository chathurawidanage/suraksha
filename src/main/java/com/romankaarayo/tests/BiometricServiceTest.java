package com.romankaarayo.tests;

import com.romankaarayo.db.Person;
import com.romankaarayo.services.BiometricService;
import com.romankaarayo.util.LibraryManager;
import junit.framework.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.net.URL;

import com.sun.jna.Platform;

/**
 * Created by krv on 5/28/2016.
 */
public class BiometricServiceTest extends TestCase {
    private BiometricService biometricService = new BiometricService();

    protected void setUp() {
    }

    @Test
    public void testEnrollment() throws Exception {
        LibraryManager.initLibraryPath();
        System.out.println("Test Started");
        Person testPerson = new Person();
        testPerson.setId(20);
        final URL resource = getClass().getResource("/images/krv.jpg");
        System.out.println(resource.getPath());
        if (Platform.isWindows()) {
            testPerson.setImage("krv.jpg");
        } else if (Platform.isLinux()) {
            testPerson.setImage(getClass().getResource("krv.jpg").getPath());
        }

        Long out = biometricService.enrollPerson(testPerson);
        System.out.println(out);
        assertTrue(out != -1L);
    }
}