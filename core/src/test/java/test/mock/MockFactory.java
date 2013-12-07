package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static test.mock.Phase.current;
/**
 * For making mocks, to use in testing.
 */
final class MockFactory {

    Object result;

    <T> T mock(Class<T> clazz, String name) {
        ClassLoader loader = MockFactory.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { clazz };
        InvocationHandler handler = new MockInvocationHandler(this,clazz,name);
        return clazz.cast(Proxy.newProxyInstance(loader, interfaces, handler));
    }

    void returns(Object value) {
        if (result != null) {
            String message = String.format("Return value [%s] hasn't been mapped, yet.",result);
            throw new IllegalStateException(message);
        }
        result = value;
        current = Phase.returns;
    }

}
