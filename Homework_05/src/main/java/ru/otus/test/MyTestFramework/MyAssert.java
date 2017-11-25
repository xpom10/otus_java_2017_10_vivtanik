package ru.otus.test.MyTestFramework;

import ru.otus.test.MyTestFramework.Exception.MyAssertionError;

public class MyAssert {

    public static void MyAssertNull(Object o) throws MyAssertionError {
        if (o == null) { throw new MyAssertionError( "Object is null" + o);}
    }

    public static void MyAssertEquals(Object expected, Object actual) throws MyAssertionError {
        if (!expected.equals(actual)) throw new MyAssertionError("Objects not equals, expected: " + expected + " actual: " + actual);
    }

    public static void MyAssertTrue(boolean condition) throws MyAssertionError {
        if (!condition) throw new MyAssertionError("Condition expected TRUE, but actual FALSE");
    }

    public static void MyAssertFalse(boolean condition) throws MyAssertionError {
        if (condition) {throw new MyAssertionError("Condition expected FALSE, but actual TRUE");}
    }
}
