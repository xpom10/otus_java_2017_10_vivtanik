package ru.otus.test;

import ru.otus.test.MyTestFramework.Annotations.MyAfter;
import ru.otus.test.MyTestFramework.Annotations.MyBefore;
import ru.otus.test.MyTestFramework.Annotations.MyTest;
import ru.otus.test.MyTestFramework.Exception.MyAssertionError;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ReflectionHelper {
    private ReflectionHelper() {
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.newInstance();
            } else {
                return type.getConstructor(toClasses(args)).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Object getFieldValue(Object object, String name) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
        return null;
    }

    static void setFieldValue(Object object, String name, Object value) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
    }

    public static Object callMethod(Object object, String name, Object... args) throws InvocationTargetException {
        Method method = null;
        boolean isAccessible = true;
        try {
            method = object.getClass().getDeclaredMethod(name, toClasses(args));
            isAccessible = method.isAccessible();
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            System.out.println(e);
        } finally {
            if (method != null && !isAccessible) {
                method.setAccessible(false);
            }
        }
        return null;
    }

    static private Class<?>[] toClasses(Object[] args) {
        List<Class<?>> classes = Arrays.stream(args)
                .map(Object::getClass)
                .collect(Collectors.toList());
        return classes.toArray(new Class<?>[classes.size()]);
    }

    public static  Method getBeforeMethod(Method... methods) {
        Method m = null;
        int count = 0;
        for (Method met : methods) {
            if (met.getAnnotation(MyBefore.class) != null) {
                m = met;
                count++;
            }
        }
        if (count > 1) throw  new MyAssertionError("Too many before methods in class, before method may be only one");
        return m;
    }

    public static Method getAfterMethod(Method... methods) {
        Method m = null;
        int count = 0;
        for (Method met : methods) {
            if (met.getAnnotation(MyAfter.class) != null) {
                m = met;
                count++;
            }
        }
        if (count > 1 ) throw new MyAssertionError("Too many after methods in class, after method may be only one");
        return m;
    }

    public static Method[] getTestMethods(Method... methods) {
       int count = 0;
        List<Method> testMethods = new ArrayList<>();
        for (Method met : methods) {
            if (met.getAnnotation(MyTest.class) != null) {
                testMethods.add(met);
                count++;
            }
        }
        if (count==0) throw new MyAssertionError("Test methods not found");
        Method[] arrayTest = new Method[testMethods.size()];
        return testMethods.toArray(arrayTest);
    }

}