package com.mauwahid.imd.friendsManagement.controller;

import com.mauwahid.imd.friendsManagement.apimodel.request.PostStoryRequest;
import com.mauwahid.imd.friendsManagement.apimodel.response.ErrorResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.StatusReceiverResponse;
import com.mauwahid.imd.friendsManagement.service.PersonStoryService;
import com.mauwahid.imd.friendsManagement.utils.Common;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/story")
@Api(tags = "Story", description="Operations for person'story")
public class PersonStoryController {

    @Autowired
    private PersonStoryService personStoryService;

    @Autowired
    private StatusReceiverResponse statusReceiverResponse;

    @ApiOperation(value = "Post Story", response = StatusReceiverResponse.class)
    @PostMapping("/post")
    public ResponseEntity postStory(@RequestBody PostStoryRequest postStoryRequest){

        if(postStoryRequest == null
                || postStoryRequest.getSender()== null || postStoryRequest.getText() == null){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(!Common.isValidEmail(postStoryRequest.getSender())){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_WRONG_EMAIL_FORMAT),HttpStatus.BAD_REQUEST);
        }

        Set<String> recipients = personStoryService.postStatus(postStoryRequest.getSender(), postStoryRequest.getText());
        statusReceiverResponse.setRecipients(recipients);

        return new ResponseEntity(statusReceiverResponse,HttpStatus.OK);


    }
}
