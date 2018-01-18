package ru.otus.UserData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneDataSet> phones = new ArrayList<>();


    public UserDataSet() {
    }

    public UserDataSet(String name, int age, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addPhone(PhoneDataSet ph) {
        this.phones.add(ph);
        ph.setUser(this);
    }

    @Override
    public String toString() {
        return String.format("UserDataSet{name = '%s', age = %s, %s, %s}", name, age, address, phones);
    }
}
