package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertSame;
import static test.mock.Mocks._;

public class SimpleContainerTranslatorTest {

    Model model;
    WidgetSupplier widgetSupplier;
    ComponentConstraintSupplier componentConstraintSupplier;
    UILayout layout = new UILayout();
    UIComponent.Listener listener;
    SimpleContainerTranslator testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new SimpleContainerTranslator(widgetSupplier, componentConstraintSupplier);
    }

    @Test
    public void translate_uses_layoutSupplier_from_constructor_for_container() {
        UIContainer container = SimpleUIContainer.of(model);
        Object expected = new Object();
        Collection components = Collections.emptyList();

        _(expected); widgetSupplier.container(container,components, componentConstraintSupplier);

        Object actual = testObject.translate(container, layout, listener);

        assertSame(expected,actual);
    }
}
