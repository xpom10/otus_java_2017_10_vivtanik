package ru.otus.test;

import ru.otus.test.MyTestFramework.Annotations.MyAfter;
import ru.otus.test.MyTestFramework.Annotations.MyBefore;
import ru.otus.test.MyTestFramework.Annotations.MyTest;

public class ExampleClassTest {
    @MyBefore
    public void testBefore() {
        System.out.println("Before");
    }

    @MyAfter
    public void testAfter() {
        System.out.println("After");
    }

    @MyTest
    public void test1() {
        System.out.println("test1");
    }

    @MyTest
    public void test2() {
        System.out.println("test2");
    }
}
