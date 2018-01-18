package ru.otus;

import ru.otus.Cache.CacheEngineImpl;
import ru.otus.DataBase.DBServiceHibernateImpl;
import ru.otus.UserData.AddressDataSet;
import ru.otus.UserData.DataSet;
import ru.otus.UserData.PhoneDataSet;
import ru.otus.UserData.UserDataSet;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DBServiceHibernateImpl dbService = new DBServiceHibernateImpl();

        CacheEngineImpl<Long,DataSet> cacheEngine = new CacheEngineImpl<>(2,1000,1000,false);
        dbService.registerCache(cacheEngine);


        List<String> numbers = new ArrayList<>();
        numbers.add("123");
        numbers.add("456");
        numbers.add("789");

        UserDataSet user1 = new UserDataSet("Mike", 25, new AddressDataSet("Moscow"));
        numbers.forEach(n -> user1.addPhone(new PhoneDataSet(n)));
        numbers.remove("123");

        UserDataSet user2 = new UserDataSet("Sven", 29, new AddressDataSet("Paris"));
        numbers.forEach(n -> user2.addPhone(new PhoneDataSet(n)));
        numbers.remove("456");

        UserDataSet user3 = new UserDataSet("Harold", 26, new AddressDataSet("Milan"));
        numbers.forEach(n -> user3.addPhone(new PhoneDataSet(n)));

        UserDataSet userFromDB;

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        dbService.save(user1);
        dbService.save(user2);
        dbService.save(user3);

        userFromDB = dbService.load(2);
        System.out.println("User: " + userFromDB.toString());
        userFromDB = dbService.load(3);
        System.out.println("User: " + userFromDB.toString());
        userFromDB = dbService.load(1);
        System.out.println("User: " + userFromDB.toString());
        userFromDB = dbService.load(1);
        System.out.println("User: " + userFromDB.toString());
    }



}
