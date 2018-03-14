package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;
import ru.otus.UserData.UserDataSet;

public class LoadUserResponse extends Response<UserDataSet> {

    public LoadUserResponse(Address from, Address to, UserDataSet result) {
        super(from, to, result);
    }
}
