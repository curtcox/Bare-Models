package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertSame;
import static test.mock.Mocks._;

public class SimpleComponentTranslatorTest {

    Model model;
    WidgetSupplier widgetSupplier;
    LayoutSupplier layoutSupplier;
    UIComponent.Listener listener;
    SimpleComponentTranslator testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new SimpleComponentTranslator(widgetSupplier,layoutSupplier);
    }

    @Test
    public void translate_uses_layoutSupplier_from_constructor_for_container() {
        UIContainer container = SimpleUIContainer.of(model);
        Object expected = new Object();
        Collection components = Collections.emptyList();

        _(expected); widgetSupplier.container(container,components,layoutSupplier);

        Object actual = testObject.translate(container, listener);

        assertSame(expected,actual);
    }
}
