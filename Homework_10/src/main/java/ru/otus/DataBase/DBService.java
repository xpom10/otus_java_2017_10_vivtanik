package ru.otus.DataBase;

import ru.otus.UserData.DataSet;
import ru.otus.UserData.UserDataSet;

import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable{
    String getMetaData();

    void prepareTables() throws SQLException;

    <T extends DataSet> void save(T user) throws SQLException, IllegalAccessException, NoSuchFieldException;

    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException;

    List<UserDataSet> getAllUsers() throws SQLException, IllegalAccessException, InstantiationException;

    <T extends DataSet> long getId(String name) throws SQLException, IllegalAccessException, InstantiationException;

    void deleteTables() throws SQLException;
}
