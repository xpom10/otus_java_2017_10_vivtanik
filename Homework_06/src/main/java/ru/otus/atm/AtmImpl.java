package ru.otus.atm;

import java.util.*;

public class AtmImpl {

    Map<Integer,Integer> cellsOfMoney;

    public AtmImpl(int numberOfBanknotes ,int[] nominal) {
        this.cellsOfMoney = new HashMap<Integer, Integer>();
        for (Integer i : nominal) {
            cellsOfMoney.put(i,numberOfBanknotes);
        }
    }

    public int checkATMMoney() {
        int val = 0;
        for (Integer i : cellsOfMoney.keySet()) {
            val += i*cellsOfMoney.get(i);
        }
        return val;
    }

    public void getMoney(int money) {

    }
}
