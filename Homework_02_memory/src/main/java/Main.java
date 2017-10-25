import java.io.IOException;

public class Main {


    public static void main(String... argc) throws IOException {
        Memory memory = new Memory();
        System.out.println(memory.countSizeOfManyObjects(1000000) + " bytes/Object");
//        System.out.println(memory.countSizeOfEmptyString() + " size of empty String");
    }
}
