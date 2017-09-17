package com.mauwahid.imd.friendsManagement.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    public static Set<String> getEmailsFromText(String text){
        Set<String> emails = new HashSet<>();
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(text);
        while (m.find()) {
            emails.add(m.group());
        }

        return emails;
    }
}
