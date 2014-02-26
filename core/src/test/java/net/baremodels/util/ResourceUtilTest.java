package net.baremodels.util;

import org.junit.Test;

import java.util.MissingResourceException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResourceUtilTest {

    @Test
    public void properties_throws_helpful_exception_when_missing_resource() {
        try {
            ResourceUtil.properties("these_properties_do_not_exist");
        } catch (MissingResourceException e) {
            assertEquals("Unable to find : these_properties_do_not_exist",e.getMessage());
        }
    }

    @Test
    public void properties_return_properties_when_resource_is_a_properties_file() {
        assertTrue(ResourceUtil.properties("/config.properties") instanceof Properties);
    }

}
