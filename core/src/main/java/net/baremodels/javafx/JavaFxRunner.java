package net.baremodels.javafx;

import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public class JavaFxRunner
    extends SimpleRunner
{
    public JavaFxRunner(Model.Listener listener) {
        super(new SimpleModelRenderer(), JavaFxDevice.newInstance(), listener);
    }
}
