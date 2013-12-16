package net.baremodels.device.vaadin;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import net.baremodels.apps.Nucleus;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILabel;
import net.baremodels.ui.UILayout;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;
import java.util.function.Supplier;

import static org.junit.Assert.assertTrue;
import static test.mock.Mocks._;
import static test.mock.Mocks.verify;

public class VaadinDeviceTest {

    UILayout layout = new UILayout();
    UILabel component = new UILabel("foo");
    Intent intent = new Intent(null){};
    Model model = ModelFactory.DEFAULT.of(new Nucleus());
    UIContainer ui = SimpleUIContainer.of(model,component);
    Supplier<Model> modelSupplier;
    SimpleContainerTranslator translator = new SimpleContainerTranslator(new VaadinWidgetSupplier(), new SimpleComponentConstraintSupplier(new FormLayout(),new HashMap<>()));
    Intent.Handler handler;

    VaadinDevice testObject;

    @Before
    public void init() {
        Mocks.init(this);
        _(model); modelSupplier.get();
        _();      handler.onIntent(intent);
        testObject = new VaadinDevice(modelSupplier,translator,handler);
    }

    @Test
    public void can_create() {
        new VaadinDevice();
    }

    @Test
    public void display_sets_translated_content() throws Exception {
        testObject.display(ui,layout);

        Component content = testObject.getContent();

        assertTrue("Content = " + content,content instanceof FormLayout);
        FormLayout layout = (FormLayout) content;
        assertTrue(layout.getComponent(0) instanceof TextField);
    }

    @Test
    public void init_makes_runner_display_supplied_model() throws Exception {
        testObject.init(null);

        Component content = testObject.getContent();

        assertTrue(content instanceof FormLayout);
    }

    @Test
    public void onIntent_relays_intent_to_constructor_handler() {
        testObject.onIntent(intent);

        verify();

        handler.onIntent(intent);
    }

}
