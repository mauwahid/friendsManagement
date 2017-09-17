package com.mauwahid.imd.friendsManagement.apimodel.response;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FriendListResponse extends DefaultResponse {

    private Set<String> friends;
    private int count;



    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
