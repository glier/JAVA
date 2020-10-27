package server.chat.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JDBCRepository {
    ResultSet select(String sql) throws SQLException;

    int update(String sql) throws SQLException;

    int insert(String sql) throws SQLException;

    int delete(String sql) throws SQLException;

    void connect();

    void close();
}
