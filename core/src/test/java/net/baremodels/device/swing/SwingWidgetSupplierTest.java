package net.baremodels.device.swing;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.ui.*;
import net.baremodels.ui.UILayout.Constraints;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static test.mock.Mocks._;
import static test.mock.Mocks.verify;

public class SwingWidgetSupplierTest {

    String name = this + "name";
    String constraints = this + "constraints";
    LayoutManager2 layoutManager;
    ComponentConstraintSupplier constraintSupplier;
    Model model = ModelFactory.DEFAULT.of(new Nucleus());
    net.baremodels.model.ListModel teams = (net.baremodels.model.ListModel) model.properties().get("teams").model();
    UIButton uiButton = new UIButton(model,"");
    UIComponent.Listener listener;
    Constraints uiConstraints = new Constraints(constraints);
    UILayout uiLayout = new UILayout(Collections.singletonMap(uiButton,uiConstraints));
    UIContainer uiContainer = SimpleUIContainer.of(model,name,uiButton);

    SwingWidgetSupplier testObject = new SwingWidgetSupplier();

    @Before
    public void init() {
        Mocks.init(this);
        constraintSupplier = new SimpleComponentConstraintSupplier(layoutManager);
    }

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
    public void container_throws_IllegalArgumentException_if_container_size_bigger_than_components() {
        try {
            testObject.container(uiContainer, uiLayout, Collections.emptyList(), constraintSupplier);
            fail();
        } catch (IllegalArgumentException e) {
            String message = "Container size must match component size, but 1!=0.";
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void container_throws_IllegalArgumentException_if_component_size_bigger_than_container() {
        try {
            testObject.container(SimpleUIContainer.of(model), uiLayout, Collections.singletonList(""), constraintSupplier);
            fail();
        } catch (IllegalArgumentException e) {
            String message = "Container size must match component size, but 0!=1.";
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void container_returns_JPanel_with_layoutManager_set() {
        List components = Collections.emptyList();
        UIContainer uiContainer = SimpleUIContainer.of(model);

        JPanel actual = testObject.container(uiContainer, uiLayout, components, constraintSupplier);

        assertSame(layoutManager, actual.getLayout());
    }

    @Test
    public void container_returns_JPanel_with_name_set() {
        UIContainer uiContainer = SimpleUIContainer.of(model,name);

        JPanel actual = testObject.container(uiContainer, uiLayout, Collections.emptyList(), constraintSupplier);

        assertSame(name, actual.getName());
    }

    @Test
    public void container_adds_components_to_the_container_returned() {
        JButton button = testObject.button(uiButton, listener);
        _(); layoutManager.addLayoutComponent(button, constraints);

        JPanel actual = testObject.container(uiContainer, uiLayout, Collections.singletonList(button), constraintSupplier);

        assertEquals(1,actual.getComponents().length);
        assertSame(button, actual.getComponent(0));
    }

    @Test
    public void container_adds_component_constraints_to_the_container_returned() {
        JButton button = testObject.button(uiButton, listener);
        _(); layoutManager.addLayoutComponent(button, constraints);

        testObject.container(uiContainer, uiLayout, Collections.singletonList(button), constraintSupplier);

        verify();

        layoutManager.addLayoutComponent(button, constraints);
    }

    @Test
    public void list_returns_JList() {
        UIList ui = new UIList(teams,name);
        JComponent list = testObject.list(ui, null);
        assertTrue(list instanceof JList);
        assertSame(name, list.getName());
    }
}
