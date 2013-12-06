package test.mock;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class PhaseSupplierTest {

    MockFactory mockFactory;
    UnderTest underTest;
    PhaseSupplier testObject;
    IMocked mocked;

    static class UnderTest {
        final IMocked mocked;

        UnderTest(IMocked mocked) {
            this.mocked = mocked;
        }

        String stuff() {
            return mocked.stuff();
        }
    }

    interface IMocked {
        String stuff();
    }

    @Before
    public void init() {
        mockFactory = new MockFactory();
        testObject = new PhaseSupplier(this);
        mocked = mockFactory.mock(IMocked.class,"mocked", testObject, Collections.EMPTY_MAP);
        underTest = new UnderTest(mocked);
    }

    @Test
    public void get_returns_verify_when_current_is_verify() {
        Phase.current = Phase.verify;
        assertSame(Phase.verify, testObject.get());
    }

    @Test
    public void get_returns_no_when_current_is_no() {
        Phase.current = Phase.no;
        assertSame(Phase.no, testObject.get());
    }


    @Test
    public void get_returns_invoke_when_current_is____invoke_or_when____and_called_through_test_object() {
        Phase.current = Phase.invoke_or_when;
        String expected = "unlikely";
        mockFactory.when(mocked.stuff(),expected);

        String actual = underTest.stuff();

        assertSame(expected,actual);
    }

    @Test
    public void get_returns_when_if_current_is____invoke_or_when____and_not_called_through_test_object() {
        Phase.current = Phase.invoke_or_when;
        assertSame(Phase.when, testObject.get());
    }

    @Test
    public void get_throws_exception_when_current_is_null_and_not_invoking_mock() {
        try {
            testObject.get();
            fail();
        } catch (IllegalStateException e) {
            String message = "This should only be used in a mock";
            assertEquals(message,e.getMessage());
        }
    }

}
