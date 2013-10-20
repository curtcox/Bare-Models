package net.baremodels.uat;

import ionic.app.NucleusTestFactory;
import junit.framework.AssertionFailedError;
import net.baremodels.apps.Nucleus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UATTest {

    UAT testObject = new UAT();

    @Test
    public void select_ok_when_object_is_on_screen() {
        List list = new ArrayList();
        testObject.show(list);
        testObject.select(list);
    }

    @Test
    public void select_fails_when_object_is_not_on_screen() {
        String object = this + "";
        try {
            List list = new ArrayList();
            testObject.show(list);
            testObject.select(object);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(String.format("[%s] is not on screen",object),e.getMessage());
        }
    }

    @Test
    public void assertScreenContains_ok_when_value_is_on_screen_for_list_element() {
        List list = new ArrayList();
        list.add("fantastic");
        testObject.show(list);
        testObject.assertScreenContains("fantastic");
    }

    @Test
    public void assertScreenContains_ok_when_value_is_on_screen_for_Nucleus() {
        Nucleus nucleus = NucleusTestFactory.newNucleus();
        testObject.show(nucleus);
        testObject.assertScreenContains("Nucleus");
    }

    @Test
    public void assertScreenContains_fails_when_value_is_not_on_screen() {
        try {
            List list = new ArrayList();
            testObject.show(list);
            testObject.assertScreenContains("fantastic");
            fail();
        } catch (AssertionFailedError e) {
            String message = e.getMessage();
            assertTrue(message,message.startsWith("fantastic not found in"));
        }
    }

}
