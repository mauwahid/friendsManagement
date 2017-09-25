package com.mauwahid.imd.friendsManagement.controller;

import com.mauwahid.imd.friendsManagement.apimodel.request.BlockFriendRequest;
import com.mauwahid.imd.friendsManagement.apimodel.request.FriendListRequest;
import com.mauwahid.imd.friendsManagement.apimodel.request.SubscribeRequest;
import com.mauwahid.imd.friendsManagement.apimodel.response.DefaultResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.ErrorResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.FriendListResponse;
import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.service.PersonService;
import com.mauwahid.imd.friendsManagement.utils.Common;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
@Api(tags = "Person",  description="Operations for personal relation")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private FriendListResponse friendListResponse;

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @ApiOperation(value = "Friend List", response = FriendListResponse.class)
    @PostMapping("/friends")
    public ResponseEntity friendList(@RequestBody FriendListRequest friendList){

        logger.debug("Friend Request : "+friendList);

        if(friendList==null || friendList.getEmail().equalsIgnoreCase("")){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(!Common.isValidEmail(friendList.getEmail())){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_WRONG_EMAIL_FORMAT), HttpStatus.BAD_REQUEST);

        }

        Set<Person> people = personService.getFriendList(friendList.getEmail());

        Set<String> friendsEmail = new HashSet<>();
        if(people!=null){
            friendsEmail =  people.stream().map(
                    data -> data.getEmail()
            ).collect(Collectors.toSet());

        }


        friendListResponse.setFriends(friendsEmail);
        friendListResponse.setCount(friendsEmail.size());

        logger.debug("Friends' email collected");
        return new ResponseEntity(friendListResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Block Person", response = DefaultResponse.class)
    @PostMapping("/block")
    public ResponseEntity blockPerson(@RequestBody BlockFriendRequest blockRequest){

        if(blockRequest==null || blockRequest.getRequestor().equalsIgnoreCase("")
                || blockRequest.getTarget().equalsIgnoreCase("")){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(blockRequest.getRequestor().equalsIgnoreCase(blockRequest.getTarget())){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_SAME_EMAIL), HttpStatus.BAD_REQUEST);

        }

        if(personService.hasBlocked(blockRequest.getRequestor(),blockRequest.getTarget())){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DUPLICATE_BLOCK), HttpStatus.BAD_REQUEST);

        }

        if(blockRequest.getRequestor().equalsIgnoreCase(blockRequest.getTarget()))
        {
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_SAME_EMAIL), HttpStatus.BAD_REQUEST);

        }

        personService.blockPerson(blockRequest.getRequestor(),blockRequest.getTarget());

        return new ResponseEntity(new DefaultResponse(),HttpStatus.OK);
    }

    @ApiOperation(value = "Subscribe To Person", response = DefaultResponse.class)
    @PostMapping("/subscribe")
    public ResponseEntity subscribe(@RequestBody SubscribeRequest subscribeRequest){
        if(subscribeRequest==null || subscribeRequest.getRequestor().equalsIgnoreCase("")
                || subscribeRequest.getTarget().equalsIgnoreCase("")){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(subscribeRequest.getRequestor().equalsIgnoreCase(subscribeRequest.getTarget())){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_SAME_EMAIL), HttpStatus.BAD_REQUEST);

        }



        if(personService.hasSubscribe(subscribeRequest.getRequestor(),subscribeRequest.getTarget())){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DUPLICATE_SUBSCIRBE), HttpStatus.BAD_REQUEST);

        }

        personService.subscribe(subscribeRequest.getRequestor(),subscribeRequest.getTarget());

        return new ResponseEntity(new DefaultResponse(),HttpStatus.OK);
    }


}
