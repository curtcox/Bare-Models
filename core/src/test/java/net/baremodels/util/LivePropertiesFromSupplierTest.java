package net.baremodels.util;

import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.Properties;
import java.util.function.Supplier;

import static test.mock.Mocks._;
import static org.junit.Assert.*;

public class LivePropertiesFromSupplierTest {

    Properties properties = new Properties();
    Supplier<Properties> supplier;
    LivePropertiesFromSupplier testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new LivePropertiesFromSupplier(supplier);
        _(properties); supplier.get();
    }

    @Test
    public void get_returns_value_from_supplier() {
        String key = randomString();
        String value = differentRandomString();
        properties.put(key,value);

        String actual = testObject.get(key);

        assertEquals(value, actual);
    }

    private String differentRandomString() {
        return toString();
    }

    private String randomString() {
        return toString() + " stuff ";
    }
}
