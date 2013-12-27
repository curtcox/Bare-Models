package net.baremodels.device.javafx;

import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.WaitingComponentListener;
import org.junit.Test;

public class JavaFxSyncDeviceTest {

    JavaFxWidgetSupplier supplier = new JavaFxWidgetSupplier();
    ComponentConstraintSupplier componentConstraintSupplier = new SimpleComponentConstraintSupplier(null);
    WaitingComponentListener listener = new WaitingComponentListener();
    SimpleContainerTranslator translator = new SimpleContainerTranslator(supplier, componentConstraintSupplier);

    @Test
    public void can_create() {
        new JavaFxSyncDevice(translator,listener,null);
    }

}
