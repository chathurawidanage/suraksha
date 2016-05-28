package com.romankaarayo.tests;

import com.romankaarayo.db.Person;
import com.romankaarayo.services.BiometricMultFace;
import com.romankaarayo.services.BiometricService;
import com.romankaarayo.util.LibraryManager;
import com.sun.jna.Platform;
import junit.framework.TestCase;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by krv on 5/29/2016.
 */
public class BiometricMultFaceTest extends TestCase {

    private BiometricMultFace biometricService = new BiometricMultFace();

    @Test
    public void testEnrollment() throws Exception {
        LibraryManager.initLibraryPath();

        if (Platform.isWindows()) {
            biometricService.enrollMultiFaceImage("multi2.jpg");
        } else if (Platform.isLinux()) {
            biometricService.enrollMultiFaceImage("multi2.jpg");
        }
    }
}