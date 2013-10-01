package net.baremodels.runner;

import ionic.app.NucleusTestFactory;
import net.baremodels.model.Model;
import net.baremodels.text.TextWidgetSupplier;
import net.baremodels.ui.*;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void label_is_label_name() {
        UILabel uiLabel = new UILabel("label text");
        String actual = testObject.translate(uiLabel, listener);
        assertEquals("label text",actual);
    }

    @Test
    public void button_contains_button_name_with_brackets() {
        UIButton uiButton = new UIButton(nucleus,"button name");
        String actual = testObject.translate(uiButton,listener);
        assertEquals("[button name]",actual);
    }

    @Test
    public void container_contains_contents_separated_by_commas() {
        UILabel label1 = new UILabel("label1");
        UILabel label2 = new UILabel("label2");
        UIContainer container = SimpleUIContainer.of(nucleus, label1, label2);

        String actual = testObject.translate(container,listener);

        assertEquals("[label1, label2]",actual);
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
