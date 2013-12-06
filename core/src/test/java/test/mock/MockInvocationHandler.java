package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.fail;
import static test.mock.Phase.*;

final class MockInvocationHandler
    implements InvocationHandler
{
    final String name;
    final Map<Class,Object> values;
    final Class clazz;
    final MockFactory factory;

    <T> MockInvocationHandler(MockFactory factory, Class clazz, String name, Map<Class, Object> values) {
        this.factory = factory;
        this.clazz = clazz;
        this.name = name;
        this.values = values;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) { return toString(); }
        Invocation latest = new Invocation(proxy,method,args);
        factory.latest = latest;
        if (current == verify) { return verify(latest); }
        if (current == no)     { return no(latest);     }
        if (current == when)   { return when(latest);   }
        if (current == invoke) { return invoke(latest);   }
        throw new UnsupportedOperationException("Invalid phase : " + current);
    }

    private Object no(Invocation invocation) throws Throwable {
        if (invoked().containsKey(invocation)) {
            fail("Unwanted invocation " + invocation);
        }
        return null;
    }

    private Map<Invocation,Object> whens() {
        return factory.whens;
    }

    private Map<Invocation,Object> invoked() {
        return factory.invoked;
    }

    private Object verify(Invocation expected) throws Throwable {
        if (!invoked().containsKey(expected)) {
            for (Invocation received : invoked().keySet()) {
                if (received.method.equals(expected.method)) {
                    String message = String.format("Expected [%s], but received [%s]",expected,received);
                    fail(message);
                }
            }
            fail("Missing invocation " + expected);
        }
        return invoked().get(expected);
    }

    private Object when(Invocation invocation) throws Throwable {
        return result(invocation);
    }

    private Object invoke(Invocation invocation) throws Throwable {
        Object result = result(invocation);
        invoked().put(invocation, result);
        return result;
    }

    private Object result(Invocation invocation) throws Throwable {
        Map<Invocation,Object> whens = factory.whens;
        Object result;
        if (whens.containsKey(invocation)) {
            result = whens.get(invocation);
        } else {
            result = values.get(invocation.method.getReturnType());
        }
        return result;
    }

    @Override
    public String toString() {
        return name + ":" +clazz + "@" + System.identityHashCode(this);
    }
}
