package test.mock;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Collections;

import static org.junit.Assert.*;

public class MocksFactoryTest {

    MockFactory testObject = new MockFactory();
    interface Sample {
        String getValue();
    }

    @Test
    public void can_create() {
        new MockFactory();
    }

    @Test
    public void mock_returns_instance_of_given_interface() {
        assertTrue(newMockSample() instanceof Sample);
    }

    private Sample newMockSample() {
        return testObject.mock(Sample.class, "name", Collections.EMPTY_MAP);
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

        testObject.when(mock.getValue(), expected);

        String actual = mock.getValue();
        assertSame(expected,actual);
    }

    @Test
    public void invoke_makes_mock_return_value_on_next_invocation() {
        Sample mock = newMockSample();
        String expected = "next";

        testObject.when(mock.getValue(), expected);

        String actual = mock.getValue();
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
        MockFactory testObject = new MockFactory();
        Sample mock = testObject.mock(Sample.class, "name", Collections.EMPTY_MAP);

        try {
            testObject.when(mock.getValue(), true);
            fail();
        } catch (IllegalStateException e) {
            String message = String.format("[%s] is not a valid value for [%s]",true,Sample.class.getDeclaredMethods()[0]);
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void when_throws_exception_if_invocation_is_incompatible_with_condition() {
        MockFactory testObject = new MockFactory();
        Sample mock = testObject.mock(Sample.class, "name", Collections.EMPTY_MAP);

        try {
            mock.getValue();
            testObject.when(true, "foo");
            fail();
        } catch (IllegalStateException e) {
            String message = String.format("[%s] is not a valid value for [%s]",true,Sample.class.getDeclaredMethods()[0]);
            assertEquals(message,e.getMessage());
        }
    }

}
