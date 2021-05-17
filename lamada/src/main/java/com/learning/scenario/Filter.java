package com.learning.scenario;

import java.util.ArrayList;
import java.util.List;

import com.learning.model.Person;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-13 16:38
 * @description：
 * @modified By：
 * @version:
 */
public class Filter {

    List<Person> personList = new ArrayList<>();

    public Filter() {
        personList.add(new Person("yabo", 31));
        personList.add(new Person("yabo1", 20));
        personList.add(new Person("yabo2", 40));
        personList.add(new Person("lining", 31));
        personList.add(new Person("wuha", 18));
    }

    public List<Person> getPersonList() {
        return personList;
    }
}