package otus;


import otus.atm.AtmImpl;
import otus.departament.AtmDepartamentImpl;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<AtmImpl> listDepartament = new ArrayList<>();

        Map<Integer,Integer> ten = new HashMap<>();
        Map<Integer,Integer> fifty = new HashMap<>();
        Map<Integer,Integer> oneHundred = new HashMap<>();
        Map<Integer,Integer> fiveHundred = new HashMap<>();
        Map<Integer,Integer> thousand = new HashMap<>();
        Map<Integer,Integer> fiveThousand = new HashMap<>();

        ten.put(10,10);
        fifty.put(50,10);
        oneHundred.put(100,10);
        fiveHundred.put(500,8);
        thousand.put(1000,5);
        fiveThousand.put(5000,3);
        List<Map<Integer,Integer>> listCells1 = Arrays.asList(ten,fifty,oneHundred,fiveHundred,thousand,fiveThousand);

        AtmImpl atm1 = new AtmImpl(listCells1);

        ten.put(10,10);
        fifty.put(50,11);
        oneHundred.put(100,12);
        fiveHundred.put(500,10);
        thousand.put(1000,8);
        fiveThousand.put(5000,5);
        List<Map<Integer,Integer>> listCells2 = Arrays.asList(ten,fifty,oneHundred,fiveHundred,thousand,fiveThousand);

        AtmImpl atm2 = new AtmImpl(listCells2);

        ten.put(10,10);
        fifty.put(50,5);
        oneHundred.put(100,7);
        fiveHundred.put(500,10);
        thousand.put(1000,6);
        fiveThousand.put(5000,7);
        List<Map<Integer,Integer>> listCells3 = Arrays.asList(ten,fifty,oneHundred,fiveHundred,thousand,fiveThousand);

        AtmImpl atm3 = new AtmImpl(listCells3);

        listDepartament.add(atm1);
        listDepartament.add(atm2);
        listDepartament.add(atm3);


        AtmDepartamentImpl departament = new AtmDepartamentImpl(listDepartament);

        departament.getBalanceOfDepartament();

        atm1.getMoney(10005);
        atm2.getMoney(5500);
        atm3.getMoney(24680);
        atm2.replenishATM(5000);
        departament.getBalanceOfDepartament();
        departament.initialRecovery();
        departament.getBalanceOfDepartament();

    }
}
