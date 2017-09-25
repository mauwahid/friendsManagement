package com.mauwahid.imd.friendsManagement.apimodel.request;


import io.swagger.annotations.ApiModelProperty;

public class AddFriendRequest {



    @ApiModelProperty(required = true)
    private String[] friends;


    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }
}
