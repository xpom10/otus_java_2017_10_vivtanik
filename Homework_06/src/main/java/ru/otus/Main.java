package ru.otus;

import ru.otus.atm.AtmImpl;

public class Main {

    public static void main(String[] args) throws Exception {
        AtmImpl atm = new AtmImpl(new int[]{10, 10, 10, 10, 10, 5}, new int[]{10, 50, 100, 500, 1000, 5000});
        atm.getMoney(660);
        atm.replenishATM(500);
        atm.getATMBalance();
        atm.getMoney(-10);
        atm.getATMBalance();
        atm.getMoney(0);
        atm.getATMBalance();
        atm.getMoney(660);
        atm.replenishATM(500);
        atm.getATMBalance();
    }

}
