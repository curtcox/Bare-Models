package net.baremodels.device.swing;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.ui.*;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SwingContainerTranslatorTest {

    final Nucleus nucleus = new Nucleus();
    final Model model = ModelFactory.DEFAULT.of(nucleus);
    final ListModel teams = (ListModel) model.properties().get("teams").model();
    final Model users = model.properties().get("users").model();
    final Map<UIComponent, UILayout.Constraints> componentConstraints = new HashMap<>();
    final UILayout layout = new UILayout(componentConstraints);
    final UIComponent.Listener listener = null;

    SimpleContainerTranslator testObject = new SimpleContainerTranslator(new SwingWidgetSupplier(),new SimpleComponentConstraintSupplier(new MigLayout()));

    @Test
    public void button() {
        UIButton uiButton = new UIButton(model,"");
        UIContainer container = SimpleUIContainer.of(model,uiButton);
        componentConstraints.put(uiButton,new UILayout.Constraints(""));

        JPanel translated = testObject.translate(container, layout, listener);

        assertTrue(translated.getComponent(0) instanceof JButton);
    }

    @Test
    public void container_with_two_buttons() {
        UIComponent one = new UIButton(teams,"one");
        UIComponent two = new UIButton(users,"two");
        UIContainer ui = SimpleUIContainer.of(model,"c",one,two);
        componentConstraints.put(one,new UILayout.Constraints(""));
        componentConstraints.put(two,new UILayout.Constraints(""));

        Component actual = testObject.translate(ui,layout,listener);

        assertTrue(actual instanceof Container);
        Container container = (Container) actual;
        assertEquals(2,container.getComponentCount());
        assertEquals("c",actual.getName());

        JButton buttonOne = (JButton) container.getComponent(0);
        assertEquals("one",buttonOne.getName());
        assertEquals("one",buttonOne.getLabel());

        JButton buttonTwo = (JButton) container.getComponent(1);
        assertEquals("two",buttonTwo.getName());
        assertEquals("two",buttonTwo.getLabel());
    }

    @Test
    public void list_name() {
        UIList uiList = new UIList(teams, "a");
        UIContainer container = SimpleUIContainer.of(model, uiList);
        componentConstraints.put(uiList,new UILayout.Constraints(""));

        JPanel panel = testObject.translate(container, layout, listener);

        assertEquals("a", panel.getComponent(0).getName());
    }

    @Test
    public void list_items_changes_with_object_list() {
        Nucleus nucleus = new Nucleus();
        Model model = ModelFactory.DEFAULT.of(nucleus);
        ListModel teams = (ListModel) model.properties().get("teams").model();
        assertFalse(teams.properties().values().iterator().hasNext());

        nucleus.teams.add(new Team());
        assertEquals(1,nucleus.teams.size());
        teams = (ListModel) model.properties().get("teams").model();
        assertTrue(teams.properties().values().iterator().hasNext());
    }

    @Test
    public void list_items() {
        Team team1 = new Team();
        team1.name = "team 1";
        Team team2 = new Team();
        team2.name = "team 2";
        nucleus.teams.add(team1);
        nucleus.teams.add(team2);

        UIList uiList = new UIList(teams, "teams");
        UIContainer container = SimpleUIContainer.of(model, uiList);
        componentConstraints.put(uiList,new UILayout.Constraints(""));

        JPanel jPanel = testObject.translate(container,layout,listener);

        JList jList = (JList) jPanel.getComponent(0);
        assertEquals("teams", jList.getName());
        assertEquals(2, jList.getModel().getSize());
        assertEquals(team1.name, jList.getModel().getElementAt(0));
        assertEquals(team2.name, jList.getModel().getElementAt(1));
    }

    @Test
    public void button_text() {
        UIButton uiButton = new UIButton(teams, "a");
        UIContainer container = SimpleUIContainer.of(model, uiButton);
        componentConstraints.put(uiButton,new UILayout.Constraints(""));

        JPanel panel = testObject.translate(container, layout, listener);

        JButton button = (JButton) panel.getComponent(0);
        assertEquals("a",button.getLabel());
    }

}
