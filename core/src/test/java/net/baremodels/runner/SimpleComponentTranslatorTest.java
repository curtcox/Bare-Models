package net.baremodels.runner;

import ionic.app.NucleusTestFactory;
import net.baremodels.model.Model;
import net.baremodels.text.TextWidgetSupplier;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleComponentTranslatorTest {

    Model nucleus = NucleusTestFactory.newNucleusModel();
    UIComponent ui = new SimpleModelRenderer().render(nucleus);
    private final SimpleComponentListener listener = new SimpleComponentListener();

    SimpleComponentTranslator testObject = new SimpleComponentTranslator(new TextWidgetSupplier());

    @Test
    public void nucleus_shows_all_top_level_elements() {
        String actual = testObject.translate(ui,listener);
        assertContains(actual,"Nucleus");
        assertContains(actual,"[teams]");
        assertContains(actual,"[users]");
        assertContains(actual,"[badges]");
        assertContains(actual,"[skills]");
    }

    @Test
    public void nucleus_has_no_extra_characters() {
        String actual = testObject.translate(ui,listener);
        assertFalse(actual, actual.contains("@"));
    }

    private void assertContains(String containing, String contained) {
        assertTrue(String.format("%s should contain %s",containing,contained),containing.contains(contained));
    }
}
