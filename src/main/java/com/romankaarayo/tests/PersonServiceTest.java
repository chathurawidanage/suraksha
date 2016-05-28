package com.romankaarayo.tests;

import com.romankaarayo.db.Person;
import com.romankaarayo.services.PersonService;
import junit.framework.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by krv on 5/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest extends TestCase{

    @Autowired
    private PersonService personService;

    @Before
    public void setUp() throws Exception {
        Person person = new Person();
        personService.save(person);
    }

    public void testAll() throws Exception {
        List<Person> list = personService.all();
        Assert.assertEquals(list.isEmpty(), false);
    }
}