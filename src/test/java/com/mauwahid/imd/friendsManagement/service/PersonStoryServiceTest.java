package com.mauwahid.imd.friendsManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonStoryServiceTest {

    @Autowired
    PersonStoryService personStoryService;

    String email = "tes3t@gmail.com";

    @Test
    public void postStatus() throws Exception {
        this.personStoryService.postStatus(email,"test status");
    }

}