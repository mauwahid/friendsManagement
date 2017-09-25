package com.mauwahid.imd.friendsManagement.service;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonServiceTest {


    @Autowired
    private PersonService personService;

    @Autowired
    private PersonFriendshipService personFriendshipService;


    private String email1;
    private String email2;



    @Before
    public void setUp() throws Exception {
        email1 = "test@gmail.com";
        email2 = "test2@gmail.com";
    }

    @Test
    public void getFriendList() throws Exception {
        this.personService.getFriendList(email1);
    }


    @Test
    public void blockPerson() throws Exception {
       this.personService.blockPerson(email1,email2);
    }

    @Test
    public void subscribe() throws Exception {
        this.personService.subscribe(email1,email2);

    }



}