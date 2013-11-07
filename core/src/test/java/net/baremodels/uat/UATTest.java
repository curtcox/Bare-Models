package net.baremodels.uat;

import ionic.app.NucleusTestFactory;
import net.baremodels.apps.Nucleus;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.UIList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UATTest {

    final ModelFactory modelFactory = ModelFactory.DEFAULT;
    UAT testObject = UAT.of();

    @Test
    public void can_create() {
        assertTrue(UAT.of() instanceof UAT);
    }

    @Test
    public void screenContains_throws_IllegalStateException_before_show() {
        try {
            testObject.screenContains("anything");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("This method is only valid after showing an object",e.getMessage());
        }
    }

    @Test
    public void assertScreenContains_throws_IllegalStateException_before_show() {
        try {
            testObject.assertScreenContains("anything");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("This method is only valid after showing an object",e.getMessage());
        }
    }

    @Test
    public void select_throws_IllegalStateException_before_show() {
        try {
            testObject.select("anything");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("This method is only valid after showing an object",e.getMessage());
        }
    }

    @Test
    public void select_shows_when_object_is_on_screen() {
        String one = "one";
        List list = Arrays.asList(one);
        testObject.show(list);
        assertEquals(modelFactory.of(list), testObject.state.showing);
        testObject.select(one);
        assertEquals(modelFactory.of(one), testObject.state.showing);
    }

    @Test
    public void select_changes_currently_selected_object() {
        String one = "one";
        String two = "two";
        List nested = Arrays.asList(one, two);
        List outer = Arrays.asList(nested);
        testObject.show(outer);
        assertFalse(testObject.screenContains(one));
        testObject.select(nested);
        assertTrue(testObject.state.text, testObject.screenContains(one));
        testObject.assertScreenContains(one);
        testObject.assertScreenContains(two);
    }

    @Test
    public void select_changes_state_for_list() {
        String one = "one";
        testObject.show(Arrays.asList(one));
        assertEquals("unnamed[one]", testObject.state.text);
        assertTrue(testObject.state.ui instanceof UIList);
        assertEquals(1,testObject.state.selectable.length);
        assertEquals(one,testObject.state.selectable[0].toString());
    }

    @Test
    public void select_changes_state_for_object() {
        Car car = new Car();

        testObject.show(car);

        assertEquals(modelFactory.of(car),testObject.state.showing);
        testObject.assertScreenContains("Key" , "Parts", "Passengers");
    }

    @Test
    public void select_fails_when_object_is_not_on_screen() {
        String object = this + "";
        try {
            List list = new ArrayList();
            testObject.show(list);
            testObject.select(object);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(String.format("[%s] is not on screen [%s]",object,testObject.state),e.getMessage());
        }
    }

    @Test
    public void assertScreenContains_ok_when_value_is_on_screen_for_list_element() {
        List list = new ArrayList();
        list.add("fantastic");
        testObject.show(list);
        assertTrue(testObject.screenContains("fantastic"));
        testObject.assertScreenContains("fantastic");
    }

    @Test
    public void assertScreenContains_ok_when_value_is_on_screen_for_Nucleus() {
        Nucleus nucleus = NucleusTestFactory.newNucleus();
        testObject.show(nucleus);
        testObject.assertScreenContains("Nucleus");
    }

    @Test
    public void assertScreenContains_fails_when_value_is_not_on_screen() {
        try {
            List list = new ArrayList();
            testObject.show(list);
            testObject.assertScreenContains("fantastic");
            fail();
        } catch (AssertionError e) {
            String message = e.getMessage();
            assertTrue(message,message.startsWith("[fantastic] not found in"));
        }
    }

    @Test
    public void assertScreenContains_fails_when_missing_any_value_on_screen() {
        try {
            List list = Arrays.asList("fantastic");
            testObject.show(list);
            testObject.assertScreenContains("fantastic","four");
            fail();
        } catch (AssertionError e) {
            String message = e.getMessage();
            assertTrue(message,message.startsWith("[four] not found in"));
        }
    }

    @Test
    public void screenContains_is_true_when_all_given_strings_are_on_screen() {
        testObject.show(Arrays.asList("Tinker","Evars","Chance"));

        assertTrue(testObject.screenContains("Evars","Chance"));
        assertTrue(testObject.screenContains("Tinker","Chance"));
        assertTrue(testObject.screenContains("Tinker", "Evars", "Chance"));
    }

    @Test
    public void screenContains_is_false_when_any_given_string_is_not_on_screen() {
        testObject.show(Arrays.asList("Tinker","Evars","Chance"));

        assertFalse(testObject.screenContains("Evars", "Johnny"));
        assertFalse(testObject.screenContains("Tailor", "Soldier"));
        assertFalse(testObject.screenContains("Tinker", "Evars", "Brown"));
    }

    public static class Part {}
    public static class StartIntent extends Intent {
        Car car;
        StartIntent(Car car) {
            this.car = car;
        }
    }
    public static class Passenger {
        public String name;
        Passenger(String name) {
            this.name = name;
        }
    }

    public static class Key {
        private final Car car;

        Key(Car car) {
            this.car = car;
        }
        public StartIntent start() {
            return new StartIntent(car);
        }
    }

    public static class Car {
        public Key key = new Key(this);
        public List<Part> parts = Arrays.asList(new Part());
        public List<Passenger> passengers = Arrays.asList(new Passenger("Moe"), new Passenger("Larry"), new Passenger("Curly"));
    }

    @Test
    public void screen_contains_buttons_for_all_named_lists_When_there_is_more_than_one_list() {
        Car car = new Car(); // a new car!!!

        testObject.show(car);
        testObject.assertScreenContains("Parts", "Passengers");
    }

    @Test
    public void screen_contains_list_contents_after_selecting_list_of_anonymous_objects() {
        Car car = new Car();

        testObject.show(car);
        testObject.select(car.parts);
        for (Part part : car.parts) {
            testObject.assertScreenContains("parts[Part]");
        }
    }

    @Test
    public void screen_contains_list_contents_after_selecting_list_of_named_objects() {
        Car car = new Car();

        testObject.show(car);
        testObject.select(car.passengers);
        for (Passenger passenger : car.passengers) {
            testObject.assertScreenContains(passenger.name);
        }
    }

    FailedAssertion failure;
    @Test
    public void assertScreenContains_fails_using_listener_when_value_is_not_on_screen() {
        UAT testObject = UAT.withListener((failure) -> {
            this.failure = failure;
        });
        List list = new ArrayList();
        testObject.show(list);
        testObject.assertScreenContains("fantastic");
        assertTrue(failure.message, failure.message.startsWith("[fantastic] not found in"));
        assertSame(failure.state,testObject.state);
    }

    Model model;
    @Test
    public void assertScreenContains_relays_state_to_given_runner() {
        UAT testObject = UAT.withFailureRunner((model, until) -> {
            this.model = model;
        });
        List list = new ArrayList();
        testObject.show(list);
        testObject.assertScreenContains("fantastic");
        assertNotNull(model);
        String message = model.properties().get("message").get().toString();
        assertTrue(message, message.startsWith("[fantastic] not found in"));
    }

    @Test
    public void select_returns_intent_when_selected() {
        Car car = new Car();

        Arrays.sort(new String[0],String::compareToIgnoreCase);
        testObject.show(car);
        Intent intent = testObject.selectIntent(car.key);

        assertTrue(intent instanceof StartIntent);
        assertSame(car,intent.target);
    }
}
