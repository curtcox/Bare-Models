package net.baremodels.device.javafx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import java.util.List;

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
    public FlowPane container(UIContainer ui, UILayout layout, List components, ComponentConstraintSupplier componentConstraintSupplier) {
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

    @Override
    public Label label(UILabel ui) {
        Label label = new Label(ui.getName());
        label.setId(ui.getName());
        return label;
    }
}
