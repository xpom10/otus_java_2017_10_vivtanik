package ru.otus.test.TestPackage;

import ru.otus.test.MyTestFramework.Annotations.MyAfter;
import ru.otus.test.MyTestFramework.Annotations.MyBefore;
import ru.otus.test.MyTestFramework.Annotations.MyTest;

public class ExamplePackageTest {

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
        System.out.println("test");
    }

}

