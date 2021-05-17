package stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-17 12:39
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class PredicateTest {
    Integer[] arr = {-12345, 9999, 520, 0, -38, -7758520, 941213};

    @Test
    public void predicate() {
        //a)   使用lambda表达式创建Predicate对象p1,p1能判断整数是否是自然数
        Predicate<Integer> p1 = (s) -> (s >= 0);
        //b)   使用lambda表达式创建Predicate对象p2,p2能判断整数的绝对值是否大于100
        Predicate<Integer> p2 = (s) -> Math.abs(s) > 100;
        //c)   使用lambda表达式创建Predicate对象p3,p3能判断整数是否是偶数
        Predicate<Integer> p3 = (s) -> s % 2 == 0;
        //自解数个数
        int count1 = 0;
        //统计负整数个数
        int count2 = 0;
        //统计绝对值大于100的偶数个数
        int count3 = 0;
        //统计是负整数或偶数的数的个数
        int count4 = 0;
        for (Integer number : arr) {
            if (p1.test(number)) {
                count1++;
            }
            if (p1.negate().test(number)) {
                count2++;
            }
            if (p1.and(p3).test(number)) {
                count3++;
            }
            if (p1.negate().or(p3).test(number)) {
                count4++;
            }
        }
        log.info("自解数个数 {}", count1);
        log.info("统计负整数个数 {}", count2);
        log.info("统计绝对值大于100的偶数个数 {}", count3);
        log.info("统计是负整数或偶数的数的个数 {}", count4);
    }

    @Test
    public void function() {
        //2 将学生姓名和成绩封装到map中
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("岑小村", 59);
        map.put("谷天洛", 82);
        map.put("渣渣辉", 98);
        map.put("蓝小月", 65);
        map.put("皮几万", 70);

        Function<List<Integer>, Integer> f1 = (list) -> {
            double value = list.stream().collect(Collectors.averagingInt(Integer::intValue));
            return (int) Math.round(value);
        };


        Function<Map<String, Integer>, List<Integer>> f2 = (item) -> {
            Collection<Integer> values = item.values();
            List<Integer> list = new ArrayList<>();
            list.addAll(values);
            return list;
        };
        int value = f2.andThen(f1).apply(map);
        log.info("average value = {}", value);

        List<Integer> list = f2.apply(map);

    }

    @Test
    public void filter() {
        Stream<String> stream = Stream.of("黄药师", "冯蘅", "郭靖", "黄蓉", "郭芙", "郭襄", "郭破虏");
        String[] filters = stream.filter(String::isEmpty).toArray(String[]::new);
        log.info("filter length = {}", filters.length);
    }
}