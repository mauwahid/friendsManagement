package com.mauwahid.imd.friendsManagement.controller;

import com.mauwahid.imd.friendsManagement.apimodel.request.AddFriendRequest;
import com.mauwahid.imd.friendsManagement.apimodel.request.CommonFriendRequest;
import com.mauwahid.imd.friendsManagement.apimodel.response.DefaultResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.ErrorResponse;
import com.mauwahid.imd.friendsManagement.apimodel.response.FriendListResponse;
import com.mauwahid.imd.friendsManagement.service.FriendshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class FriendController {

    private final Logger logger = LoggerFactory.getLogger(FriendController.class);


    @Autowired
    FriendshipService friendshipService;

    @Autowired
    FriendListResponse friendListResponse;

    @PostMapping("friend/add")
    public ResponseEntity<?> createFriendConnection(@RequestBody AddFriendRequest friendRequest){

        logger.debug("Friend Request : "+friendRequest);

        if(friendRequest==null || friendRequest.getFriends()==null){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(friendRequest.getFriends().length!=2){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_MORE_THAN_TWO), HttpStatus.BAD_REQUEST);

        }

        String[] emails = friendRequest.getFriends();

        if(friendshipService.isFriendship(emails[0], emails[1])){
            logger.debug("Relation checker");
        }

        friendshipService.addFriendRelation(emails[0], emails[1]);
        logger.debug("Friendship created");
        return new ResponseEntity(new DefaultResponse(), HttpStatus.OK);
    }

    @PostMapping("friend/common")
    public ResponseEntity<?> getCommonFriends(@RequestBody CommonFriendRequest commonFriendRequest){

        logger.debug("CommFriend Request : "+commonFriendRequest);

        if(commonFriendRequest==null || commonFriendRequest.getFriends()==null){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_DATA_FORMAT), HttpStatus.BAD_REQUEST);
        }

        if(commonFriendRequest.getFriends().length!=2){
            return new ResponseEntity(ErrorResponse.getErrorResponse(ErrorResponse.ERR_MORE_THAN_TWO), HttpStatus.BAD_REQUEST);

        }

        String[] emails = commonFriendRequest.getFriends();

        Set<String> mutualFriends =  friendshipService.getCommonFriends(emails[0], emails[1]);

        friendListResponse.setFriends(mutualFriends);
        friendListResponse.setCount(mutualFriends!=null?mutualFriends.size():0);

        return new ResponseEntity(friendListResponse, HttpStatus.OK);
    }



}
