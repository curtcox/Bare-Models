package net.baremodels.awt;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

final class AwtComponentListener implements UIComponent.Listener {
    public Model selected;

    @Override
    public void onSelected(Model model) {
        System.out.println("selected " + model);
        selected = model;
    }
}
