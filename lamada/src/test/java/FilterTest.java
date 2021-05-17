import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.learning.model.Person;
import com.learning.scenario.Filter;
import org.junit.Test;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-13 16:40
 * @description：
 * @modified By：
 * @version:
 */
public class FilterTest {

    @Test
    public void testFilter() {
        Filter filter = new Filter();
        List<Person> collects = filter.getPersonList().stream().filter(person -> person.getAge() > 30).collect(Collectors.toList());
        collects.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                printPersonName(person);
            }
        });

        collects.forEach(this::printPersonName);

        List<String> names = collects.stream().map(Person::getName).collect(Collectors.toList());
        names.forEach(System.out::println);

    }

    private void printPersonName(Person person) {
        System.out.println(person.getName());
    }
}