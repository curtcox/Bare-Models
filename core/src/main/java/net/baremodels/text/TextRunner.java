package net.baremodels.text;

import net.baremodels.intent.Intent;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public final class TextRunner
    extends SimpleRunner
{
    public TextRunner(FakeUser user, Intent.Listener listener) {
        super(new SimpleModelRenderer(), new TextDevice(user,listener));
    }
}