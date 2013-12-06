package test.mock;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MockInvocationHandlerTest {

    String name;
    Map<Class,Object> values;
    Phase phase;
    Supplier<Phase> phaseSupplier = () -> phase;
    Class clazz;
    MockFactory factory = new MockFactory();
    Object proxy;
    Method method;
    Object[] args;

    MockInvocationHandler testObject = new MockInvocationHandler(factory,phaseSupplier,clazz,name,values);

    @Test
    public void can_create() {
        new MockInvocationHandler(factory,phaseSupplier,clazz,name,values);
    }

    @Test
    public void invoke_throws_exception_when_phase_is_null() throws Throwable {
        method = getMethod(Map.class,"size");
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
}
