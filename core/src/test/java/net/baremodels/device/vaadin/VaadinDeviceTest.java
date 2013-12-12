package net.baremodels.device.vaadin;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import net.baremodels.apps.Nucleus;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UILabel;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;
import java.util.function.Supplier;

import static org.junit.Assert.assertTrue;
import static test.mock.Mocks._;
import static test.mock.Mocks.verify;

public class VaadinDeviceTest {

    UIComponent ui = new UILabel("jello");
    Intent intent = new Intent(null){};
    Model model = ModelFactory.DEFAULT.of(new Nucleus());
    Supplier<Model> modelSupplier;
    SimpleComponentTranslator translator = new SimpleComponentTranslator(new VaadinWidgetSupplier(), new VaadinLayoutSupplier(new HashMap<>()));
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
        testObject.display(ui);

        Component content = testObject.getContent();

        assertTrue(content instanceof TextField);
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
