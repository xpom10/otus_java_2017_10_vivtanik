package ru.otus.test;

import ru.otus.test.MyTestFramework.Annotations.MyAfter;
import ru.otus.test.MyTestFramework.Annotations.MyBefore;
import ru.otus.test.MyTestFramework.Annotations.MyTest;
import ru.otus.test.MyTestFramework.Exception.MyAssertionError;

public class ExampleClassTestTwo {
    @MyBefore
    public void testBefore() {
        System.out.println("Before class 2");
    }

    @MyAfter
    public void testAfter() {
        System.out.println("After class 2");
    }

    @MyTest
    public void test1() {
        System.out.println("Test1 class 2");
    }

    @MyTest
    public void test2() throws MyAssertionError {
        System.out.println("Test2 class 2");
    }
}
