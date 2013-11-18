package net.baremodels.device.javafx;

import net.baremodels.runner.SimpleComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import org.junit.Test;

public class JavaFxDeviceTest {

    JavaFxWidgetSupplier supplier = new JavaFxWidgetSupplier();
    SimpleComponentListener listener = new SimpleComponentListener();
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier);

    @Test
    public void can_create() {
        new JavaFxDevice(translator,listener,null);
    }

}
