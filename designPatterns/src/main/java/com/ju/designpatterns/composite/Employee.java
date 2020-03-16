package com.ju.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private double salary;
    private List<Employee> child;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        child = new ArrayList<>();
    }

    public void add(Employee employee) {
        child.add(employee);
    }

    public void remove(Employee employee) {
        child.remove(employee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary+"}" ;
    }

    public List<Employee> getChild() {
        return child;
    }
}
