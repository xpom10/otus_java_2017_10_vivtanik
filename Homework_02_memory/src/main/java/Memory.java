public class Memory {


     public long countSizeOfManyObjects(int sizeOfArray) {

        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();

        Object[] array = new Object[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++) {
            array[i] = new Object();
        }
        System.gc();
        long after = runtime.totalMemory() - runtime.freeMemory();
        return  ((after - before) / sizeOfArray) ;
    }

    public long countSizeOfEmptyString() {

//        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();

        String s = "";

//        System.gc();
        long after = runtime.totalMemory() - runtime.freeMemory();
        return  ((after - before)) ;
    }


}
