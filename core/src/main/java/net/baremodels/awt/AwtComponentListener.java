package net.baremodels.awt;

import net.baremodels.ui.UIComponent;

final class AwtComponentListener implements UIComponent.Listener {
    public UIComponent selected;

    @Override
    public void onSelected(UIComponent component) {
        System.out.println("selected " + component);
        selected = component;
    }
}
