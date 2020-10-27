package clientserver;

public enum CommandType {
    AUTH,
    AUTH_OK,
    AUTH_ERROR,
    AUTH_TIMEOUT,
    PRIVATE_MESSAGE,
    PUBLIC_MESSAGE,
    INFO_MESSAGE,
    ERROR,
    END,
    UPDATE_USERS_LIST,
}
