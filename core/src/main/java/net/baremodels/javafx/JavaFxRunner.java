package net.baremodels.javafx;

import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public class JavaFxRunner
    extends SimpleRunner
{
    public JavaFxRunner() {
        super(new SimpleModelRenderer(), JavaFxDevice.newInstance());
    }
}
