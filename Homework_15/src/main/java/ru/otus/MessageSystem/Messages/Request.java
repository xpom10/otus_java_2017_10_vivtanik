package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.Addressee;
import ru.otus.MessageSystem.Message;

public abstract class Request<T> extends Message {

    public abstract Response exec(Addressee addressee);

    public Request(Address from, Address to) {
        super(from, to);
    }
}
