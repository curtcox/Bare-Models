package net.baremodels.ui;

import net.baremodels.model.Model;

public interface UIComponent {
    Model getModel();
    String getName();

    interface Listener {
        void onSelected(Model model);
    }
}
