package net.baremodels.runner;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SimpleModelRendererTest {

    Nucleus nucleus = new Nucleus();
    Model nucleusModel = ObjectModel.of(nucleus);
    List<Team> teams = nucleus.teams;
    Model teamsModel = nucleusModel.properties().get("teams").model();
    Model usersModel = nucleusModel.properties().get("users").model();
    Model badgesModel = nucleusModel.properties().get("badges").model();
    Model skillsModel = nucleusModel.properties().get("skills").model();

    SimpleModelRenderer testObject = new SimpleModelRenderer();

    @Before
    public void setUp() {
        Team team = new Team();
        teams.add(team);
    }

    @Test
    public void Nucleus_renders_into_5_components() {
        UIComponent actual = testObject.render(nucleusModel);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;

        assertEquals(5, container.size());
    }

    @Test
    public void Nucleus_renders_into_proper_ui() {
        UIContainer actual = (UIContainer) testObject.render(nucleusModel);

        assertEquals(new UILabel("Nucleus"),             actual.get(0));
        assertEquals(new UIButton(teamsModel,"teams"),   actual.get(1));
        assertEquals(new UIButton(usersModel,"users"),   actual.get(2));
        assertEquals(new UIButton(badgesModel,"badges"), actual.get(3));
        assertEquals(new UIButton(skillsModel,"skills"), actual.get(4));
    }

    @Test
    public void Nucleus_teams() {
        UIComponent actual = testObject.render(nucleusModel);
        UIContainer container = (UIContainer) actual;

        UIComponent expectedButton = new UIButton(teamsModel,"teams");
        UIComponent actualButton = container.get(1);
        assertEquals(expectedButton, actualButton);
        ListModel fromButton = (ListModel) container.get(1).getModel();
        assertSame(teams, fromButton.getList());
    }

    @Test
    public void list_renders_to_UIList() {
        UIComponent actual = testObject.render(teamsModel);
        UIList container = (UIList) actual;
        assertSame(teamsModel,container.getModel());
    }
}

