package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;
import static test.mock.Phase.*;

/**
 * For making mocks, to use in testing.
 */
final class MockFactory {

    Invocation invocation;
    final Map<Invocation,Object> whens = new HashMap<>();
    final Map<Invocation,Object> invoked = new HashMap<>();

    class MockInvocationHandler
        implements InvocationHandler
    {
        final String name;
        final Map<Class,Object> values;
        final Class clazz;

        <T> MockInvocationHandler(Class clazz, String name, Map<Class,Object> values) {
            this.clazz = clazz;
            this.name = name;
            this.values = values;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("toString")) { return toString(); }
            invocation = new Invocation(proxy,method,args);
            if (current == verify) { return verify(invocation); }
            if (current == no)     { return no(invocation);     }
            return invoke(invocation);
        }

        private Object no(Invocation invocation) throws Throwable {
            if (invoked.containsKey(invocation)) {
                fail("Unwanted invocation " + invocation);
            }
            return null;
        }

        private Object verify(Invocation invocation) throws Throwable {
            if (!invoked.containsKey(invocation)) {
                fail("Missing invocation " + invocation);
            }
            return invoked.get(invocation);
        }

        private Object invoke(Invocation invocation) throws Throwable {
            if (whens.containsKey(invocation))   { return whens.get(invocation); }
            if (invoked.containsKey(invocation)) { return invoked.get(invocation); }
            Object result = _invoke(invocation);
            invoked.put(invocation, result);
            return result;
        }

        private Object _invoke(Invocation invocation) throws Throwable {
            Method method = invocation.method;
            Class returnType = method.getReturnType();
            if (values.containsKey(returnType)) { return values.get(returnType); }
            if (returnType.isInterface())       { return mock(returnType, name, values); }
            return null;
        }

        @Override
        public String toString() {
            return name + ":" +clazz.toString() + "@" + System.identityHashCode(this);
        }
    }

    <T> T mock(Class<T> clazz, String name, Map<Class,Object> values) {
        ClassLoader loader = MockFactory.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { clazz };
        InvocationHandler handler = new MockInvocationHandler(clazz,name,values);
        return clazz.cast(Proxy.newProxyInstance(loader, interfaces, handler));
    }

    <T> void when(T condition, T result) {
        if (invocation==null) {
            String message = String.format("No method has been invoked that could return [%s]",result);
            throw new IllegalStateException(message);
        }
        checkValueOkForReturn(condition,invocation);
        checkValueOkForReturn(result,invocation);
        whens.put(invocation, result);
    }

    void checkValueOkForReturn(Object value, Invocation invocation) {
        Class returnType = invocation.method.getReturnType();
        if (value==null || isInstanceConsideringBoxing(value, returnType)) {
            return;
        }
        String message = String.format("[%s] is not a valid value for [%s]",value,invocation.method);
        throw new IllegalStateException(message);
    }

    private boolean isInstanceConsideringBoxing(Object value, Class type) {
        if (type==boolean.class) {
            type = Boolean.class;
        }
        return type.isInstance(value);
    }
}
