package net.baremodels.awt;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.model.Model;
import net.baremodels.models.ListModel;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIList;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AwtComponentTranslatorTest {

    Nucleus nucleus = new Nucleus();
    Model model = ObjectModel.of(nucleus);
    ListModel teams = (ListModel) model.properties().get("teams").model();
    Model users = model.properties().get("users").model();
    UIComponent.Listener listener = null;

    AwtComponentTranslator testObject = new AwtComponentTranslator();

    @Test
    public void button() {
        test(new UIButton(model,""),Button.class);
    }

    @Test
    public void container_with_two_buttons() {
        UIComponent one = new UIButton(teams,"one");
        UIComponent two = new UIButton(users,"two");
        UIComponent ui = new SimpleUIContainer(model,"c",one,two);

        Component actual = testObject.translate(ui,listener);

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
        java.awt.List awtList = (java.awt.List) testObject.translate(new UIList(teams,"a"),listener);
        assertEquals("a",awtList.getName());
    }

    @Test
    public void list_items() {
        Team team1 = new Team();
        Team team2 = new Team();
        nucleus.teams.add(team1);
        nucleus.teams.add(team2);

        java.awt.List awtList = (java.awt.List) testObject.translate(new UIList(teams,"a"),listener);

        assertEquals(2,awtList.getItemCount());
        assertEquals(team1.toString(),awtList.getItem(0));
        assertEquals(team2.toString(),awtList.getItem(1));
    }

    @Test
    public void button_text() {
        Button button = (Button) testObject.translate(new UIButton(teams,"a"),listener);
        assertEquals("a",button.getLabel());
    }

    private void test(UIComponent ui, Class clazz) {
        assertTrue(clazz.isInstance(testObject.translate(ui,listener)));
    }
}
