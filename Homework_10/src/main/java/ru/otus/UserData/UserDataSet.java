package ru.otus.UserData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet{
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    private AddressDataSet address;
    private PhoneDataSet phone;


    public UserDataSet() {
    }

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("UserDataSet{name = '%s', age = %s}",name,age);
    }
}
