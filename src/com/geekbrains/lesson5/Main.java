package com.geekbrains.lesson5;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Эрлих Бахман", "CEO", 799999999, 15000, 50));
        employees.add(new Employee("Ричард Хендрикс", "CTO", 799999998, 10000, 45));
        employees.add(new Employee("Нельсон «Башка» Бигетти", "Junior Developer", 799999997, 9000, 40));
        employees.add(new Employee("Динеш Чугтая", "Middle Developer", 799999996, 8000, 35));
        employees.add(new Employee("Бертрам Гилфойл", "Senior Developer", 799999995, 7000, 30));
        employees.add(new Employee("Дональд Данн", "Engineer", 799999994, 6000, 25));

        EmpUtil.printEmployees(EmpUtil.getEmployeesByAgeGt(employees, 40));
    }
}
