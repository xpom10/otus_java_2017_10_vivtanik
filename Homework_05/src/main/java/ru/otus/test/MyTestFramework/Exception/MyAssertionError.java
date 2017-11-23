package ru.otus.test.MyTestFramework.Exception;

public class MyAssertionError extends Exception {

    public MyAssertionError() {
        super();
    }

    public MyAssertionError(String msg) {
        super(msg);
    }

}
