import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class Test {

    @org.junit.Test
    public void test1() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        int val = list.get(0);
        int size = list.size();
        assertEquals(5, val);
        assertEquals(1, size);
    }

    @org.junit.Test
    public void test2() {
        assertTrue(true);
    }
}
