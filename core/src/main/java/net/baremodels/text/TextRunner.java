package net.baremodels.text;

import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public final class TextRunner
    extends SimpleRunner
{
    public TextRunner(FakeUser user) {
        super(new SimpleModelRenderer(), new TextDevice(user));
    }
}