package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;
import net.baremodels.util.TimeUtil;

/**
 * A component listener that remembers the last model selected.
 */
public final class WaitingComponentListener
    implements UIComponent.Listener
{
    private Model selected;
    private boolean changed;

    @Override
    public void onSelected(Model model) {
        selected = model;
        changed = true;
    }

    /**
     * Return the new model, as soon as the selection changes.
     * Note that this is a blocking call.
     */
    public Model waitForSelectionChange() {
        TimeUtil.waitUntil(() -> changed);
        changed = false;
        return selected;
    }
}
