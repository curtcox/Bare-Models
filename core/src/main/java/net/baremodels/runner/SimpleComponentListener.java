package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;
import net.baremodels.util.TimeUtil;

/**
 * A component failureListener that remembers the last model selected.
 */
public final class SimpleComponentListener
    implements UIComponent.Listener
{
    private Model selected;
    private boolean changed;

    @Override
    public void onSelected(Model model) {
        System.out.println("selected " + model);
        selected = model;
        changed = true;
    }

    public Model waitForSelectionChange() {
        TimeUtil.waitUntil(() -> changed);
        changed = false;
        return selected;
    }
}
