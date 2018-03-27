package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;
import ru.otus.Server.SignInServlet;

public class SaveUserAnswer extends MessageToSigninServlet {

    private final long id;
    public SaveUserAnswer(Address from, Address to, long id) {
        super(from, to);
        this.id = id;
    }

    @Override
    public void exec(SignInServlet signInServlet) {
        System.out.println("********** " + id);
    }

}
