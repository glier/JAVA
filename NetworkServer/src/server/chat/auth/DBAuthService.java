package server.chat.auth;

import server.chat.services.UserServices;

public class DBAuthService implements AuthService {
    @Override
    public void start() {
        System.out.println("Auth service has been started");
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return UserServices.getUsernameByLoginAndPassword(login, password);
    }

    @Override
    public void stop() {
        System.out.println("Auth service has been finished");
    }
}
