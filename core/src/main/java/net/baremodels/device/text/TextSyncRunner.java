package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.NextModelGenerator;
import net.baremodels.runner.SimpleSyncRunner;

public final class TextSyncRunner
    extends SimpleSyncRunner
{
    public TextSyncRunner(AppContext appContext, FakeUser user, NextModelGenerator generator, Model.Listener listener, Intent.Handler handler) {
        super(appContext, new TextSyncDevice(user,handler), generator, listener);
    }
}