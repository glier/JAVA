package com.geekbrains.level3.lesson7;

public class TestSuite {

    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("test1");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("test2");
    }

    @Test(priority = 5)
    public void test5() {
        System.out.println("test5");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("test3");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("test4");
    }

    @Test
    public void nonPriorityTest1() {
        System.out.println("nonPriorityTest1");
    }

    @Test
    public void nonPriorityTest3() {
        System.out.println("nonPriorityTest3");
    }

    @Test
    public void nonPriorityTest2() {
        System.out.println("nonPriorityTest2");
    }
}
