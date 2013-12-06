package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * For making mocks, to use in testing.
 */
final class MockFactory {

    Invocation latest;
    final Map<Invocation,Object> whens = new HashMap<>();
    final Map<Invocation,Object> invoked = new HashMap<>();

    <T> T mock(Class<T> clazz, String name, Map<Class,Object> values) {
        ClassLoader loader = MockFactory.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { clazz };
        InvocationHandler handler = new MockInvocationHandler(this,clazz,name,values);
        return clazz.cast(Proxy.newProxyInstance(loader, interfaces, handler));
    }

    <T> void when(T condition, T result) {
        if (latest ==null) {
            String message = String.format("No method has been invoked that could return [%s]",result);
            throw new IllegalStateException(message);
        }
        checkValueOkForReturn(condition, latest);
        checkValueOkForReturn(result, latest);
        whens.put(latest, result);
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
