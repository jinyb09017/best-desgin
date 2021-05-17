package com.learning.model;

import lombok.Data;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-17 10:37
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class Student {

    // 姓名
    private String name;
    // 年龄
    private int age;
    // 性别
    private Gender gender;
    // 小区
    private Community community;

    public Student(String name, int age, Gender gender, Community community) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.community = community;
    }

    @Data
    public static class Community {
        private String address;
        private String name;
        private int count; // 小区人数

        public Community(String address, String name, int count) {
            this.address = address;
            this.name = name;
            this.count = count;
        }
    }

    public enum Gender {
        MALE,
        FEMALE;
    }

}