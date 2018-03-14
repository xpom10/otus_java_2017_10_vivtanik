package ru.otus.MessageSystem.Messages;

import ru.otus.DataBase.DBService;
import ru.otus.MessageSystem.Address;
import ru.otus.UserData.UserDataSet;

public class SaveUserRequest extends RequestToDB {

    public SaveUserRequest(Address from, Address to) {
        super(from, to);
    }

    private UserDataSet user;

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    @Override
    public Response exec(DBService dbService) {
        Long createdUserId;
        try {
            createdUserId = dbService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new SaveUserResponse(getFrom(),getTo(),createdUserId);
    }
}
