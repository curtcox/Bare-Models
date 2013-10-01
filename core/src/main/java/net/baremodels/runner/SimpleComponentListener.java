package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

public final class SimpleComponentListener
    implements UIComponent.Listener
{

    public Model selected;

    @Override
    public void onSelected(Model model) {
        System.out.println("selected " + model);
        selected = model;
    }
}
