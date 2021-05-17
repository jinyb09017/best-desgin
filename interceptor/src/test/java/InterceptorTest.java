import com.interceptor.Target;
import com.interceptor.impl.AuditInterceptor;
import com.interceptor.impl.InterceptInvocation;
import com.interceptor.impl.LoggerInterceptor;
import com.interceptor.model.Request;
import com.interceptor.model.Response;
import org.junit.Test;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-12 17:26
 * @description：
 * @modified By：
 * @version:
 */

public class InterceptorTest {
    @Test
    public void test() {
        System.out.println("haha");
    }


    @Test
    public void testIntercept() {
        InterceptInvocation interceptInvocation = new InterceptInvocation();
        interceptInvocation.addInterceptor(new LoggerInterceptor());
        interceptInvocation.addInterceptor(new AuditInterceptor());
        interceptInvocation.setTarget(new Target() {
            @Override
            public Response execute(Request request) {
                System.out.println("doing request");
                return new Response();
            }
        });
        interceptInvocation.invoke();
    }
}