package com.geekbrains.level1.lesson5;

import java.util.ArrayList;
import java.util.List;

public class EmpUtil {

    public static List<Employee> getEmployeesByAgeGt(List<Employee> list, int age) {
        List<Employee> filtered = new ArrayList<>();

        for (Employee emp: list) {
            if (emp.getAge() > age) filtered.add(emp);
        }

        return filtered;
    }

    public static void printEmployees(List<Employee> list) {
        for (Employee emp: list) {
            emp.printEmployeeInfo();
        }
    }
}
