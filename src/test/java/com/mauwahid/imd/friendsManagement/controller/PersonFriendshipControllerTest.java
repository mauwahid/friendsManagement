package com.mauwahid.imd.friendsManagement.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.imd.friendsManagement.FriendsManagementApplication;
import com.mauwahid.imd.friendsManagement.apimodel.request.AddFriendRequest;
import com.mauwahid.imd.friendsManagement.service.PersonFriendshipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = FriendsManagementApplication.class)
public class PersonFriendshipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }


    @Test
    public void createFriendConnection() throws Exception {

        String jsonReq = "{\n" +
                "\t\"friends\": [\"mail1@example.com\", \"mail3@example.com\"]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/friendship/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonReq)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true)).andDo(print());

    }


    @Test
    public void getCommonFriends() throws Exception {
        String jsonReq = "{\n" +
                "\t\"friends\": [\"mail1@example.com\", \"mail3@example.com\"]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/friendship/common")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonReq)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true)).andDo(print());

    }



}