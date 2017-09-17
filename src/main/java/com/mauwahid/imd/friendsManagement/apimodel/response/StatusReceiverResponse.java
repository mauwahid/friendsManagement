package com.mauwahid.imd.friendsManagement.apimodel.response;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StatusReceiverResponse extends DefaultResponse {

    private Set<String> recipients;

    public Set<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<String> recipients) {
        this.recipients = recipients;
    }
}
