package ru.otus.atm;

public interface AtmInterface {

    int getATMBalance();

    boolean replenishATM(Integer replenishSum);

    boolean getMoney(Integer money);


}
