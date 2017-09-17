package com.mauwahid.imd.friendsManagement.controller;

import com.mauwahid.imd.friendsManagement.apimodel.request.BlockFriendRequest;
import com.mauwahid.imd.friendsManagement.apimodel.request.FriendListRequest;
import com.mauwahid.imd.friendsManagement.apimodel.request.SubscribeRequest;
import com.mauwahid.imd.friendsManagement.apimodel.response.DefaultResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.ErrorResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.FriendListResponse;
import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(PersonController.class);


    @Autowired
    FriendListResponse friendListResponse;

    @Autowired
    PersonService personService;

    @PostMapping("person/friends")
    public ResponseEntity friendList(@RequestBody FriendListRequest friendList){

        logger.debug("Friend Request : "+friendList);

        if(friendList==null || friendList.getEmail().equalsIgnoreCase("")){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        Set<Person> people = personService.getFriendList(friendList.getEmail());
        Set<String> friendsEmail = people.stream().map(
                data -> data.getEmail()
        ).collect(Collectors.toSet());

        friendListResponse.setFriends(friendsEmail);
        friendListResponse.setCount(friendsEmail.size());

        logger.debug("Friends' email collected");
        return new ResponseEntity(friendListResponse, HttpStatus.OK);
    }

    @PostMapping("person/block")
    public ResponseEntity blockPerson(@RequestBody BlockFriendRequest blockRequest){
        if(blockRequest==null || blockRequest.getRequestor().equalsIgnoreCase("")
                || blockRequest.getTarget().equalsIgnoreCase("")){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        personService.blockPerson(blockRequest.getRequestor(),blockRequest.getTarget());

        return new ResponseEntity(new DefaultResponse(),HttpStatus.OK);
    }

    @PostMapping("person/subscribe")
    public ResponseEntity subscribe(@RequestBody SubscribeRequest subscribeRequest){
        if(subscribeRequest==null || subscribeRequest.getRequestor().equalsIgnoreCase("")
                || subscribeRequest.getTarget().equalsIgnoreCase("")){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        personService.subscribe(subscribeRequest.getRequestor(),subscribeRequest.getTarget());

        return new ResponseEntity(new DefaultResponse(),HttpStatus.OK);
    }

}
