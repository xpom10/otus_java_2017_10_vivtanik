package ru.otus.test.MyTestFramework.Exception;

public class MyAssertionError extends RuntimeException {

    public MyAssertionError() {
        super();
    }

    public MyAssertionError(String msg) {
        super(msg);
    }

}
