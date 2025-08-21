
package com.bci.app;

public class ServiceConsts {

    private ServiceConsts(){}
    /**
     * Msg
     */
    public static final String MSG_USER_CREATED = "User created";
    public static final String MSG_PROCESS_SERVICE = "User %s successfully. %s: %s";
    public static final String MSG_ERROR_PROCESS_SERVICE = "User %s error, %s: %s. Message: %s";

    /**
     * Msg error
     */
    public static final String ERROR_FORMAT_PASSWORD = "Password format not allowed. The valid password must have: at least 8 characters, at least one uppercase letter, one lowercase letter, one number, and one special character";
    public static final String ERROR_FORMAT_EMAIL = "Email format not allowed. Example: aaaaaaa@dominio.cl";

    public static final String EXISTS_USER = "User exists in system.";
}
