package net.baremodels.runner;

import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

import java.util.HashMap;

public final class SimpleAppContext
    implements AppContext
{
    @Override
    public UILayout layout(UIContainer ui) {
        return new UILayout(new HashMap<>());
    }
}
