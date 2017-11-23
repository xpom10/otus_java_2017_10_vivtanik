package ru.otus.test;


import ru.otus.test.MyTestFramework.Exception.MyAssertionError;
import ru.otus.test.MyTestFramework.MyAssert;
import ru.otus.test.MyTestFramework.TestFramework;

public class Main {

    private static MyAssert myAssert= new MyAssert();

    public static void main(String[] args) throws MyAssertionError {

        new TestFramework(ExampleClassTest.class).run();


    }

}
