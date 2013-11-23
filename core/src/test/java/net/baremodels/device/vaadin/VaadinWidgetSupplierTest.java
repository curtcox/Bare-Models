package net.baremodels.device.vaadin;

import com.vaadin.ui.Button;
import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VaadinWidgetSupplierTest {

    Model model = ModelFactory.DEFAULT.of(new Nucleus());
    Model teams = model.properties().get("teams").model();
    UIComponent.Listener listener = null;

    VaadinWidgetSupplier testObject = new VaadinWidgetSupplier();

    @Test
    public void button_text() {
        Button button = testObject.button(new UIButton(teams,"a"),listener);
        assertEquals("a",button.getId());
    }

}
