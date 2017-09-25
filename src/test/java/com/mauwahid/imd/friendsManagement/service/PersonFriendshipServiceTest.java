package com.mauwahid.imd.friendsManagement.service;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonFriendshipServiceTest {


    Logger logger = LoggerFactory.getLogger(PersonFriendshipServiceTest.class);


    @Autowired
    private PersonFriendshipService personFriendshipService;

    private String email1;
    private String email2;
    private String email3;

    private Set<String> commons;

    @Before
    public void setUp() throws Exception {
        email1 = "test@gmail.com";
        email2 = "test2@gmail.com";
        email3 = "test3@gmail.com";

        commons = new HashSet<>();
        commons.add(email1);
    }

    @Test
    public void test1_AddFriendship() throws Exception {

        this.personFriendshipService.addFriendRelation(email1,email2);
        this.personFriendshipService.addFriendRelation(email2,email3);
        this.personFriendshipService.addFriendRelation(email1,email3);

    }

    @Test
    public void test2_IsFriendship() throws Exception{
        assertEquals(true,this.personFriendshipService.isFriendship(email1,email2));
        assertEquals(true,this.personFriendshipService.isFriendship(email1,email3));

    }

    @Test
    public void test3_IsFriendshipInverse() throws Exception{
        assertEquals(true, this.personFriendshipService.isFriendship(email2,email1));
        assertEquals(true,this.personFriendshipService.isFriendship(email3,email1));



    }






}