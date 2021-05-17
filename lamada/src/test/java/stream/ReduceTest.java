package stream;

import java.util.HashMap;
import java.util.Map;

import com.sun.tools.javac.util.List;
import org.junit.Test;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-17 15:17
 * @description：
 * @modified By：
 * @version:
 */
public class ReduceTest {

    List<String> props = List.of("profile=native", "debug=true", "logging=warn", "interval=500");

    @Test
    public void reduce() {
        HashMap<String, String> maps = props.stream().map(kv -> {
            String[] spilt = kv.split("=");
            HashMap<String, String> map = new HashMap<>();
            map.put(spilt[0], spilt[1]);
            return map;
        }).reduce(new HashMap<>(), (acc, ac) -> {
            acc.putAll(ac);
            return acc;
        });

        maps.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
    }
}