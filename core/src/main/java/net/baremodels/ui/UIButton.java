package net.baremodels.ui;

public class UIButton implements UIComponent {

    private final String name;

    public UIButton(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
