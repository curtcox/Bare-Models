package net.baremodels.runner;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
    public void Nucleus() {
        UIComponent actual = testObject.render(nucleusModel);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;

        assertEquals(4, container.size());
        UIComponent teamsButton = new UIButton(teamsModel,"teams");
        assertEquals(teamsButton, container.get(0));
        UIComponent usersButton = new UIButton(usersModel,"users");
        assertEquals(usersButton,container.get(1));
        UIComponent badgesButton = new UIButton(badgesModel,"badges");
        assertEquals(badgesButton,container.get(2));
        UIComponent skillsButton = new UIButton(skillsModel,"skills");
        assertEquals(skillsButton, container.get(3));
    }

    @Test
    public void Nucleus_teams() {
        UIComponent actual = testObject.render(nucleusModel);
        UIContainer container = (UIContainer) actual;

        UIComponent expectedButton = new UIButton(teamsModel,"teams");
        UIComponent actualButton = container.get(0);
        assertEquals(expectedButton, actualButton);
        ObjectModel fromButton = (ObjectModel) container.get(0).getModel();
        assertSame(teams, fromButton.object);
    }

}

