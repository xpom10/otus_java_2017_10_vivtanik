package ru.otus.atm;

import java.util.*;

public class AtmImpl {

    Map<Integer,Integer> cellsOfMoney;

    public AtmImpl(int numberOfBanknotes ,int[] nominal) {
        this.cellsOfMoney = new HashMap<>();
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

    public void getMoney(Integer money) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = cellsOfMoney.keySet();
        for (Integer i : set) {
            list.add(i);
        }
        Collections.sort(list,Comparator.reverseOrder());
        if (((money & cellsOfMoney.get(list.get(list.size() - 1))) == 0)) {
            for (Integer a : list) {
                if ((money / a) >=  1) {
                    Integer newCount = cellsOfMoney.get(a) - (money/a);
                    cellsOfMoney.put(a, newCount);
                    money &= a;
                }
            }
        }
    }
}
