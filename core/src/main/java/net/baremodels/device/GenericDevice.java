package net.baremodels.device;

import net.baremodels.intent.Intent;
import net.baremodels.ui.UIComponent;

public interface GenericDevice extends Intent.Listener {
    void display(UIComponent ui);
}
