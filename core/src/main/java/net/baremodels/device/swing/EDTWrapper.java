package net.baremodels.device.swing;

import java.awt.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * For ensuring access is limited to the EDT.
 */
final class EDTWrapper {

    static <T> T of(final T t) {
        ClassLoader loader = t.getClass().getClassLoader();
        Class<?>[] interfaces = t.getClass().getInterfaces();
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (!EventQueue.isDispatchThread()) {
                    String message = String.format("%s is only valid from EDT",method);
                    throw new IllegalThreadStateException(message);
                }
                return method.invoke(t,args);
            }
        };
        return (T) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
