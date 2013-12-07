package test.mock;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static test.mock.Phase.current;

public class MockInvocationHandlerTest {

    String name;
    Class clazz;
    MockFactory factory = new MockFactory();
    Object proxy;
    Method method;
    Object[] args;

    MockInvocationHandler testObject = new MockInvocationHandler(factory,clazz,name);

    @Test
    public void can_create() {
        new MockInvocationHandler(factory,clazz,name);
    }

    @Test
    public void invoke_throws_exception_when_phase_is_null() throws Throwable {
        method = getMethod(Map.class,"size");
        current = null;
        try {
            testObject.invoke(proxy,method,args);
            fail();
        } catch (UnsupportedOperationException e) {
            String message = "Invalid phase : null";
            assertEquals(message,e.getMessage());
        }
    }

    private Method getMethod(Class c, String methodName) {
        for (Method method : c.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new UnsupportedOperationException(methodName);
    }

    @Test
    public void invoke_fails_when_method_invoked_with_wrong_value() throws Throwable {
        method = getMethod(Map.class,"get");
        factory.returns("???");
        testObject.invoke(proxy,method,new Object[] {"right"});

        try {
            testObject.invoke(proxy,method,new Object[] {"wrong"});
        } catch (UnsupportedOperationException e) {
            Invocation expected = new Invocation(testObject,method, new Object[] {"right"});
            Invocation received = new Invocation(testObject,method, new Object[] {"wrong"});
            String message = String.format("Expected [%s], but received [%s]",expected,received);
            assertEquals(message,e.getMessage());
            return;
        }
        fail();
    }
}
