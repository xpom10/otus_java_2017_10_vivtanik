package ru.otus.DAO;

import ru.otus.UserData.UserDataSet;

import java.util.List;

public interface UserDAO {
    void save(UserDataSet user);

    UserDataSet load(long id);

    List<UserDataSet> getAllUsers();

    UserDataSet getByName(String name);
}
