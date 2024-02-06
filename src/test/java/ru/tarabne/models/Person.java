package ru.tarabne.models;

public class Person {
    private String name;
    private int age;
    private PersonAdditionalInfo additionalInfo;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public PersonAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }
}
