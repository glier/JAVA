package server.chat.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.chat.repository.JDBCRepository;
import server.chat.repository.SQLiteRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServices {
    private static final Logger LOGGER = LogManager.getLogger(UserServices.class);
    private static JDBCRepository repo = new SQLiteRepository();

    public static String getUsernameByLoginAndPassword(String login, String password) {
        String username = null;
        try {
            repo.connect();
            ResultSet result = repo.select(String.format(
                    "SELECT username " +
                    "FROM users " +
                    "WHERE login='%s' AND password='%s'"
                    , login
                    , password));
            if (result != null) {
                username = result.getString("username");
                result.close();
            }
            repo.close();
        } catch (SQLException throwables) {
            LOGGER.error("SQLException", throwables);
        }
        return username;
    }
}
