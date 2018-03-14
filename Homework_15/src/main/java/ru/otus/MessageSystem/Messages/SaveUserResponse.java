package ru.otus.MessageSystem.Messages;

import ru.otus.MessageSystem.Address;

public class SaveUserResponse extends Response<Long>{

    public SaveUserResponse(Address from, Address to, Long result) {
        super(from, to, result);
    }
}
