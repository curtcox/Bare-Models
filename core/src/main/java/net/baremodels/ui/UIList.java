package net.baremodels.ui;


import net.baremodels.model.ListModel;

public final class UIList implements UIComponent {

    private final ListModel listModel;
    private final String name;

    public UIList(ListModel listModel, String name) {
        this.listModel = listModel;
        this.name = name;
    }

    @Override
    public ListModel getModel() {
        return listModel;
    }

    @Override
    public String getName() {
        return name;
    }
}
