package ru.otus.test;


import ru.otus.test.MyTestFramework.Exception.MyAssertionError;
import ru.otus.test.MyTestFramework.TestFramework;

public class Main {



    public static void main(String[] args) throws MyAssertionError {

         new TestFramework(ExampleClassTestOne.class,ExampleClassTestTwo.class).run();

         new TestFramework("ru.otus.test.TestPackage").run();


    }

}
