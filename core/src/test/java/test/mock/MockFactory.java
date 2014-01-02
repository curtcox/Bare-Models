package test.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static test.mock.Phase.current;
/**
 * For making mocks, to use in testing.
 * During normal usage, there is one MockFactory per JUnit test class.
 */
final class MockFactory {

    /**
     * The last specified return.
     */
    Object result;

    /**
     * Create the specified mock.
     */
    <T> T mock(Class<T> clazz, String name) {
        ClassLoader loader = MockFactory.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { clazz };
        InvocationHandler handler = new MockInvocationHandler(this,clazz,name);
        return clazz.cast(Proxy.newProxyInstance(loader, interfaces, handler));
    }

    /**
     * Used to specify the glyph to be returned.
     * The next mock invocation will specify what returns it.
     */
    void returns(Object value) {
        if (result != null) {
            String message = String.format("Return glyph [%s] hasn't been mapped, yet.",result);
            throw new IllegalStateException(message);
        }
        result = value;
        current = Phase.returns;
    }

}
