package test.mock;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;

public class InvocationTest {

    Object proxy = new Object();
    Method method = proxy.getClass().getDeclaredMethods()[0];

    @Test
    public void can_create_with_empty_array() {
        Invocation invocation = new Invocation(proxy,method,args());

        assertSame(proxy,invocation.proxy);
        assertSame(method,invocation.method);
        assertEquals(0,invocation.args.size());
    }

    @Test
    public void equal_values_are_equals() {
        assertEquals(new Invocation(proxy,method,args()),new Invocation(proxy,method,args()));
        assertEquals(new Invocation(proxy,method,args("")),new Invocation(proxy,method,args("")));
        assertEquals(new Invocation(proxy,method,args(new ArrayList())),new Invocation(proxy,method,args(Collections.emptyList())));
    }

    @Test
    public void mock_equals_itself() {
        Map map = Mocks.mock("foo", Map.class);
        assertEquals(new Invocation(proxy, method, args(map)), new Invocation(proxy, method, args(map)));
    }

    @Test
    public void different_arguments_are_not_equal() {
        assertFalse(new Invocation(proxy, method,args()).equals(new Invocation(proxy, method,args(null))));
        assertFalse(new Invocation(proxy, method,args(0)).equals(new Invocation(proxy, method,args(1))));
    }

    @Test
    public void different_proxy_is_not_equal() {
        Object different = new Object();
        assertFalse(new Invocation(proxy, method,args()).equals(new Invocation(different, method,args())));
    }

    @Test
    public void different_method_is_not_equal() {
        Method different = proxy.getClass().getDeclaredMethods()[1];
        assertFalse(new Invocation(proxy, method,args()).equals(new Invocation(proxy, different,args())));
    }

    Object[] args(Object... args) {
        if (args==null) {
            return new Object[] {null};
        }
        return args;
    }
}
