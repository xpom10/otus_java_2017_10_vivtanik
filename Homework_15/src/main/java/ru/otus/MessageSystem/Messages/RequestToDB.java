package ru.otus.MessageSystem.Messages;

import ru.otus.DataBase.DBService;
import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.Addressee;

public abstract class RequestToDB extends Request {

    public RequestToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public Response exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            return exec(addressee);
        }
        throw new IllegalStateException("Адресат не может обработать данное сообщение");
    }

    public abstract Response exec(DBService dbService);
}
