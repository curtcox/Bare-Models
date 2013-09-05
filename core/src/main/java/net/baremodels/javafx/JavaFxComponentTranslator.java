package net.baremodels.javafx;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;

public class JavaFxComponentTranslator {

    public Region translate(UIComponent ui) {
        if (ui instanceof UIContainer) {
            return container(ui);
        }
        Button button = new Button();
        button.setId(ui.getName());
        button.setText(ui.getName());
        return button;
    }

    private Region container(UIComponent ui) {
        UIContainer container = (UIContainer) ui;
        FlowPane pane = new FlowPane();
        pane.setId(ui.getName());
        for (UIComponent component : container) {
            pane.getChildren().add(translate(component));
        }
        return pane;
    }
}
