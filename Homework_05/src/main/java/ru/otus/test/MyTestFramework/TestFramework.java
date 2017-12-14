package ru.otus.test.MyTestFramework;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.otus.test.MyTestFramework.Exception.MyAssertionError;
import ru.otus.test.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestFramework {

    private Class[] classes;


    public TestFramework(Class... classes) throws MyAssertionError {
        System.out.println("Test for class run");
        this.classes = classes;
    }

    public TestFramework(String packageName) throws MyAssertionError {
        System.out.println("Test for package run");

        List<ClassLoader> classLoadersList = new ArrayList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));

        Set<Class<?>> modules = reflections.getSubTypesOf(Object.class);
        this.classes = modules.toArray(new Class[modules.size()]);
    }

    public void run() {
        int fail = 0;
        for (Class aClass : classes) {
            Method[] a = aClass.getMethods();
            Method afterMethod;
            Method beforeMethod;
            Method[] testMethods;

            try {
                beforeMethod = ReflectionHelper.getBeforeMethod(a);
                afterMethod = ReflectionHelper.getAfterMethod(a);
                testMethods = ReflectionHelper.getTestMethods(a);
            } catch (MyAssertionError error) {
                System.out.println(error.getMessage() + ". Classname: " + aClass.getName() );
                continue;
            }

            for (Method test : testMethods) {
                Object testObject = ReflectionHelper.instantiate(aClass);
                    if (beforeMethod != null) {
                        try {
                            ReflectionHelper.callMethod(testObject, beforeMethod.getName());
                        } catch (InvocationTargetException e) {
                            System.out.println(e.getTargetException().getMessage());

                            fail++;
                            continue;
                        }
                    }
                    if (test != null) {
                        try {
                            ReflectionHelper.callMethod(testObject, test.getName());
                        } catch (InvocationTargetException e) {
                            e.getTargetException().printStackTrace();
                            fail++;
                            continue;
                        }
                    }
                    if (afterMethod != null) {
                        try {
                            ReflectionHelper.callMethod(testObject, afterMethod.getName());
                        } catch (InvocationTargetException e) {
                            System.out.println(Arrays.toString(e.getTargetException().getStackTrace()));
                            fail++;
                        }
                    }
                }
            }
        System.out.println(fail + " ***************");
        }
}


