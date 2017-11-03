import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String... argc) {

        MyArrayList myArrayList = new MyArrayList();
        ArrayList arrayListDest = new ArrayList();
        ArrayList arrayListSource = new ArrayList();

        checkAddAll();
        checkInteger(myArrayList, arrayListDest, arrayListSource);
        checkStrings(myArrayList, arrayListDest, arrayListSource);
    }

    private static void checkInteger(MyArrayList myArrayList, ArrayList arrayListDest, ArrayList arrayListSource) {
        myArrayList = new MyArrayList();
        arrayListDest = new ArrayList();
        arrayListSource = new ArrayList();
        System.out.println("Test MyArrayList with Integer: ");
        for (int i = 0; i < 10; i++) {
            myArrayList.add(10 - i);
            arrayListDest.add(0);
            arrayListSource.add(0);
        }
        System.out.println("Was: \n" +
                "ArrayListDest:" + arrayListDest + "\n"
                +   "ArrayListSource:" + arrayListSource);
        System.out.print("MyArrayList: "); printMyArrayList(myArrayList);

        Collections.sort(myArrayList, Comparator.naturalOrder());
        System.out.print("Sort MyArrayList: "); printMyArrayList(myArrayList);

        Collections.copy(arrayListDest,myArrayList );
        Collections.copy(myArrayList, arrayListSource);

        System.out.println("Has become: \n" +
                "ArrayListDest copy from MyArraList:" + arrayListDest + "\n"
                +   "ArrayListSource:" + arrayListSource);
        System.out.print("MyArrayList copy from ArrayListSource: "); printMyArrayList(myArrayList);
        System.out.println();

    }

    private static void checkAddAll() {
        System.out.println("Test addAll:");
        MyArrayList myArrayListInt = new MyArrayList();
        Collections.addAll(myArrayListInt,1,2,3,4,5);
        System.out.print("addAll Integer ");printMyArrayList(myArrayListInt);

        MyArrayList myArrayListStr = new MyArrayList();
        Collections.addAll(myArrayListStr,"a","b","c","d","e");
        System.out.print("addAll Strings "); printMyArrayList(myArrayListStr);
        System.out.println();

    }

    private static void checkStrings(MyArrayList myArrayList, ArrayList arrayListDest, ArrayList arrayListSource) {
        myArrayList = new MyArrayList();
        arrayListDest = new ArrayList();
        arrayListSource = new ArrayList();
        System.out.println("Test MyArrayList with Strings: ");
        for (int i = 0; i < 5; i++) {
            arrayListDest.add("z");
            arrayListSource.add("z");
        }
        Collections.addAll(myArrayList,"e", "d","c", "b", "a");
        System.out.println("Was: \n" +
                "ArrayListDest:" + arrayListDest + "\n"
                +   "ArrayListSource:" + arrayListSource);
        System.out.print("MyArrayList: "); printMyArrayList(myArrayList);

        Collections.sort(myArrayList, Comparator.naturalOrder());
        System.out.print("Sort MyArrayList: "); printMyArrayList(myArrayList);

        Collections.copy(arrayListDest,myArrayList );
        Collections.copy(myArrayList, arrayListSource);

        System.out.println("Has become: \n" +
                "ArrayListDest copy from MyArraList:" + arrayListDest + "\n"
                +   "ArrayListSource:" + arrayListSource);
        System.out.print("MyArrayList copy from ArrayListSource: "); printMyArrayList(myArrayList);
        System.out.println();

    }


    private static void printMyArrayList(MyArrayList myArrayList) {
        for (int i =0; i < myArrayList.size(); i++) {
            System.out.print(myArrayList.get(i) + " ");
        }
        System.out.println();
    }


}
