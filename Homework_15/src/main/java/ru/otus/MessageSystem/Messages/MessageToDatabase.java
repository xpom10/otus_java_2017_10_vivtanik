package ru.otus.MessageSystem.Messages;

import ru.otus.DataBase.DBService;
import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.Addressee;
import ru.otus.UserData.UserDataSet;

public abstract class MessageToDatabase extends Message {


    public MessageToDatabase(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}
