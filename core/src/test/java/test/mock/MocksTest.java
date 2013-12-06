package test.mock;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;
import static test.mock.Mocks.*;
import static test.mock.Phase.*;

public class MocksTest {

    String value = "use this";
    Sample sample;

    interface Sample {
       String getValue();
    }

    @Before
    public void init() {
        Mocks.init(this);
    }

    @Test
    public void init_assigns_values_to_null_instance_variables_that_are_interfaces() {
        assertTrue(sample instanceof Sample);
    }

    @Test
    public void init_uses_instance_variable_values() {
        assertSame(value,sample.getValue());
    }

    @Test
    public void init_set_current_state_to_when() {
        assertSame(when,current);
    }

    @Test
    public void mock_implements_specified_interface() {
        assertTrue(mock("name",Sample.class) instanceof Sample);
    }

    @Test
    public void mock_sets_current_state_to_when() {
        mock("name",Sample.class);
        assertSame(when,current);
    }

    @Test
    public void when_makes_mock_return_specified_value() {
        Sample sample = mock("name",Sample.class);
        String expected = "expected";
        when(sample.getValue(),expected);

        String actual = sample.getValue();

        assertSame(expected,actual);
    }

    @Test
    public void mock_value_arg_makes_mock_return_specified_value() {
        String expected = "expected";
        Sample sample = mock("name",Sample.class,expected);

        String actual = sample.getValue();

        assertSame(expected,actual);
    }

    @Test
    public void verify_sets_current_state_to_verify() {
        verify();
        assertSame(verify,current);
    }

    @Test
    public void no_sets_current_state_to_no() {
        no();
        assertSame(no,current);
    }

    @Test
    public void verify_does_not_fail_when_method_invoked() {
        Sample sample = mock("name",Sample.class,"expected");

        sample.getValue();
        verify();

        sample.getValue();
    }

    @Test
    public void verify_fails_when_method_not_invoked() {
        Sample sample = mock("name",Sample.class,"expected");

        verify();

        try {
            sample.getValue();
        } catch (AssertionError e) {
            return;
        }
        fail();
    }

    @Test
    public void no_does_not_fail_when_method_not_invoked() {
        Sample sample = mock("name",Sample.class,"expected");

        no();

        sample.getValue();
    }

    @Test
    public void no_fails_when_method_invoked() {
        Sample sample = mock("name",Sample.class,"expected");

        sample.getValue();
        no();

        try {
            sample.getValue();
        } catch (AssertionError e) {
            return;
        }
        fail();
    }

    @Test
    public void toString_returns_name_class_and_hash() {
        Sample sample = mock("name",Sample.class,"expected");
        String expected = "name:" + Sample.class + "@" + System.identityHashCode(Proxy.getInvocationHandler(sample));
        String actual = sample.toString();
        assertEquals(expected,actual);
    }
}
