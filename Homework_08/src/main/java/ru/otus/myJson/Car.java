package ru.otus.myJson;

import java.io.Serializable;

public class Car implements Serializable {

    private final Specification specification;
    private String brand;
    private final boolean bool;
    private final String model;
    private final Integer count;




    public Car(String brandCar, String modelCar, Integer countCar, Specification colors) {
        this.brand = brandCar;
        this.bool = true;
        this.model = modelCar;
        this.specification = colors;
        this.count = countCar;


    }

//    public String getBrand() {
//        return brand;
//    }

//    public String getModel() {
//        return model;
//    }
//
//    public Integer getCount() {
//        return count;
//    }
//
//    public Specification getSpecification() {
//        return specification;
//    }
}
