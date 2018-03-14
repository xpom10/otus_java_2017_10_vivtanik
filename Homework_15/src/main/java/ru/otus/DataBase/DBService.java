package ru.otus.DataBase;

import ru.otus.Cache.CacheEngine;
import ru.otus.Cache.CacheEngineImpl;
import ru.otus.MessageSystem.Addressee;
import ru.otus.UserData.UserDataSet;

import java.util.List;

public interface DBService extends Addressee {

    String getLocalStatus();

    long save(UserDataSet user);

    UserDataSet load(long id);

    List<UserDataSet> getAllUsers();

    UserDataSet getByName(String name);

    void shutdown();

    CacheEngine getCache();
}
