package net.baremodels.device.swing;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class SwingWidgetSupplierTest {

    Model model = ModelFactory.DEFAULT.of(new Nucleus());
    Model teams = model.properties().get("teams").model();
    UIComponent.Listener listener = null;

    SwingWidgetSupplier testObject = new SwingWidgetSupplier();

    @Test
    public void button_text() {
        JButton button = testObject.button(new UIButton(teams,"a"),listener);
        assertEquals("a",button.getText());
    }

}