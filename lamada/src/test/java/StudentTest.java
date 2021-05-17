import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.learning.model.Student;
import com.learning.model.Student.Community;
import com.learning.model.Student.Gender;
import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-17 10:18
 * @description：
 * @modified By：
 * @version:
 */
@Data
@Slf4j
public class StudentTest {

    Community hdb = new Community("黄渠头路", "海德堡", 2000);
    Community yglz = new Community("黄渠头路", "阳光丽兹", 1500);
    Community tdzy = new Community("黄渠头路", "唐顿庄园", 1000);
    Community qjld = new Community("雁翔路", "曲江龙邸", 1000);
    List<Student> students = Arrays.asList(
        new Student("小黑", 5, Gender.MALE, hdb),
        new Student("小白", 6, Gender.FEMALE, hdb),
        new Student("小红", 7, Gender.FEMALE, yglz),
        new Student("小蓝", 5, Gender.MALE, tdzy),
        new Student("小陈", 3, Gender.FEMALE, hdb),
        new Student("小张", 3, Gender.MALE, yglz),
        new Student("小吴", 5, Gender.MALE, hdb),
        new Student("小刘", 12, Gender.FEMALE, hdb),
        new Student("大牛", 16, Gender.MALE, qjld),
        new Student("大李", 15, Gender.MALE, hdb),
        new Student("大Q", 42, Gender.FEMALE, qjld),
        new Student("大E", 35, Gender.MALE, hdb)
    );

    @Test
    public void test1() {

        //汇总小区名为 海德堡 的学生的总数:7
        long count = students.stream().filter(student -> student.getCommunity() == hdb).count();
        Assert.assertEquals(7, count);

    }

    @Test
    public void test2() {
        //汇总小区名为 海德堡 的学生的姓名集合
        List<String> namesList = students.stream().filter(student -> student.getCommunity() == hdb).map(
            Student::getName).collect(
            Collectors.toList());
        System.out.println(namesList);
    }

    @Test
    public void test3() {
        //住在阳光丽兹+唐顿庄园的学生的平均年龄为:5.0岁
        long totalStudents = students.stream().filter(
            student -> student.getCommunity() == tdzy || student.getCommunity() == yglz).map(
            Student::getAge).count();
        long totalAges = students.stream().filter(
            student -> student.getCommunity() == tdzy || student.getCommunity() == yglz).map(
            Student::getAge).reduce(0,
            Integer::sum);
        double averageAge = totalAges / (double)totalStudents;
        log.info("住在阳光丽兹+海德堡的学生的平均年龄为:{}岁", averageAge);

        Double collect = students.stream().filter(s -> s.getCommunity() == tdzy || s.getCommunity() == yglz).collect(Collectors.averagingDouble(Student::getAge));
        log.info("住在阳光丽兹+海德堡的学生的平均年龄为:{}岁", collect);

        double averageAge1 =  students.stream().filter(
            student -> student.getCommunity() == tdzy || student.getCommunity() == tdzy).collect(Collectors.averagingDouble(Student::getAge));
        Assert.assertEquals(5.0, averageAge1, 0.01);
    }

    @Test
    public void test4() {
        //所有学生平均年龄:12.833333333333334, 大于平均年龄的学生有:[大Q, 大E, 大李, 大牛]
        List<String> names = students.stream().filter(student ->
                student.getAge() > students.stream().collect(Collectors.averagingDouble(Student::getAge))
            ).map(Student::getName).collect(Collectors.toList());
        log.info("names = {}", names);
    }

    @Test
    public void test5() {
        int maxAge = students.parallelStream().map(Student::getAge).max((Integer::compareTo)).get();
        int minAge = students.parallelStream().map(Student::getAge).min((Integer::compareTo)).get();
        log.info("max age = {}", maxAge);
        log.info("min age = {}", minAge);
    }

}