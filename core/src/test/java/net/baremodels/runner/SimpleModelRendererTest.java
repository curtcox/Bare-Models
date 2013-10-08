package net.baremodels.runner;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SimpleModelRendererTest {

    Nucleus nucleus = new Nucleus();
    Model nucleusModel = ObjectModel.of(nucleus);
    List<Team> teams = nucleus.teams;
    Map<?,Property> nucleusProperties = nucleusModel.properties();
    Model teamsModel = nucleusProperties.get("teams").model();
    Model usersModel = nucleusProperties.get("users").model();
    Model badgesModel = nucleusProperties.get("badges").model();
    Model skillsModel = nucleusProperties.get("skills").model();

    SimpleModelRenderer testObject = new SimpleModelRenderer();

    @Before
    public void setUp() {
        Team team = new Team();
        team.name = "team 1";
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
    public void team_renders_into_2_components() {
        Team team = new Team();
        Model teamModel = ObjectModel.of(team);

        UIComponent actual = testObject.render(teamModel);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;

        assertEquals(2, container.size());
    }

    @Test
    public void team_renders_into_named_list_of_users() {
        Team team = new Team();
        team.name = "A";
        Model teamModel = ObjectModel.of(team);
        ListModel listModel = (ListModel) ObjectModel.of(team.users);

        UIContainer actual = (UIContainer) testObject.render(teamModel);

        assertEquals(new UILabel("A"),              actual.get(0));
        assertEquals(new UIList(listModel,"users"), actual.get(1));
    }

    @Test
    public void Nucleus_teams_button() {
        UIComponent actual = testObject.render(nucleusModel);
        UIContainer container = (UIContainer) actual;

        UIComponent expectedButton = new UIButton(teamsModel,"teams");
        UIComponent actualButton = container.get(1);
        assertEquals(expectedButton, actualButton);
        ListModel fromButton = (ListModel) container.get(1).getModel();
        assertSame(teams, fromButton.getList());
    }

    @Test
    public void list_renders_to_UIList_with_proper_model() {
        UIComponent actual = testObject.render(teamsModel);
        UIList uiList = (UIList) actual;
        assertSame(teamsModel, uiList.getModel());
    }

}

