package com.romankaarayo.tests;
import com.romankaarayo.db.Person;
import com.romankaarayo.services.BiometricService;
import junit.framework.*;

import java.net.URL;


/**
 * Created by krv on 5/28/2016.
 */
public class BiometricServiceTest extends TestCase {
    private BiometricService biometricService = new BiometricService();

    protected void setUp(){

    }

    public void testEnrollment() throws Exception {
        System.out.println("Test Started");
        Person testPerson = new Person();
        testPerson.setId(20);
        System.out.println(getClass().getResource("/images/krv.jpg").getPath());
        /*final URL resource = ;
        System.out.println(resource.getFile());*/
        testPerson.setImage(getClass().getResource("/images/krv.jpg").getPath());
        Long out = biometricService.enrollPerson(testPerson);
        assertTrue(out != -1L);
    }
}