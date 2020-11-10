package server.chat.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.chat.services.UserServices;

public class DBAuthService implements AuthService {
    private static final Logger LOGGER = LogManager.getLogger(DBAuthService.class);

    @Override
    public void start() {
        LOGGER.info("Auth service has been started");
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return UserServices.getUsernameByLoginAndPassword(login, password);
    }

    @Override
    public void stop() {
        LOGGER.info("Auth service has been finished");
    }
}
