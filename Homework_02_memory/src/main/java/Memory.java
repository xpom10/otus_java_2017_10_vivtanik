import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Memory {

    private final int sizeOfArray = 1000000;


    public long countSizeOfManyObjects(Supplier<Object> sup) {

         long memoryBefore, memoryAfter;
         Object[] array = new Object[sizeOfArray];
         memoryBefore = currentMemory();
         for (int i = 0; i < sizeOfArray; i++) {
             array[i] = sup.get();
         }
         memoryAfter = currentMemory();
         if (array.length > 0) {
             array[0] = null;
         }
         return Math.round((memoryAfter - memoryBefore) / sizeOfArray);
    }

    private long currentMemory() {
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public List<Integer> setListSize(int sizeList) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < sizeList; i ++) {
            list.add(1);
        }
        return list;
    }
}
