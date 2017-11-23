package ru.otus.test.MyTestFramework;

import ru.otus.test.MyTestFramework.Exception.MyAssertionError;
import ru.otus.test.ReflectionHelper;

import java.lang.reflect.Method;

public class TestFramework {

    private Class[] classes;

    private Method afterMethod;
    private Method beforeMethod;
    private Method[] testMethods;

    public TestFramework(Class... classes) throws MyAssertionError {
        System.out.println("Test for class run");
        this.classes = classes;
    }

    public TestFramework(String packageName) throws MyAssertionError {

    }

    public void run() {
        startTests(classes);
    }

    private void startTests(Class... classes) {
        for (Class aClass : classes) {
            Object testObject = ReflectionHelper.instantiate(aClass);

            Method[] a = aClass.getMethods();
            this.afterMethod = ReflectionHelper.getAfterMethod(a);
            this.beforeMethod = ReflectionHelper.getBeforeMethod(a);
            this.testMethods = ReflectionHelper.getTestMethods(a);

            for (Method test: testMethods) {
                if (beforeMethod != null) {
                    ReflectionHelper.callMethod(testObject, beforeMethod.getName());
                }
                if (test != null) {
                    ReflectionHelper.callMethod(testObject, test.getName());
                }
                if (afterMethod != null) {
                    ReflectionHelper.callMethod(testObject, afterMethod.getName());
                }

            }
        }
    }
}
