import java.io.IOException;
import java.util.HashSet;

public class Main {


    public static void main(String... argc) throws IOException {
        Memory memory = new Memory();

        System.out.println(memory.countSizeOfManyObjects(() -> new String()) + " bytes of String");
        System.out.println(memory.countSizeOfManyObjects(() -> new Object()) + " bytes of Object");
        System.out.println(memory.countSizeOfManyObjects(() -> new HashSet<>()) + " bytes of HashSet");
        System.out.println(memory.countSizeOfManyObjects(() -> new Integer(5)) + " bytes of Integer");
        System.out.println(memory.countSizeOfManyObjects(() -> 999) + " bytes of int");
        System.out.println(memory.countSizeOfManyObjects(() -> new int[100]) + " bytes of int[]");
    }

}
