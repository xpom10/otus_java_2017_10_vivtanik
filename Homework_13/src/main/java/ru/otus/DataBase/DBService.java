package ru.otus.DataBase;

import ru.otus.Cache.CacheEngineImpl;
import ru.otus.UserData.UserDataSet;

import java.util.List;

public interface DBService {

    String getLocalStatus();

    void save(UserDataSet user);

    UserDataSet load(long id);

    List<UserDataSet> getAllUsers();

    UserDataSet getByName(String name);

    void shutdown();

    CacheEngineImpl getCache();
}
