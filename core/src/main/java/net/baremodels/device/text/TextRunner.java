package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public final class TextRunner
    extends SimpleRunner
{
    public TextRunner(FakeUser user, Model.Listener listener, Intent.Handler handler) {
        super(new SimpleModelRenderer(), new TextDevice(user,handler), listener);
    }
}