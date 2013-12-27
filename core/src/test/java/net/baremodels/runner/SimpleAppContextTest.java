package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.*;
import net.baremodels.ui.UIComponent.Matcher;
import net.baremodels.ui.UILayout.Constraints;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static test.mock.Mocks._;

public class SimpleAppContextTest {

    Model model = ModelFactory.DEFAULT.of("");
    Matcher matcher;
    UIComponent component = new UILabel("label text here");
    UIContainer container = SimpleUIContainer.of(model,component);
    DeviceState deviceState = new DeviceState(25,25);

    SimpleAppContext testObject = new SimpleAppContext();

    @Before
    public void init() {
        Mocks.init(this);
        _(true); matcher.matches(component);
    }

    @Test
    public void is_an_AppContext() {
        assertTrue(testObject instanceof AppContext);
    }

    @Test
    public void layout_returns_a_UILayout() {
        assertTrue(testObject.layout(container,deviceState) instanceof UILayout);
    }

    @Test
    public void layout_returns_a_UILayout_with_constraints_for_all_components_in_the_container() {
        Constraints constraints = new Constraints("wrap");

        assertEquals(constraints, testObject.layout(container, deviceState).getConstraints(component));
    }
}
