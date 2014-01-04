package net.baremodels.runner;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.PhoneNumber;
import net.baremodels.common.StreetAddress;
import net.baremodels.common.Team;
import net.baremodels.common.User;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.*;
import org.junit.Before;
import org.junit.Test;
import test.models.Car;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SimpleModelRendererTest {

    Nucleus nucleus = new Nucleus();
    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model nucleusModel = modelFactory.of(nucleus);
    NavigationContext context = new NavigationContext();
    List<Team> teams = nucleus.teams;
    Map<?,Property> nucleusProperties = nucleusModel.properties();
    Model teamsModel = nucleusProperties.get("teams").model();
    Model usersModel = nucleusProperties.get("users").model();
    Model badgesModel = nucleusProperties.get("badges").model();
    Model skillsModel = nucleusProperties.get("skills").model();
    PropertyIconMapper iconMapper = new PropertyIconMapper() {
        @Override
        public UIIcon getIcon(Property property) {
            String name = property.name();
            if (name.equals("users"))  { return new UIIcon(UIGlyph.user); }
            if (name.equals("teams"))  { return new UIIcon(UIGlyph.users); }
            if (name.equals("badges")) { return new UIIcon(UIGlyph.certificate); }
            if (name.equals("skills")) { return new UIIcon(UIGlyph.star); }
            return null;
        }
    };

    SimpleModelRenderer testObject = new SimpleModelRenderer(new SimplePropertyNameMapper(),iconMapper);

    @Before
    public void init() {
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
    public void Nucleus_renders_into_proper_ui() throws Exception {
        UIContainer actual = testObject.render(nucleusModel);

        assertEquals(new UILabel("Nucleus"),             actual.get(0));
        assertEquals(new UIButton(teamsModel, "Teams", new UIIcon(UIGlyph.users)),  actual.get(1));
        assertEquals(new UIButton(usersModel, "Users", new UIIcon(UIGlyph.user)),  actual.get(2));
        assertEquals(new UIButton(badgesModel,"Badges",new UIIcon(UIGlyph.certificate)), actual.get(3));
        assertEquals(new UIButton(skillsModel,"Skills",new UIIcon(UIGlyph.star)), actual.get(4));
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

        UIContainer actual = testObject.render(teamModel);

        assertEquals(new UILabel("A"),              actual.get(0));
        assertEquals(new UIList(listModel,"users"), actual.get(1));
    }

    @Test
    public void Nucleus_teams_button_renders_with_equivalent_button() throws Exception {
        UIIcon teamsIcon = new UIIcon(UIGlyph.users);
        UIComponent expectedButton = new UIButton(teamsModel,"Teams",teamsIcon);

        UIContainer actual = testObject.render(nucleusModel);

        UIComponent actualButton = actual.get(1);
        assertEquals(expectedButton, actualButton);
    }

    @Test
    public void Nucleus_teams_button_renders_button_with_right_list() {
        UIContainer actual = testObject.render(nucleusModel);

        ListModel fromButton = (ListModel) actual.get(1).getModel();
        assertSame(teams, fromButton.getList());
    }

    @Test
    public void teams_list_renders_to_UIList_with_proper_model() {
        UIContainer actual = testObject.render(teamsModel);
        UIList uiList = (UIList) actual.get(0);
        assertSame(teamsModel, uiList.getModel());
    }

    @Test
    public void String_renders_to_UILabel_with_proper_text() {
        String text = "faeughaeuguaefr";
        UIContainer actual = testObject.render(modelFactory.of(text));
        UILabel label = (UILabel) actual.get(0);
        assertSame(text, label.getName());
    }

    @Test
    public void render_car_size() {
        Car car = new Car();

        Model model = modelFactory.of(car);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals("translate=" + container,4,container.size());
    }

    @Test
    public void render_user_size() {
        User user = new User();

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals("translate=" + container,17,container.size());
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
        assertEquals(modelFactory.of(car.parts),container.get(2).getModel());
    }

    @Test
    public void render_car_passengers() {
        Car car = new Car();

        Model model = modelFactory.of(car);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        assertEquals(modelFactory.of(car.passengers),container.get(3).getModel());
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
        assertEquals("First Name: Tom", label.getName());
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
        assertEquals("Last Name: Baker",label.getName());
    }

    @Test
    public void render_user_cellPhone() {
        User user = new User();
        user.cellPhone = new PhoneNumber();
        user.cellPhone.value = "867-5309";

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        UIComponent label = container.get(5);
        assertTrue(label instanceof UIButton);
        assertEquals("Cell Phone: 867-5309",label.getName());
    }

    @Test
    public void render_user_homePhone() {
        User user = new User();
        user.homePhone = new PhoneNumber();
        user.homePhone.value = "867-5309";

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        UIComponent label = container.get(6);
        assertTrue(label instanceof UIButton);
        assertEquals("Home Phone: 867-5309",label.getName());
    }

    @Test
    public void render_user_streetAddress() {
        User user = new User();
        user.streetAddress = new StreetAddress();
        user.streetAddress.city = "Chicago";
        user.streetAddress.state = "IL";
        user.streetAddress.zip = "60613";
        user.streetAddress.line1 = "1060 W. Addison";

        Model model = modelFactory.of(user);

        UIComponent actual = testObject.render(model);

        assertTrue(actual instanceof UIContainer);
        UIContainer container = (UIContainer) actual;
        UIComponent label = container.get(8);
        assertTrue(label instanceof UIButton);
        assertEquals("Street Address: 1060 W. Addison, Chicago, IL 60613",label.getName());
    }

}

