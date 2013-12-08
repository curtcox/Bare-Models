package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

public final class TextRunner
    extends SimpleSyncRunner
{
    public TextRunner(FakeUser user, Model.Listener listener, Intent.Handler handler) {
        super(new SimpleModelRenderer(), new TextDevice(user,handler), listener);
    }
}