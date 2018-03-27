package ru.otus.MessageSystem.Messages;

import ru.otus.DataBase.DBService;
import ru.otus.MessageSystem.Address;
import ru.otus.UserData.UserDataSet;

public class LoadUser extends MessageToDatabase {

    private final String name;

    public LoadUser(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    @Override
    public void exec(DBService dbService) {
        UserDataSet user = dbService.getByName(name);
        System.out.println("user load " + user);
        dbService.getMessageSystem().sendMessage(new LoadUserAnswer(getTo(),getFrom(),user));
    }
}
