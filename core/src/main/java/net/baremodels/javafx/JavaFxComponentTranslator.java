package net.baremodels.javafx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;

import java.util.Collection;

final class JavaFxComponentTranslator
    implements WidgetSupplier
{

    @Override
    public Button button(UIComponent ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setId(ui.getName());
        button.setText(ui.getName());
        return button;
    }

    @Override
    public FlowPane container(UIComponent ui, Collection components) {
        UIContainer container = (UIContainer) ui;
        FlowPane pane = new FlowPane();
        pane.setId(ui.getName());
        for (Object component : container) {
            pane.getChildren().add((Node) component);
        }
        return pane;
    }

    @Override
    public Button list(UIComponent ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setId(ui.getName());
        button.setText(ui.getName());
        return button;
    }
}
