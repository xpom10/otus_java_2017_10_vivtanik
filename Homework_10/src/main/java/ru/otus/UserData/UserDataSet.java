package ru.otus.UserData;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet{

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToOne(cascade = CascadeType.ALL)
    private PhoneDataSet phone;


    public UserDataSet() {
    }

    public UserDataSet(String name, int age, AddressDataSet address, PhoneDataSet phone) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("UserDataSet{name = '%s', age = %s, %s, %s}",name,age,address,phone);
    }
}
