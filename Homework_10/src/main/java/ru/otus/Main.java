package ru.otus;

import ru.otus.DataBase.DBService;
import ru.otus.DataBase.DBServiceImpl;
import ru.otus.UserData.AddressDataSet;
import ru.otus.UserData.PhoneDataSet;
import ru.otus.UserData.UserDataSet;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DBService dbService = new DBServiceImpl();

        UserDataSet user1 = new UserDataSet("Mike",25,new AddressDataSet("Moscow"),new PhoneDataSet("9237482734"));
        UserDataSet user2 = new UserDataSet("Sven",29,new AddressDataSet("Paris"),new PhoneDataSet("4563423"));
        UserDataSet user3 = new UserDataSet("Harold",26,new AddressDataSet("Milan"),new PhoneDataSet("7456456"));
        UserDataSet userFromDB;
        List<UserDataSet> list;

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);
        dbService.save(user1);
        dbService.save(user2);
        dbService.save(user3);
        System.out.println("User with name Sven" + dbService.getByName("Sven"));
        list = dbService.getAllUsers();
        userFromDB = dbService.load(3);

        for (UserDataSet userDataSet : list) {
            System.out.println("User from LIST: " + userDataSet.toString());
        }
        System.out.println("User from DB: " + userFromDB.toString());
    }
}
