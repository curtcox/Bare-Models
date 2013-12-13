package net.baremodels.device.javafx;

import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.WaitingComponentListener;
import org.junit.Test;

import java.util.HashMap;

public class JavaFxDeviceTest {

    JavaFxWidgetSupplier supplier = new JavaFxWidgetSupplier();
    ComponentConstraintSupplier componentConstraintSupplier = new SimpleComponentConstraintSupplier(null,new HashMap<>());
    WaitingComponentListener listener = new WaitingComponentListener();
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier, componentConstraintSupplier);

    @Test
    public void can_create() {
        new JavaFxDevice(translator,listener,null);
    }

}
