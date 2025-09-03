package com.bankofjordan.training;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Pattern;

public class ContactInfo {
    public static Pattern MOBILE_NO_PATTERN = Pattern.compile("[0-9]{10}");

    private final String mobileNo;
    private final String email;

    public ContactInfo(String mobileNo, String email) {
        if(!EmailValidator.getInstance().isValid(email))
            throw new IllegalArgumentException("Invalid email address");
        if(!isValidMobile(mobileNo))
            throw new IllegalArgumentException("Invalid mobile number");
        this.mobileNo = mobileNo;
        this.email = email;
    }

    private boolean isValidMobile(String mobileNo) {
        return mobileNo != null
                && MOBILE_NO_PATTERN.matcher(mobileNo).matches();
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }
}
