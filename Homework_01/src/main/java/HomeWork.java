import org.apache.commons.collections4.ListUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HomeWork {

    public static void main(String... a) {


        List<Integer> sourceList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            sourceList.add(randomGenerator(100));
        }
        List<Integer> shuffleList = sourceList;
        Collections.shuffle(shuffleList);

        List<Integer> unionList = ListUtils.union(sourceList,shuffleList);
        Collections.shuffle(unionList);
        List<Integer> result = countContains(sourceList,shuffleList,unionList);


        int contain = result.get(result.size()-1);
        result.remove(result.size()-1);

        System.out.println("Number of contains: " + contain + "\n" + "Index of contains: " + result);

    }

    private static int randomGenerator(int max) {
        return (int) (Math.random() * max + 1);
    }

    private static List<Integer> countContains(List<Integer> list1 , List<Integer> list2, List<Integer> union) {
        int contain = 0;
        List<Integer> index = new LinkedList();
        for (int i = 0; i < list1.size(); i++) {
            if (Objects.equals(union.get(i), list1.get(i))) {
                contain++;
                index.add(i);
            }
        }
        for (int j = 0; j < list2.size(); j++) {
            if (Objects.equals(union.get(j + list2.size()), list2.get(j))) {
                contain++;
                index.add(j + list2.size());
            }
        }
        index.add(contain);
        return index;
    }
}
