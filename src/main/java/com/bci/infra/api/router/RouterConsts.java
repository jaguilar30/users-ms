package com.bci.infra.api.router;

public class RouterConsts {

    private RouterConsts(){}
    /**
     * componentes
     */
    public static final String COMPONENT_SCAN = "com.bci";

    /**
     * Controller config
     */
    public static final String API = "Users";
    public static final String CROSS_ORIGIN = "*";
    public static final String CONTROLLER_PATH = "/users";

    /**
     * operaciones o metodos
     */
    public static final String API_OPERATION_CREATE_USER = "Create a new user";

    /**
     * descripcion de las operaciones o metodos
     */
    public static final String NOTE_API_OPERATION_CREATE_USER = "In charge of create a new user.";

    /**
     * mensajes de respuesta de acuerdo al codigo http
     */
    public static final String API_RESPONSE_COD_200 = "successful process";
    public static final String API_RESPONSE_COD_400 = "Some parameter is missing in the header";
    public static final String API_RESPONSE_COD_404 = "source not found.";
    public static final String API_RESPONSE_COD_422 = "Functional error in the application";
    public static final String API_RESPONSE_COD_500 = "Unknown error";

    /**
     * swagger param
     */
    public static final String API_PARAM_REQUEST_CREATE_USER = "Body mapped to CreateUserDto.";

    /**
     * messages
     */
    public static final String MSG_PROCESS = "%s %s user: %s.";
}
