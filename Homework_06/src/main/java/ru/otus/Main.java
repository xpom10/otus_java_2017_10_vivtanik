package ru.otus;

import ru.otus.atm.AtmImpl;

public class Main {

    public static void main(String[] args) {

        AtmImpl atm = new AtmImpl(10, new int[]{10, 50, 100, 500, 1000, 5000});
        System.out.println(atm.checkATMMoney());
    }

}
