package ru.otus.UserData;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserDataSet user;

    @Column(name = "number")
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }


    public String getNumber() {
        return number;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("PhoneDataSet{number = '%s'}", number);
    }

}
