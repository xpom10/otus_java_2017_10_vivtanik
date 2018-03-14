package ru.otus.MessageSystem.Messages;

import ru.otus.DataBase.DBService;
import ru.otus.MessageSystem.Address;
import ru.otus.UserData.UserDataSet;

public class LoadUserRequest extends RequestToDB {

    private long id;

    public LoadUserRequest(Address from, Address to, long id) {
        super(from, to);
        this.id = id;
    }


    @Override
    public Response exec(DBService dbService) {
        UserDataSet user;
        try {
            user = dbService.load(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new LoadUserResponse(getFrom(),getTo(),user);
    }
}
