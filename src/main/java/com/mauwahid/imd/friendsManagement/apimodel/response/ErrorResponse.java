package com.mauwahid.imd.friendsManagement.apimodel.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

public class ErrorResponse extends DefaultResponse {

    @JsonIgnore
    public static String ERR_DATA_FORMAT = "Error : JSON/Data Format error";

    @JsonIgnore
    public static String ERR_NOT_TWO = "Error : email relation must be two";

    @JsonIgnore
    public static String ERR_DUPLICATE_SUBSCIRBE = "Error : This target has been subscribed before(duplicate request)";

    @JsonIgnore
    public static String ERR_DUPLICATE_BLOCK = "Error : This target has been blocked before(duplicate request)";

    @JsonIgnore
    public static String ERR_DUPLICATE_ADDFRIENDS = "Error : These emails has friendship (duplicate request)";

    @JsonIgnore
    public static String ERR_WRONG_EMAIL_FORMAT = "Error : Email format not valid";

    public static String ERR_SAME_EMAIL = "Error : Email must be different";


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
