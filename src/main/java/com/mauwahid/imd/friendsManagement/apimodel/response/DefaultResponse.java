package com.mauwahid.imd.friendsManagement.apimodel.response;

import org.springframework.stereotype.Component;

@Component
public class DefaultResponse {

    private boolean success = true;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
