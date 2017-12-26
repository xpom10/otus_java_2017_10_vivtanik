package ru.otus.UserData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet {

    @Column(name = "address")
    private String address;

    public AddressDataSet() {
    }

    public AddressDataSet(String address) {
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("AddressDataSet{address = '%s'}",address);
    }
}
