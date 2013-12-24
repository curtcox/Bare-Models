package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SimpleAppContextTest {

    Model model = ModelFactory.DEFAULT.of("");
    UIContainer container = SimpleUIContainer.of(model);
    DeviceState deviceState = new DeviceState(25,25);
    SimpleAppContext testObject = new SimpleAppContext();

    @Test
    public void is_an_AppContext() {
        assertTrue(testObject instanceof AppContext);
    }

    @Test
    public void layout_returns_a_UILayout() {
        assertTrue(testObject.layout(container,deviceState) instanceof UILayout);
    }

}
