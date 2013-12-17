package net.baremodels.device.swing;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.ui.*;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import javax.swing.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SwingWidgetSupplierTest {

    String name = this + "name";
    ComponentConstraintSupplier constraintSupplier = new SimpleComponentConstraintSupplier(new MigLayout(),new HashMap<>());
    Model model = ModelFactory.DEFAULT.of(new Nucleus());
    net.baremodels.model.ListModel teams = (net.baremodels.model.ListModel) model.properties().get("teams").model();
    UIComponent.Listener listener = null;

    SwingWidgetSupplier testObject = new SwingWidgetSupplier();

    @Test
    public void button_text() {
        JButton button = testObject.button(new UIButton(teams,"a"),listener);
        assertEquals("a",button.getText());
    }

    @Test
    public void label_name() {
        JLabel label = testObject.label(new UILabel(name));
        assertEquals(name,label.getName());
    }

    @Test
    public void label_text() {
        JLabel label = testObject.label(new UILabel(name));

        String expected = String.format("<html><div WIDTH=%d>%s</div><html>", 800, name);

        assertEquals(expected,label.getText());
    }

    @Test
    public void container_returns_JPanel_with_MigLayout_and_constraints_set() {
        UIContainer container = SimpleUIContainer.of(model);
        UILayout layout = new UILayout();
        List components = Collections.emptyList();

        JComponent actual = testObject.container(container, layout, components, constraintSupplier);

        assertTrue(actual instanceof JPanel);
        assertTrue(actual.getLayout() instanceof MigLayout);
    }

    @Test
    public void list_returns_JList() {
        UIList ui = new UIList(teams,name);
        JComponent list = testObject.list(ui, null);
        assertTrue(list instanceof JList);
        assertSame(name, list.getName());
    }
}
