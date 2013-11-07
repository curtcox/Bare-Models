package net.baremodels.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StreetAddressTest {

    @Test
    public void toString_formats_address() {
        StreetAddress testObject = new StreetAddress();
        testObject.city = "University City";
        testObject.state = "MO";
        testObject.zip = "63130";
        testObject.line1 = "7425 Amherst";

        assertEquals("7425 Amherst, University City, MO 63130",testObject.toString());
    }
}
