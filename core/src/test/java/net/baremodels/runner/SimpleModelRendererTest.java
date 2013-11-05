package net.baremodels.runner;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.common.User;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SimpleModelRendererTest {

    Nucleus nucleus = new Nucleus();
    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model nucleusModel = modelFactory.of(nucleus);
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
        Model teamModel = modelFactory.of(team);

        UIComponent actual = testObject.render(teamModel);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;

        assertEquals(2, container.size());
    }

    @Test
    public void team_renders_into_named_list_of_users() {
        Team team = new Team();
        team.name = "A";
        Model teamModel = modelFactory.of(team);
        ListModel listModel = (ListModel) modelFactory.of(team.users,"users");

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
    public void teams_list_renders_to_UIList_with_proper_model() {
        UIComponent actual = testObject.render(teamsModel);
        UIList uiList = (UIList) actual;
        assertSame(teamsModel, uiList.getModel());
    }

    @Test
    public void String_renders_to_UILabel_with_proper_text() {
        String text = "faeughaeuguaefr";
        UIComponent actual = testObject.render(modelFactory.of(text));
        UILabel label = (UILabel) actual;
        assertSame(text, label.getName());
    }

    public static class Part {}
    public static class Passenger {
        public String name;
        Passenger(String name) {
            this.name = name;
        }
    }

    public static class Car {
        public List<Part> parts = Arrays.asList(new Part());
        public List<Passenger> passengers = Arrays.asList(new Passenger("Moe"), new Passenger("Larry"), new Passenger("Curly"));
    }

    @Test
    public void render_car_size() {
        Car car = new Car();

        Model model = modelFactory.of(car);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals("container=" + container,3,container.size());
    }

    @Test
    public void render_user_size() {
        User user = new User();

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals("container=" + container,17,container.size());
    }

    @Test
    public void render_car_label() {
        Car car = new Car();

        Model model = modelFactory.of(car);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals(new UILabel("Car"),container.get(0));
    }

    @Test
    public void render_user_label() {
        User user = new User();
        user.firstName = "John";
        user.lastName = "Smith";

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals(new UILabel("John Smith"),container.get(0));
    }

    @Test
    public void render_car_parts() {
        Car car = new Car();

        Model model = modelFactory.of(car);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals(modelFactory.of(car.parts),container.get(1).getModel());
    }

    @Test
    public void render_car_passengers() {
        Car car = new Car();

        Model model = modelFactory.of(car);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals(modelFactory.of(car.passengers),container.get(2).getModel());
    }

    @Test
    public void render_user_teams() {
        User user = new User();
        user.teams = Arrays.asList();

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals(modelFactory.of(user.teams),container.get(14).getModel());
    }

    @Test
    public void render_user_skills() {
        User user = new User();
        user.skills = Arrays.asList();

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals(modelFactory.of(user.skills),container.get(15).getModel());
    }

    @Test
    public void render_user_firstName() {
        User user = new User();
        user.firstName = "Tom";

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        UIComponent label = container.get(2);
        assertTrue(label instanceof UILabel);
        assertEquals("firstName: Tom", label.getName());
    }

    @Test
    public void render_user_lastName() {
        User user = new User();
        user.lastName = "Baker";

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        UIComponent label = container.get(3);
        assertTrue(label instanceof UILabel);
        assertEquals("lastName: Baker",label.getName());
    }

}

