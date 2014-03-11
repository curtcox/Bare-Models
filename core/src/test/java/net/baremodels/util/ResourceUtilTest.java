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

    @Test
    public void findResourceSource_returns_resource_path_when_it_exists() {
        String given = "C:/Users/Curt/Documents/GitHub/Bare-Models/core/target/classes/fontawesome-webfont.ttf";
        String expected = "C:/Users/Curt/Documents/GitHub/Bare-Models/core/src/main/resources/fontawesome-webfont.ttf";
        String actual = ResourceUtil.findResourceSource(given);
        assertEquals(expected,actual);
    }

    @Test
    public void findResourceSource_returns_test_resource_path_when_it_exists() {
        String given = "C:/Users/Curt/Documents/GitHub/Bare-Models/core/target/test-classes/config.properties";
        String expected = "C:/Users/Curt/Documents/GitHub/Bare-Models/core/src/test/resources/config.properties";
        String actual = ResourceUtil.findResourceSource(given);
        assertEquals(expected,actual);
    }
}
