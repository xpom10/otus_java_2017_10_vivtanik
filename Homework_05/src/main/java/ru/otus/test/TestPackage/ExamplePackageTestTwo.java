package ru.otus.test.TestPackage;

import ru.otus.test.MyTestFramework.Annotations.MyAfter;
import ru.otus.test.MyTestFramework.Annotations.MyBefore;
import ru.otus.test.MyTestFramework.Annotations.MyTest;
import ru.otus.test.MyTestFramework.Exception.MyAssertionError;
import ru.otus.test.MyTestFramework.MyAssert;

public class ExamplePackageTestTwo {

    @MyBefore
    public void testBefore() {
        System.out.println("Before package 2");
    }

    @MyAfter
    public void testAfter() {
        System.out.println("After package 2");
    }

    @MyTest
    public void test1() throws MyAssertionError {
        System.out.println("test package 2");
    }

}

