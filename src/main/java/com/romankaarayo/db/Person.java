package com.romankaarayo.db;

import com.romankaarayo.db.constants.Sex;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * @author Chathura Widanage
 */
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex = Sex.UNDEFINED;




}



