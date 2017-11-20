import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public class Test {

    @org.junit.Test
    public void test1() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        int val = list.get(0);
        int size = list.size();
        Assert.assertEquals(5, val);
        Assert.assertEquals(1, size);

    }
}
