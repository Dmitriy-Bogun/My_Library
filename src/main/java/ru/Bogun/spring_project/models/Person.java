package ru.Bogun.spring_project.models;

public class Person {
    private String FNS;
    private int age;

    public Person(String FNS, int age) {
        this.FNS = FNS;
        this.age = age;
    }

    public Person(){

    }

    public String getFNS() {
        return FNS;
    }

    public void setFNS(String FNS) {
        this.FNS = FNS;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
