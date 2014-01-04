package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleSyncRunner;

public final class TextRunner
    extends SimpleSyncRunner
{
    public TextRunner(AppContext appContext, FakeUser user, Model.Listener listener, Intent.Handler handler) {
        super(appContext, new TextDevice(user,handler), listener);
    }
}