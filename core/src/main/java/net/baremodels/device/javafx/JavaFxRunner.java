package net.baremodels.device.javafx;

import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

public class JavaFxRunner
    extends SimpleSyncRunner
{
    public JavaFxRunner(Model.Listener listener) {
        super(new SimpleModelRenderer(), JavaFxDevice.newInstance(), listener);
    }
}
