package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public final class Mock {

    Map<Invocation,Object> invocations = new HashMap<>();
    Invocation invocation;

    public <T> T mock(Class<T> clazz, String name, Map<Class,Object> values) {
        ClassLoader loader = Mock.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { clazz };

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                invocation = new Invocation(proxy,method,args);
                if (invocations.containsKey(invocation)) {
                    return invocations.get(invocation);
                }
                Object result = doInvoke(proxy,method,args);
                invocations.put(invocation,result);
                return result;
            }

            private Object doInvoke(Object proxy, Method method, Object[] args) throws Throwable {
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

        };
        Object proxy = Proxy.newProxyInstance(loader, interfaces, handler);
        return clazz.cast(proxy);
    }

    public void verify() {
    }

    public <T> T no() {
        return null;
    }

    public void when(Object condition, Object result) {
        invocations.put(invocation,result);
    }
}
