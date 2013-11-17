package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public final class TextRunner
    extends SimpleRunner
{
    public TextRunner(FakeUser user, Model.Listener modelListener, Intent.Listener intentListener) {
        super(new SimpleModelRenderer(), new TextDevice(user,intentListener), modelListener);
    }
}