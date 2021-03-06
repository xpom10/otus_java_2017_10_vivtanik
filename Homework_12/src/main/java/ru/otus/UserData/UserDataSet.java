package ru.otus.UserData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {

    @Column(name = "user")
    private String admin;

    @Column(name = "password")
    private String password;


    public UserDataSet() {
    }

    public UserDataSet(String admin, String password) {
        this.admin = admin;
        this.password = password;
    }

    public String getUser() {
        return admin;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return String.format("UserDataSet{name = '%s', password = %s}", admin, password);
    }

    @Override
    public boolean equals(Object obj) {
       UserDataSet a =  (UserDataSet) obj;
        return (admin.equals(a.getUser()) && password.equals(a.getPassword()));
    }

}
