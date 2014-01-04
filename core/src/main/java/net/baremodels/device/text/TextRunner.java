package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleSyncRunner;

public final class TextRunner
    extends SimpleSyncRunner
{
    public TextRunner(AppContext appContext, NavigationContext navigationContext, FakeUser user, Model.Listener listener, Intent.Handler handler) {
        super(appContext, navigationContext, new TextDevice(user,handler), listener);
    }
}