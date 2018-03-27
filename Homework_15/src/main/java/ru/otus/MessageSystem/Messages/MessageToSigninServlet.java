package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.Addressee;
import ru.otus.Server.SignInServlet;

public abstract class MessageToSigninServlet extends Message {

    public MessageToSigninServlet(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof SignInServlet) {
            exec((SignInServlet) addressee);
        }
    }

    public abstract void exec(SignInServlet signInServlet);

}
