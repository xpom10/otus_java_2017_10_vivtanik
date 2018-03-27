package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;
import ru.otus.Server.AdminServlet;
import ru.otus.UserData.UserDataSet;

public class LoadUserAnswer extends MessageToAdminServlet {

    private UserDataSet user;

    public LoadUserAnswer(Address from, Address to, UserDataSet user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(AdminServlet adminServlet) {
        adminServlet.addUser(user);
    }
}
