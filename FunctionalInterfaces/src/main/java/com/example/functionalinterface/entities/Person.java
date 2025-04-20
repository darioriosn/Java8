package com.example.functionalinterface.entities;

public class Person {
    private final String name;
    private final int age;
    private final String role;
    private final double salary;

    public Person(String name, int age, String role, double salary) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getRole() { return role; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}
