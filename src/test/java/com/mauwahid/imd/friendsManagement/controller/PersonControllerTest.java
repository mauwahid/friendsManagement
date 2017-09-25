package com.mauwahid.imd.friendsManagement.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mauwahid.imd.friendsManagement.FriendsManagementApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class PersonControllerTest {


    @Autowired
    MockMvc mockMvc;


    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void friendList() throws Exception {

        String jsonReq = "{\n" +
                "\t\"email\":\"maulana@example.com\"\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/person/friends")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonReq)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());


    }

    @Test
    public void blockPerson() throws Exception {

        String jsonReq = "{\n" +
                "\t\"requestor\":\"maulana@example.com\",\n" +
                "\t\"target\":\"entah@example.com\"\n" +
                "}";


        mockMvc.perform(
                MockMvcRequestBuilders.post("/person/block")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonReq)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true)).andDo(print());

      }

    @Test
    public void subscribe() throws Exception {

        String jsonReq = "{\n" +
                "\t\"requestor\":\"maulana@example.com\",\n" +
                "\t\"target\":\"entah@example.com\"\n" +
                "}";


        mockMvc.perform(
                MockMvcRequestBuilders.post("/person/subscribe")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonReq)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true)).andDo(print());

    }


}