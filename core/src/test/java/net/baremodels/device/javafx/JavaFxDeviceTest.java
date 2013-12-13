package net.baremodels.device.javafx;

import net.baremodels.runner.LayoutSupplier;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.runner.SimpleLayoutSupplier;
import net.baremodels.runner.WaitingComponentListener;
import org.junit.Test;

import java.util.HashMap;

public class JavaFxDeviceTest {

    JavaFxWidgetSupplier supplier = new JavaFxWidgetSupplier();
    LayoutSupplier layoutSupplier = new SimpleLayoutSupplier(null,new HashMap<>());
    WaitingComponentListener listener = new WaitingComponentListener();
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier,layoutSupplier);

    @Test
    public void can_create() {
        new JavaFxDevice(translator,listener,null);
    }

}
