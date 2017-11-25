package ru.otus.test.TestPackage;

import ru.otus.test.MyTestFramework.Annotations.MyAfter;
import ru.otus.test.MyTestFramework.Annotations.MyBefore;
import ru.otus.test.MyTestFramework.Annotations.MyTest;
import ru.otus.test.MyTestFramework.Exception.MyAssertionError;
import ru.otus.test.MyTestFramework.MyAssert;

public class ExamplePackageTestOne {

    @MyBefore
    public void testBefore() {
        System.out.println("Before package 1");
    }

    @MyAfter
    public void testAfter() {
        System.out.println("After package 1");
    }

    @MyTest
    public void test1() throws MyAssertionError {
        System.out.println("test package 1");
        MyAssert.MyAssertFalse(false);
    }

}

