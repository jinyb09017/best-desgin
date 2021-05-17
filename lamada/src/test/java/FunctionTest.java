import java.sql.SQLOutput;

import com.learning.function.HelloService;
import org.junit.Test;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-15 23:57
 * @description：
 * @modified By：
 * @version:
 */
public class FunctionTest {



    private void printName(HelloService helloService, String over) {
        System.out.println(helloService.getHellWorld("jin"));
        System.out.println(over);
    }

    @Test
    public void testFunction() {
        String prefix = "hello:";
        HelloService helloService = (name) -> prefix + name;

        printName(helloService, "ww");
    }
}