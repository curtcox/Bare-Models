package net.baremodels.device.javafx;

import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import org.junit.Test;

public class JavaFxDeviceTest {

    JavaFxWidgetSupplier supplier = new JavaFxWidgetSupplier();
    JavaFxLayoutSupplier layoutSupplier = new JavaFxLayoutSupplier();
    WaitingComponentListener listener = new WaitingComponentListener();
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier,layoutSupplier);

    @Test
    public void can_create() {
        new JavaFxDevice(translator,listener,null);
    }

}
