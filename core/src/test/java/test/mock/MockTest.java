package test.mock;

import org.junit.Test;
import static org.junit.Assert.*;
import static test.mock.Mocks.*;

public class MockTest {

    interface Sample {
       String getValue();
    }

    @Test
    public void mock_implements_specified_interface() {
        assertTrue(mock("name",Sample.class) instanceof Sample);
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

}
