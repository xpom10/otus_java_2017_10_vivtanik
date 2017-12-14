import org.junit.Before;
import org.junit.Test;
import otus.atm.AtmImpl;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class TestAtm {

    private AtmImpl atm;

    @Before
    public void initATM() throws Exception {
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

        atm = new AtmImpl(listCells1);
    }

    @Test
    public void testBalance() {
        assertEquals(atm.getATMBalance(),25600);
    }

    @Test
    public void testBalanceAfterGetMoney() {
        assertTrue(atm.getMoney(500));
        assertEquals(atm.getATMBalance(),25600 - 500);
    }

    @Test
    public void testIncorrectGetMoney() {
        assertFalse(atm.getMoney(-1));
        assertFalse(atm.getMoney(0));
        assertFalse(atm.getMoney(1));
        assertFalse(atm.getMoney(43));
        assertEquals(atm.getATMBalance(),25600);
    }

    @Test
    public void testBalanceAfterIncorrectGetMoney() {
        assertFalse(atm.getMoney(-500));
        assertEquals(atm.getATMBalance(),25600);
        assertFalse(atm.getMoney(-1000));
        assertEquals(atm.getATMBalance(),25600);
    }

    @Test(expected = Exception.class)
    public void testNegativeNumberOfBanknotesInitATM() throws Exception {
        atm = new AtmImpl(new int[]{5, 5, 5, 5, 5, -1}, new int[]{10, 50, 100, 500, 1000, 5000});
    }

    @Test(expected = Exception.class)
    public void testNegativeNominalInitATM() throws Exception {
        atm = new AtmImpl(new int[]{5, 5, 5, 5, 5, 5}, new int[]{-5, 50, 100, 500, 1000, 5000});
    }

    @Test(expected = Exception.class)
    public void testIncorrectInitATM() throws Exception {
        atm = new AtmImpl(new int[]{5, 5, 5, 5, 5, 5}, new int[]{ 50, 100, 500, 1000, 5000});
    }

    @Test(expected = Exception.class)
    public void testIncorrectInitATM2() throws Exception {
        atm = new AtmImpl(new int[]{ 5, 5, 5, 5, 5}, new int[]{ 10, 50, 100, 500, 1000, 5000});
    }

    @Test
    public void testReplenishATM() {
        assertTrue(atm.replenishATM(10));
        assertEquals(atm.getATMBalance(),25600 + 10);
        assertTrue(atm.replenishATM(50));
        assertEquals(atm.getATMBalance(),25600 + 10 + 50);
        assertTrue(atm.replenishATM(100));
        assertTrue(atm.replenishATM(500));
        assertTrue(atm.replenishATM(1000));
        assertTrue(atm.replenishATM(5000));

        assertEquals(atm.getATMBalance(),25600 + 6660);
    }

    @Test
    public void testIncorrectReplenishATM() {
        assertFalse(atm.replenishATM(-10));
        assertFalse(atm.replenishATM(0));
        assertFalse(atm.replenishATM(43));


        assertEquals(atm.getATMBalance(),25600);

        assertFalse(atm.replenishATM(178));
        assertFalse(atm.replenishATM(541));
        assertFalse(atm.replenishATM(1003));

        assertEquals(atm.getATMBalance(),25600);
    }


    @Test
    public void testEmptyBanknotesReplenishATM() throws Exception {
        atm = new AtmImpl(new int[]{5}, new int[]{10});
        assertTrue(atm.getMoney(10));
        assertTrue(atm.getMoney(10));
        assertTrue(atm.getMoney(10));
        assertTrue(atm.getMoney(10));
        assertTrue(atm.getMoney(10));
        assertFalse(atm.getMoney(10));

        assertEquals(atm.getATMBalance(),0);
    }
}
