package otus.departament;

import otus.atm.AtmImpl;

import java.util.ArrayList;
import java.util.List;

public class AtmDepartamentImpl implements AtmDepartamentInterface {
    private List<AtmImpl> atmDepartament;

    public AtmDepartamentImpl(List<AtmImpl> atms) {
        atmDepartament = new ArrayList<>();
        this.atmDepartament.addAll(atms);
    }

    public Integer getBalanceOfDepartament() {
        Integer balance = 0;
        for (AtmImpl atm : atmDepartament) {
            balance += atm.getATMBalance();
        }
        System.out.println("Баланс Департамента: " + balance);
        return balance;
    }

    public void initialRecovery() {
        for (AtmImpl atm : atmDepartament) {
            atm.getInitialRecovery();
        }
        System.out.println("Восстановление всех АТМ в Департаменте");
    }

}
