package com.mauwahid.imd.friendsManagement.controller;

import com.mauwahid.imd.friendsManagement.apimodel.request.PostStoryRequest;
import com.mauwahid.imd.friendsManagement.apimodel.response.ErrorResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.StatusReceiverResponse;
import com.mauwahid.imd.friendsManagement.repository.PersonStoryRepository;
import com.mauwahid.imd.friendsManagement.service.PersonStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class StoryController {

    @Autowired
    PersonStoryService personStoryService;

    @Autowired
    StatusReceiverResponse statusReceiverResponse;

    @PostMapping("story/post")
    public ResponseEntity<?> postStory(@RequestBody PostStoryRequest postStoryRequest){

        if(postStoryRequest == null
                || postStoryRequest.getSender()== null || postStoryRequest.getText() == null){
            return new ResponseEntity<Object>(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        Set<String> recipients = personStoryService.postStatus(postStoryRequest.getSender(), postStoryRequest.getText());
        statusReceiverResponse.setRecipients(recipients);

        return new ResponseEntity<Object>(recipients,HttpStatus.OK);


    }
}
