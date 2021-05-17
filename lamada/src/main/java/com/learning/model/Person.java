package com.learning.model;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-13 16:35
 * @description：person
 * @modified By：
 * @version:
 */
public class Person {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}