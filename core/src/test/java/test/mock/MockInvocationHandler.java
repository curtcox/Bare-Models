package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.junit.Assert.fail;
import static test.mock.Phase.*;

final class MockInvocationHandler
    implements InvocationHandler
{
    final String name;
    final Class clazz;
    final MockFactory factory;

    <T> MockInvocationHandler(MockFactory factory, Class clazz, String name) {
        this.factory = factory;
        this.clazz = clazz;
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) { return toString(); }
        Invocation latest = new Invocation(proxy,method,args);
        factory.latest = latest;
        if (current == no)     { return no(latest);     }
        if (current == returns){ return returns(latest);   }
        if (current == verify) { return verify(latest); }
        if (current == invoke) { return invoke(latest);   }
        throw new UnsupportedOperationException("Invalid phase : " + current);
    }

    private Object no(Invocation invocation) throws Throwable {
        factory.nos.add(invocation);
        current = invoke;
        return dummy(invocation);
    }

    private Object dummy(Invocation invocation) {
        if (invocation.method.getReturnType().equals(boolean.class)) {
            return false;
        }
        return null;
    }

    private Object returns(Invocation invocation) throws Throwable {
        factory.returns.put(invocation,factory.result);
        Object value = factory.result;
        factory.result = null;
        current = invoke;
        return value;
    }

    private Object verify(Invocation expected) throws Throwable {
        if (!factory.invoked.containsKey(expected)) {
            for (Invocation received : factory.invoked.keySet()) {
                if (received.method.equals(expected.method)) {
                    String message = String.format("Expected [%s], but received [%s]",expected,received);
                    fail(message);
                }
            }
            fail("Missing invocation " + expected);
        }
        return factory.invoked.get(expected);
    }

    private Object invoke(Invocation invocation) throws Throwable {
        if (factory.nos.contains(invocation)) {
            String message = "Unwanted invocation " + invocation;
            fail(message);
        }
        Object result = result(invocation);
        factory.invoked.put(invocation, result);
        return result;
    }

    private Object result(Invocation invocation) throws Throwable {
        Object value = factory.returns.get(invocation);
        if (value==null) {
            String message = String.format("[%s] is not defined for [%s]",invocation.method,this);
            throw new UnsupportedOperationException(message);
        }
        factory.invoked.put(invocation,value);
        return value;
    }

    @Override
    public String toString() {
        return name + ":" +clazz + "@" + System.identityHashCode(this);
    }
}
