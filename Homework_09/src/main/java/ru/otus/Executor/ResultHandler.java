package ru.otus.Executor;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultHandler<T> {
    T handle(ResultSet result) throws SQLException, IllegalAccessException, InstantiationException;
}
