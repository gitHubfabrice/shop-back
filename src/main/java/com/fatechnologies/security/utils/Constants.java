package com.fatechnologies.security.utils;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String COMPTE_PRINCIPAL = "accountPrincipal";
    private Constants() {}
    public static String toUpperCase(String str){
        return  str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
