package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.fail;
import static test.mock.Phase.*;

final class MockFactory {

    Invocation invocation;
    final Map<Invocation,Object> whens = new HashMap<>();
    final Set<Invocation> invoked = new HashSet<>();

    class MockInvocationHandler implements InvocationHandler {
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
            invocation = new Invocation(proxy,method,args);

            if (current == other)  { return invoke(invocation); }
            if (current == verify) { return verify(invocation); }
            if (current == no)     { return no(invocation);     }

            throw new IllegalStateException("Phase = " + current);
        }

        private Object no(Invocation invocation) throws Throwable {
            if (invoked.contains(invocation)) {
                fail();
            }
            return null;
        }

        private Object verify(Invocation invocation) throws Throwable {
            if (!invoked.contains(invocation)) {
                fail();
            }
            return null;
        }

        private Object invoke(Invocation invocation) throws Throwable {
            invoked.add(invocation);
            Method method = invocation.method;
            if (whens.containsKey(invocation)) {
                return whens.get(invocation);
            }
            Class returnType = method.getReturnType();
            if (method.getName().equals("toString")) { return toString(); }
            if (values.containsKey(returnType)) {
                return values.get(returnType);
            }
            if (returnType.isInterface()) {
                return mock(returnType, name, values);
            }
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
        whens.put(invocation, result);
    }
}
