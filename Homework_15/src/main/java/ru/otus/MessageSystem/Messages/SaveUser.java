package ru.otus.MessageSystem.Messages;

import ru.otus.DataBase.DBService;
import ru.otus.MessageSystem.Address;
import ru.otus.UserData.UserDataSet;

public class SaveUser extends MessageToDatabase {

    private final UserDataSet user;
    public SaveUser(Address from, Address to, UserDataSet user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(DBService dbService) {
        System.out.println(user);
        long id = dbService.save(user);
        dbService.getMessageSystem().sendMessage(new SaveUserAnswer(getTo(),getFrom(),id));
    }
}
