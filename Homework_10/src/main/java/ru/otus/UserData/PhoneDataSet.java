package ru.otus.UserData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet {

    @Column(name = "phone")
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }



    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("PhoneDataSet{number = '%s'}",number);
    }

}
