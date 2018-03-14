package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.Message;

public abstract class Response<T> extends Message {
    private T result;

    public Response(Address from, Address to, T result) {
        super(from, to);
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}
