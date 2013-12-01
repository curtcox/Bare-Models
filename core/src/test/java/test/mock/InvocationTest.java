package test.mock;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class InvocationTest {

    Object proxy = new Object();
    Method method = proxy.getClass().getDeclaredMethods()[0];

    @Test
    public void can_create_with_empty_array() {
        Invocation invocation = new Invocation(proxy,method,new Object[0]);

        assertSame(proxy,invocation.proxy);
        assertSame(method,invocation.method);
        assertEquals(0,invocation.args.size());
    }

    @Test
    public void equal_values() {
        assertEquals(new Invocation(proxy,method,new Object[0]),new Invocation(proxy,method,new Object[0]));
    }

    @Test
    public void unequal_values() {
        assertFalse(new Invocation(proxy, method, new Object[0]).equals(new Invocation(proxy, method, new Object[1])));
    }

}
