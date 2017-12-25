package ru.otus.UserData;

import javax.persistence.Entity;

@Entity
public abstract class DataSet {
    long id;

    public long getId() {
        return id;
    }
}
