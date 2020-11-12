package com.geekbrains.level3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestStarter {
    public static void start(Class testClass, Object instance) {
        validate(testClass, instance);
        startBeforeTest(testClass, instance);
        startTests(testClass, instance);
        startAfterTest(testClass, instance);
    }

    private static void validate(Class testClass, Object instance) {
        int beforeCount = 0;
        int afterCount = 0;

        Method[] methods = testClass.getMethods();
        for (Method m: methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                beforeCount++;
            }
            if (m.getAnnotation(AfterSuite.class) != null) {
                afterCount++;
            }
        }

        if (beforeCount > 1 || afterCount >1) {
            String message = String.format(
                    "BeforeSuite or AfterSuite annotations " +
                            "declared in class: %s more then once", testClass.getName());
            throw new RuntimeException(message);
        }

    }

    private static void startAfterTest(Class testClass, Object instance) {
        Method[] methods = testClass.getMethods();

        for (Method m: methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                try {
                    m.invoke(instance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void startTests(Class testClass, Object instance) {
        Method[] methods = testClass.getMethods();
        List<Method> tests = new ArrayList<>();

        for (Method m: methods) {
            if (m.isAnnotationPresent(Test.class)) {
                tests.add(m);
            }
        }

        tests.sort((m1, m2) -> {
            int p1 = m1.getAnnotation(Test.class).priority();
            int p2 = m2.getAnnotation(Test.class).priority();
            return Integer.compare(p1, p2);
        });

        for (Method m: tests) {
            try {
                m.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startBeforeTest(Class testClass, Object instance) {
        Method[] methods = testClass.getMethods();

        for (Method m: methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                try {
                    m.invoke(instance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
