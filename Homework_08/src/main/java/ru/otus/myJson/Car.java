package ru.otus.myJson;

import java.io.Serializable;

public class Car implements Serializable {

    private final String brand;
    private final String model;
    private final Color colors;
    private final Integer count;



    public Car(String brandCar, String modelCar, Integer countCar, Color colors) {
        this.brand = brandCar;
        this.model = modelCar;
        this.colors = colors;
        this.count = countCar;


    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getCount() {
        return count;
    }

    public Color getColors() {
        return colors;
    }
}
