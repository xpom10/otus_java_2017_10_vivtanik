
import org.junit.Before;
import org.junit.Test;
import ru.otus.atm.AtmImpl;

import static org.junit.Assert.*;


public class TestAtm {

    private AtmImpl atm;

    @Before
    public void initATM() throws Exception {
        atm = new AtmImpl(new int[]{5, 5, 5, 5, 5, 5}, new int[]{10, 50, 100, 500, 1000, 5000});
    }

    @Test
    public void testBalance() {
        assertEquals(atm.getATMBalance(),33300);
    }

    @Test
    public void testBalanceAfterGetMoney() {
        assertTrue(atm.getMoney(500));
        assertEquals(atm.getATMBalance(),32800);
    }

    @Test
    public void testIncorrectGetMoney() {
        assertFalse(atm.getMoney(-1));
        assertFalse(atm.getMoney(0));
        assertFalse(atm.getMoney(1));
        assertFalse(atm.getMoney(43));
        assertEquals(atm.getATMBalance(),33300);
    }

    @Test
    public void testBalanceAfterIncorrectGetMoney() {
        assertFalse(atm.getMoney(-500));
        assertEquals(atm.getATMBalance(),33300);
        assertFalse(atm.getMoney(-1000));
        assertEquals(atm.getATMBalance(),33300);
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
        assertEquals(atm.getATMBalance(),33300 + 10);
        assertTrue(atm.replenishATM(50));
        assertEquals(atm.getATMBalance(),33300 + 10 + 50);
        assertTrue(atm.replenishATM(100));
        assertTrue(atm.replenishATM(500));
        assertTrue(atm.replenishATM(1000));
        assertTrue(atm.replenishATM(5000));

        assertEquals(atm.getATMBalance(),33300 + 6660);
    }

    @Test
    public void testIncorrectReplenishATM() {
        assertFalse(atm.replenishATM(-10));
        assertFalse(atm.replenishATM(0));
        assertFalse(atm.replenishATM(43));


        assertEquals(atm.getATMBalance(),33300);

        assertFalse(atm.replenishATM(178));
        assertFalse(atm.replenishATM(541));
        assertFalse(atm.replenishATM(1003));

        assertEquals(atm.getATMBalance(),33300);
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
