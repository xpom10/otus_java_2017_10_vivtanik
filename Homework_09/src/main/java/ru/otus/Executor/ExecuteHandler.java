package ru.otus.Executor;

import java.sql.PreparedStatement;
import java.sql.SQLException;


@FunctionalInterface
public interface ExecuteHandler {
    void accept(PreparedStatement statement) throws SQLException, IllegalAccessException;
}

