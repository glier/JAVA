package server.chat.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.chat.User;

import java.util.List;

public class BaseAuthService implements AuthService {
    private static final Logger LOGGER = LogManager.getLogger(DBAuthService.class);

    private static final List<User> USERS = List.of(
            new User("login1", "pass1", "Oleg"),
            new User("login2", "pass2", "Alexey"),
            new User("login3", "pass3", "Peter")
    );

    @Override
    public void start() {
        LOGGER.info("Auth service has been started");
    }

    @Override
    public void stop() {
        LOGGER.info("Auth service has been finished");
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (User user : USERS) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }

        return null;
    }
}
