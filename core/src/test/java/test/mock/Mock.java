package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.fail;
import static test.mock.Mocks.*;
import static test.mock.Mocks.Phase.*;

public final class Mock {

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

            if (phase == when)   { return invoke(invocation); }
            if (phase == test)   { return invoke(invocation); }
            if (phase == verify) { return verify(invocation); }
            if (phase == no)     { return no(invocation);     }

            throw new IllegalStateException("Phase = " + Mocks.phase);
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
            throw new UnsupportedOperationException(this + " " + method.toString());
        }

        @Override
        public String toString() {
            return name + ":" +clazz.toString() + "@" + System.identityHashCode(this);
        }
    }

    public <T> T mock(Class<T> clazz, String name, Map<Class,Object> values) {
        ClassLoader loader = Mock.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { clazz };
        InvocationHandler handler = new MockInvocationHandler(clazz,name,values);
        return clazz.cast(Proxy.newProxyInstance(loader, interfaces, handler));
    }

    public <T> void when(T condition, T result) {
        whens.put(invocation, result);
    }
}
