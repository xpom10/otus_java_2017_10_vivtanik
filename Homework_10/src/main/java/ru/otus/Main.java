package ru.otus;

import ru.otus.DataBase.DBService;
import ru.otus.DataBase.DBServiceHibernateImpl;
import ru.otus.UserData.AddressDataSet;
import ru.otus.UserData.PhoneDataSet;
import ru.otus.UserData.UserDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DBService dbService = new DBServiceHibernateImpl();

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
        List<UserDataSet> list;

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);
        dbService.save(user1);
        dbService.save(user2);
        dbService.save(user3);
        System.out.println("User with name Sven: " + dbService.getByName("Sven"));
        list = dbService.getAllUsers();
        userFromDB = dbService.load(3);

        for (UserDataSet userDataSet : list) {
            System.out.println("User from LIST: " + userDataSet.toString());
        }
        System.out.println("User from DB: " + userFromDB.toString());
    }
}
