package com.mauwahid.imd.friendsManagement.apimodel.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

public class ErrorResponse extends DefaultResponse {

    @JsonIgnore
    public static String ERR_DATA_FORMAT = "Error : JSON/Data Format error";

    @JsonIgnore
    public static String ERR_MORE_THAN_TWO = "Error : email relation cannot more than two";

    public static ErrorResponse getErrorResponse(String statusDesc){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setStatusDesc(statusDesc);

        return errorResponse;
    }

    private String statusDesc;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
