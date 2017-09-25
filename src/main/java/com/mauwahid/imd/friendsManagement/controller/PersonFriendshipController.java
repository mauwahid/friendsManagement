package com.mauwahid.imd.friendsManagement.controller;

import com.mauwahid.imd.friendsManagement.apimodel.request.AddFriendRequest;
import com.mauwahid.imd.friendsManagement.apimodel.request.CommonFriendRequest;
import com.mauwahid.imd.friendsManagement.apimodel.response.DefaultResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.ErrorResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.FriendListResponse;
import com.mauwahid.imd.friendsManagement.service.PersonFriendshipService;
import com.mauwahid.imd.friendsManagement.utils.Common;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/friendship")
@Api(tags = "Friendship", description="Operations for friendship relation")
public class PersonFriendshipController {

    private final Logger logger = LoggerFactory.getLogger(PersonFriendshipController.class);

    @Autowired
    private PersonFriendshipService personFriendshipService;

    @Autowired
    private FriendListResponse friendListResponse;


    @ApiOperation(value = "Add Friendship Relation", response = DefaultResponse.class)
    @PostMapping("/add")
    public ResponseEntity createFriendConnection(@RequestBody AddFriendRequest friendRequest){

        logger.debug("Friend Request : "+friendRequest);

        if(friendRequest==null || friendRequest.getFriends()==null){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(friendRequest.getFriends().length!=2){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_NOT_TWO), HttpStatus.BAD_REQUEST);

        }

        String[] emails = friendRequest.getFriends();

        if(!Common.isValidEmail(emails[0]) || !Common.isValidEmail(emails[1])){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_WRONG_EMAIL_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(personFriendshipService.isFriendship(emails[0], emails[1])){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DUPLICATE_ADDFRIENDS), HttpStatus.BAD_REQUEST);
        }

        personFriendshipService.addFriendRelation(emails[0], emails[1]);
        logger.debug("Friendship created");
        return new ResponseEntity(new DefaultResponse(), HttpStatus.OK);
    }

    @ApiOperation(value = "Common Friends", response = FriendListResponse.class)
    @PostMapping("/common")
    public ResponseEntity getCommonFriends(@RequestBody CommonFriendRequest commonFriendRequest){

        logger.debug("CommFriend Request : "+commonFriendRequest);

        if(commonFriendRequest==null || commonFriendRequest.getFriends()==null){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(commonFriendRequest.getFriends().length!=2){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_NOT_TWO), HttpStatus.BAD_REQUEST);

        }

        if(commonFriendRequest.getFriends()[0].equalsIgnoreCase(commonFriendRequest.getFriends()[0]))
        {
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_SAME_EMAIL), HttpStatus.BAD_REQUEST);

        }

        if(!Common.isValidEmail(commonFriendRequest.getFriends()[0]) || !Common.isValidEmail(commonFriendRequest.getFriends()[1])){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_WRONG_EMAIL_FORMAT), HttpStatus.BAD_REQUEST);
        }

        String[] emails = commonFriendRequest.getFriends();

        Set<String> mutualFriends =  personFriendshipService.getCommonFriends(emails[0], emails[1]);

        friendListResponse.setFriends(mutualFriends);
        friendListResponse.setCount(mutualFriends!=null?mutualFriends.size():0);

        return new ResponseEntity(friendListResponse, HttpStatus.OK);
    }




}
