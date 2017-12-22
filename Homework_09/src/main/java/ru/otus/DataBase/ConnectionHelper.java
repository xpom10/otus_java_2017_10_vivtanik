package ru.otus.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionHelper {

    static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = "jdbc:mysql://" +
                    "localhost:" +
                    "3306/" +
                    "db_example?" +
                    "user=xpom10&" +
                    "password=Passw0rd&" +
                    "useSSL=false";

            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
