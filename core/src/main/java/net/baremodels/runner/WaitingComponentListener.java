package net.baremodels.runner;

import net.baremodels.model.Inspectable;
import net.baremodels.ui.UIComponent;
import net.baremodels.util.TimeUtil;

/**
 * A component listener that remembers the last model selected.
 */
public final class WaitingComponentListener
    implements UIComponent.Listener
{
    private Inspectable selected;
    private boolean changed;

    @Override
    public void onSelected(Inspectable model) {
        selected = model;
        changed = true;
    }

    /**
     * Return the new selection, as soon as the selection changes.
     * Note that this is a blocking call.
     */
    public Inspectable waitForSelectionChange() {
        TimeUtil.waitUntil(() -> changed);
        changed = false;
        return selected;
    }
}
