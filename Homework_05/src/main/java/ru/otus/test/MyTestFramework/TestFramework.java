package ru.otus.test.MyTestFramework;

import ru.otus.test.ReflectionHelper;

public class TestFramework {

    private Class[] classes;

    public TestFramework(Class... classes) {
        this.classes = classes;
    }

    public TestFramework(String packageName) {

    }

    public void run() {
        System.out.println("Tests run");
        startTests(classes);

    }

    private void startTests(Class... classes) {
        for (Class aClass : classes) {

        }
    }
}
