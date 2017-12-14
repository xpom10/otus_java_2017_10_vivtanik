package ru.otus.test;

import ru.otus.test.MyTestFramework.Annotations.MyAfter;
import ru.otus.test.MyTestFramework.Annotations.MyBefore;
import ru.otus.test.MyTestFramework.Annotations.MyTest;
import ru.otus.test.MyTestFramework.Exception.MyAssertionError;

public class ExampleClassTestOne {
    @MyBefore
    public void testBefore() {
        System.out.println("Before class 1");
    }

    @MyAfter
    public void testAfter() {
        System.out.println("After class 1");
    }

    @MyTest
    public void test1() throws MyAssertionError {
        System.out.println("test1 class 1");
    }

    @MyTest
    public void test2() {
        System.out.println("test2 class 1");
    }
}
