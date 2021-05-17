package function;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.learning.model.Person;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.awt.image.ImageWatched.Link;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-17 15:32
 * @description：
 * @modified By：
 * @version: 介绍java内置的四种函数式接口
 *
 * 其中函数对象可以理解为java回调。可以直接从函数方法的出参和入参来理解，就非常清楚了。
 */
@Slf4j
public class FunctionTest {
    //Java8四大核心内置函数式接口
    //1、消费型   Consumer<T> 方法 void accept(T t)  表示消费参数T, 没有返回值
    //2、供给型   Supplier<T> 方法 T get();          表示提供参数T,没有入参支持
    //3、函数型(转换型)   Function<T, R> 方法  R apply(T t); 表示将入参T转换为R
    //4、断言型   Predicate<T>   方法 boolean test(T t);    表示断言输入参数T后，是否正确

    private void consumer(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    private void supplier(Supplier<Integer> supplier) {
        int number = supplier.get();
        log.info("supplier get {}", number);
    }

    private <T, R> R function(T t, Function<T, R> function) {
        R r = function.apply(t);
        log.info("{} transition to {}", t, r);
        return r;

    }

    private <T> List<T> predicate(List<T> list, Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                newList.add(t);
            }
        }

        return newList;
    }

    @Test
    public void consumerTest() {
        consumer(100, (t) -> log.info("消费 {} 元", t));
    }

    @Test
    public void supplierTest() {
        supplier(() -> new Random().nextInt(10));
    }

    @Test
    public void functionTest() {
        String name = function(new Person("jin", 12), Person::getName);
        log.info("name is {}", name);
    }

    @Test
    public void predicateTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("jin", 20));
        personList.add(new Person("yabo", 10));
        List<Person> predicate = predicate(personList, s -> s.getAge() > 14);
        log.info("person list length = {}", predicate.size());
    }

    Stream<String> source = Stream.of("yabo", "xingze", "lining", "jiuha", "ming, ming", "dahua, ", "haha=haha");
    Stream<String> newSource = Stream.of("yabo", "xingze", "lining", "jiuha", "ming, ming", "dahua, ", "haha=haha");

    @Test
    public void testCollection() {
        source.peek((s) -> log.info("origin value={}", s))
            .filter(s -> s.length() < 6)
            .peek((s) -> log.info("filter value={}", s))
            .map(String::toUpperCase)
            .peek((s) -> log.info("upper value={}", s))
            .collect(Collectors.toList());

        List<String> results = newSource.limit(4)
            .skip(2)
            .collect(Collectors.toList());

        log.info("results = {}", results);
    }

    @Test
    public void testCollection1() {
        Stream<String> newSource = Stream.of("yabo", "xingze", "lining", "jiuha", "ming,ming", "dahua,", "haha=www");
        List<String> results = newSource.flatMap(t -> {
            if (t.contains("=")) {
                return Stream.of(t.split("="));
            } else if (t.contains(",")) {
                return Stream.of(t.split(","));
            } else {
                return Stream.of(t);
            }
        }).filter(s -> s.length() > 0).collect(Collectors.toCollection(LinkedList::new));

        log.info("results = {}", results);
        results.forEach(System.out::println);
    }

}