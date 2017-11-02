import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String... argc) {

        MyArrayList myArrayListStrings = new MyArrayList();
        ArrayList arrayListString = new ArrayList(100);

        for (int i = 0; i < 100; i++) {
            myArrayListStrings.add(i);
            arrayListString.add(1);
        }
        System.out.println(myArrayListStrings + " \n" + arrayListString);
        System.out.println(myArrayListStrings.size()+ " " + arrayListString.size());

        Collections.copy(myArrayListStrings,arrayListString );
        Collections.sort(myArrayListStrings);

        for (int i = 0; i < 100; i++) {
            System.out.print(myArrayListStrings.get(i) + " ");
        }
    }

}
