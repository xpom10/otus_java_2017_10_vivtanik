package ru.otus.myJson;

import java.util.ArrayList;
import java.util.List;

public class Specification {
    private String color;
    private String[] details;
    private Integer[] countOfDetails;
    private List<String> layers;

    public Specification() {
        this.color = "blue";
        this.details = new String[]{"hood","bumper","fender"};
        this.countOfDetails = new Integer[]{1,2,3,4,5};
        this.layers = new ArrayList<>();
        layers.add("thin");
        layers.add("thick");
    }
}
