package net.baremodels.device.vaadin;

import org.junit.Test;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class VaadinDeviceTest {

    VaadinWidgetSupplier supplier = new VaadinWidgetSupplier();
    VaadinDevice testObject = new VaadinDevice();

    @Test
    public void can_create() {
        new VaadinDevice();
    }

    @Test
    public void display_returns_selected_model() throws Exception {
        fail();
    }

    @Test
    public void display_adds_translated_component() throws Exception {
        fail();
    }

    @Test
    public void onIntent_relays_intent_to_constructor_handler() {
        fail();
    }

}
