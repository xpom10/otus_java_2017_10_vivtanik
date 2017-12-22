package ru.otus.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

   public void execQuery(String sqlQuery) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        }
   }

   public <T> T execQuery(String sqlQuery,ResultHandler<T> handler) throws SQLException, InstantiationException, IllegalAccessException {
       try(Statement statement = connection.createStatement()) {
           statement.execute(sqlQuery);
           ResultSet result = statement.getResultSet();
           return handler.handle(result);
       }
   }
}
