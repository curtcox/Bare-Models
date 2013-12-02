package test.mock;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static test.mock.Mocks.no;
import static test.mock.Mocks.verify;

public class MocksFactoryTest {

    MockFactory testObject = new MockFactory();
    interface Sample {
        String methodWithNoArgs();
        String methodWithOneArg(String arg);
        boolean methodThatReturnsBoolean();
    }
    Method methodWithNoArgs = method(Sample.class, "methodWithNoArgs");
    Method methodWithOneArg = method(Sample.class, "methodWithOneArg");

    private static Method method(Class c, String name) {
        for (Method method : c.getDeclaredMethods()) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        throw new IllegalArgumentException(name);
    }

    @Before
    public void init() {
        Phase.current = null;
    }

    @Test
    public void can_create() {
        new MockFactory();
    }

    @Test
    public void mock_returns_instance_of_given_interface() {
        assertTrue(newMockSample() instanceof Sample);
    }

    @Test
    public void mock_uses_supplied_default_values() {
        Map<Class,Object> values = new HashMap(){{
            put(boolean.class,true);
            put(String.class,"coffee");
        }};
        Sample mock = testObject.mock(Sample.class, "name", values);

        assertEquals(true,mock.methodThatReturnsBoolean());
        assertEquals("coffee",mock.methodWithNoArgs());
        assertEquals("coffee",mock.methodWithOneArg("ignore me"));
    }

    private static Map<Class,Object> defaultValues = new HashMap() {{
        put(boolean.class,false);
    }};

    private Sample newMockSample() {
        return testObject.mock(Sample.class, "name", defaultValues);
    }

    @Test
    public void toString_uses_name_class_and_system_hash() {
        Object mock = newMockSample();
        String expected = "name:" + Sample.class.toString() + "@" + System.identityHashCode(Proxy.getInvocationHandler(mock));
        String actual = mock.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void when_makes_mock_return_value_on_next_invocation() {
        Sample mock = newMockSample();
        String expected = "next";

        testObject.when(mock.methodWithNoArgs(), expected);

        String actual = mock.methodWithNoArgs();
        assertSame(expected,actual);
    }

    @Test
    public void when_makes_mock_return_primitive_on_next_invocation() {
        Sample mock = newMockSample();
        boolean expected = true;

        testObject.when(mock.methodThatReturnsBoolean(), expected);

        boolean actual = mock.methodThatReturnsBoolean();
        assertSame(expected,actual);
    }

    @Test
    public void last_when_wins_for_the_same_invocation() {
        Sample mock = newMockSample();
        String expected = "next";

        testObject.when(mock.methodWithNoArgs(), "first");
        testObject.when(mock.methodWithNoArgs(), expected);

        String actual = mock.methodWithNoArgs();
        assertSame(expected,actual);
    }

    @Test
    public void when_works_with_multiple_whens_on_different_methods() {
        Sample mock = newMockSample();

        testObject.when(mock.methodWithNoArgs(), "no args");
        testObject.when(mock.methodWithOneArg("1"), "one arg");

        assertEquals("no args",mock.methodWithNoArgs());
        assertEquals("one arg", mock.methodWithOneArg("1"));
    }

    @Test
    public void invoke_makes_mock_return_value_on_next_invocation() {
        Sample mock = newMockSample();
        String expected = "next";

        testObject.when(mock.methodWithNoArgs(), expected);

        String actual = mock.methodWithNoArgs();
        assertSame(expected,actual);
    }

    @Test
    public void when_throws_exception_if_there_are_no_invocations() {
        MockFactory testObject = new MockFactory();

        try {
            testObject.when("condition", "value");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("No method has been invoked that could return [value]",e.getMessage());
        }
    }

    @Test
    public void when_throws_exception_if_invocation_is_incompatible_with_value() {
        Sample mock = newMockSample();

        try {
            testObject.when(mock.methodWithNoArgs(), true);
            fail();
        } catch (IllegalStateException e) {
            String message = String.format("[%s] is not a valid value for [%s]",true,methodWithNoArgs);
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void when_throws_exception_if_invocation_is_incompatible_with_condition() {
        Sample mock = newMockSample();

        try {
            mock.methodWithNoArgs();
            testObject.when(true, "foo");
            fail();
        } catch (IllegalStateException e) {
            String message = String.format("[%s] is not a valid value for [%s]",true,methodWithNoArgs);
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void verify_does_not_fail_when_method_invoked() {
        Sample mock = newMockSample();

        mock.methodWithNoArgs();
        verify();

        mock.methodWithNoArgs();
    }

    @Test
    public void method_returns_previous_value_when_invoked_during_verify() {
        Sample mock = newMockSample();

        String arg = "jello";
        String result = "pudding";
        testObject.when(mock.methodWithOneArg(arg), result);

        mock.methodWithOneArg(arg);

        verify();

        assertSame(result, mock.methodWithOneArg(arg));
    }

    @Test
    public void verify_fails_when_method_not_invoked() {
        Sample mock = newMockSample();

        verify();

        try {
            mock.methodWithNoArgs();
        } catch (AssertionError e) {
            String message = "Missing invocation " + new Invocation(mock,methodWithNoArgs, new Object[0]);
            assertEquals(message,e.getMessage());
            return;
        }
        fail();
    }

    @Test
    public void verify_fails_when_method_invoked_with_wrong_value() {
        Sample mock = newMockSample();

        mock.methodWithOneArg("wrong");

        verify();

        try {
            mock.methodWithOneArg("right");
        } catch (AssertionError e) {
            Invocation expected = new Invocation(mock,methodWithOneArg, new Object[] {"right"});
            Invocation received = new Invocation(mock,methodWithOneArg, new Object[] {"wrong"});
            String message = String.format("Expected [%s], but received [%s]",expected,received);
            assertEquals(message,e.getMessage());
            return;
        }
        fail();
    }

    @Test
    public void no_does_not_fail_when_method_not_invoked() {
        Sample mock = newMockSample();

        no();

        mock.methodWithNoArgs();
    }

    @Test
    public void no_returns_null_when_method_not_invoked() {
        Sample mock = newMockSample();

        no();

        assertEquals(null,mock.methodWithOneArg(""));
    }

    @Test
    public void no_fails_when_method_invoked() {
        Sample mock = newMockSample();

        mock.methodWithNoArgs();
        no();

        try {
            mock.methodWithNoArgs();
        } catch (AssertionError e) {
            String message = "Unwanted invocation " + new Invocation(mock,methodWithNoArgs, new Object[0]);
            assertEquals(message,e.getMessage());
            return;
        }
        fail();
    }

}
