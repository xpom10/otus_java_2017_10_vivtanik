package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.Addressee;
import ru.otus.Server.AdminServlet;
import ru.otus.UserData.UserDataSet;

public abstract class MessageToAdminServlet extends Message {

    public MessageToAdminServlet(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof AdminServlet) {
            exec((AdminServlet) addressee);
        }
    }

    public abstract void exec(AdminServlet adminServlet);

}
