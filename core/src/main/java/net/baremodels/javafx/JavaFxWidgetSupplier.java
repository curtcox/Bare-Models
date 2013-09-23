package net.baremodels.javafx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.util.Collection;

final class JavaFxWidgetSupplier
    implements WidgetSupplier
{

    @Override
    public Button button(UIButton ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setId(ui.getName());
        button.setText(ui.getName());
        return button;
    }

    @Override
    public FlowPane container(UIContainer ui, Collection components) {
        FlowPane pane = new FlowPane();
        pane.setId(ui.getName());
        for (Object component : components) {
            pane.getChildren().add((Node) component);
        }
        return pane;
    }

    @Override
    public Button list(UIList ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setId(ui.getName());
        button.setText(ui.getName());
        return button;
    }
}
