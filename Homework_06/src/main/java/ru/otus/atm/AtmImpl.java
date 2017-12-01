package ru.otus.atm;

import java.util.*;

public class AtmImpl implements AtmInterface {

    private Map<Integer,Integer> cellsOfMoney;
    private Map<Integer,Integer> recoveryCell;

    public AtmImpl(int[] numberOfBanknotes ,int[] nominal) throws Exception {
        this.cellsOfMoney = new HashMap<>();
        this.recoveryCell = new HashMap<>();
        checkInitATM(numberOfBanknotes,nominal);
        for (int i = 0; i < nominal.length; i++) {
            cellsOfMoney.put(nominal[i], numberOfBanknotes[i]);
        }

        System.out.println("ATM инициализирован" ); getATMBalance();
    }

    public int getATMBalance() {
        int val = 0;
        for (Integer i : cellsOfMoney.keySet()) {
            val += i*cellsOfMoney.get(i);
        }
        System.out.println("Баланс ATM: " + val);
        return val;
    }

    public boolean replenishATM(Integer replenishSum) {
        if (!cellsOfMoney.containsKey(replenishSum) || replenishSum < 0) {
            try {
                throw new Exception("ATM не может быть пополнен на эту сумму: " + replenishSum);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            Integer newCount = cellsOfMoney.get(replenishSum) + 1;
            cellsOfMoney.put(replenishSum,newCount);
            System.out.println("ATM пополнен на одну банкноту номиналом: " + replenishSum);
            return true;
        }
    }

    public boolean getMoney(Integer money) {
        if (money <= 0) {
            try {
                throw new Exception("ATM не может выдать отрицательную сумму или ноль: " + money);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        setRecoveryCell(cellsOfMoney);
        Integer sum = money;
        List<Integer> reverseListNominal = new ArrayList<>(cellsOfMoney.keySet());
        reverseListNominal.sort(Comparator.reverseOrder());
        for (Integer nominal : reverseListNominal) {
            Integer countOfBanknotes = this.cellsOfMoney.get(nominal);
            Integer count = Math.min(money / nominal, countOfBanknotes);
            countOfBanknotes -= count;
            money -= (count*nominal);
            cellsOfMoney.put(nominal,countOfBanknotes);
            if (money == 0) {
                System.out.println("ATM выдал: " + sum);
                return true;
            } else if (money != 0 && (Objects.equals(nominal, reverseListNominal.get(reverseListNominal.size() - 1)))) {
                try {
                    throw new Exception("ATM не может выдать данную сумму: " + sum);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    cellsOfMoney = getRecoveryCell();
                    return false;
                }
            }
        }
        return false;
    }

    private void checkInitATM(int[] numberOfBanknotes ,int[] nominal) throws Exception {
        if (nominal.length != numberOfBanknotes.length) {
            throw new Exception("Не для всех номиналов валюты указано количество банкнот в ATM");
        }
        for (int i = 0; i < nominal.length; i++) {
            if (numberOfBanknotes[i] < 0) {
                throw new Exception("Количество купюр в АТМ не может быть отрицательным");
            }
            if (nominal[i] < 1) {
                throw new Exception("Номинал валюты не может быть меньше 1");
            }
        }
    }


    private void setRecoveryCell(Map<Integer,Integer> cell) {
        recoveryCell.putAll(cell);
    }

    private Map<Integer,Integer> getRecoveryCell() {
        HashMap<Integer,Integer> retMap = new HashMap<>();
        retMap.putAll(recoveryCell);
        return retMap;
    }

}
