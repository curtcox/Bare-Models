package net.baremodels.device.awt;

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

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class AwtContainerTranslatorTest {

    final Nucleus nucleus = new Nucleus();
    final Model model = ModelFactory.DEFAULT.of(nucleus);
    final ListModel teams = (ListModel) model.properties().get("teams").model();
    final Model users = model.properties().get("users").model();
    final UILayout layout = new UILayout();
    final UIComponent.Listener listener = null;

    SimpleContainerTranslator testObject = new SimpleContainerTranslator(new AwtWidgetSupplier(),new SimpleComponentConstraintSupplier(new MigLayout(),new HashMap<>()));

    @Test
    public void button() {
        test(new UIButton(model,""),Button.class);
    }

    @Test
    public void container_with_two_buttons() {
        UIComponent one = new UIButton(teams,"one");
        UIComponent two = new UIButton(users,"two");
        UIContainer ui = SimpleUIContainer.of(model,"c",one,two);

        Component actual = testObject.translate(ui,layout,listener);

        assertTrue(actual instanceof Container);
        Container container = (Container) actual;
        assertEquals(2,container.getComponentCount());
        assertEquals("c",actual.getName());

        Button buttonOne = (Button) container.getComponent(0);
        assertEquals("one",buttonOne.getName());
        assertEquals("one",buttonOne.getLabel());

        Button buttonTwo = (Button) container.getComponent(1);
        assertEquals("two",buttonTwo.getName());
        assertEquals("two",buttonTwo.getLabel());
    }

    @Test
    public void list_name() {
        UIContainer container = SimpleUIContainer.of(model,new UIList(teams,"a"));
        java.awt.Panel awtPanel = testObject.translate(container, layout, listener);
        assertEquals("a", awtPanel.getComponent(0).getName());
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

        UIContainer container = SimpleUIContainer.of(model,new UIList(teams, "teams"));

        java.awt.Panel awtPanel = testObject.translate(container,layout,listener);

        java.awt.List awtList = (List) awtPanel.getComponent(0);
        assertEquals("teams", awtList.getName());
        assertEquals(2, awtList.getItemCount());
        assertEquals(team1.name,awtList.getItem(0));
        assertEquals(team2.name, awtList.getItem(1));
    }

    @Test
    public void button_text() {
        UIContainer container = SimpleUIContainer.of(model,new UIButton(teams,"a"));
        Panel panel = testObject.translate(container, layout, listener);
        Button button = (Button) panel.getComponent(0);
        assertEquals("a",button.getLabel());
    }

    private void test(UIComponent ui, Class clazz) {
        UIContainer container = SimpleUIContainer.of(model,ui);
        Panel translated = testObject.translate(container, layout, listener);
        assertTrue(clazz.isInstance(translated.getComponent(0)));
    }
}
