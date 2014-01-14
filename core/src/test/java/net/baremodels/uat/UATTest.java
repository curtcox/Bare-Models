package net.baremodels.uat;

import ionic.app.NucleusTestFactory;
import net.baremodels.apps.Nucleus;
import net.baremodels.device.DeviceState;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.*;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIGlyph;
import org.junit.Test;
import test.models.Car;
import test.models.Part;
import test.models.Passenger;
import test.models.StartIntent;

import java.util.*;

import static org.junit.Assert.*;

public class UATTest {

    final ModelFactory modelFactory = ModelFactory.DEFAULT;
    Map<Property.Matcher, UIGlyph> propertyGlyphs = new HashMap<>();
    AppContext appContext = new SimpleAppContext(propertyGlyphs);
    NextModelGenerator selectedGenerator = new SelectedNextModelGenerator();
    UAT testObject = new UAT(appContext, selectedGenerator);

    @Test
    public void can_create() {
        assertTrue(new UAT() instanceof UAT);
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
    public void selectIntent_throws_IllegalStateException_before_show() {
        try {
            testObject.execute("anything");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("This method is only valid after showing an object",e.getMessage());
        }
    }

    @Test
    public void show_initially_shows_object() {
        List list = Arrays.asList("William");

        testObject.show(list);

        assertEquals(modelFactory.of(list), testObject.state.showing);
    }

    @Test
    public void show_sets_selected_object() {
        String item = "Patrick";
        List list = Arrays.asList(item);

        testObject.show(list);
        testObject.select(item);

        assertEquals(modelFactory.of(item), testObject.state.selected);
    }

    @Test
    public void show_uses_given_runner_to_generate_next_showing_model() {
        Model expected = modelFactory.of("next");

        testObject = new UAT(appContext,(current,selected) -> expected);

        testObject.show("first");

        assertEquals(expected, testObject.state.next);
    }

    @Test
    public void select_shows_selected_object_when_it_is_on_screen() {
        String one = "one";
        List list = Arrays.asList(one);
        testObject.show(list);
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
        assertEquals("[unnamed[one]]", testObject.state.text);
        assertTrue(testObject.state.container instanceof UIContainer);
        assertEquals(1,testObject.state.selectable.length);
        assertEquals(one, testObject.state.selectable[0].toString());
    }

    @Test
    public void select_changes_state_for_object() {
        Car car = new Car();

        testObject.show(car);

        assertEquals(modelFactory.of(car),testObject.state.showing);
        testObject.assertScreenContains("Key" , "Parts", "Passengers");
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

    @Test
    public void screen_contains_car_icons_when_showing_car() {
        Car car = new Car();
        propertyGlyphs.put(property -> property.name().equals("key"), UIGlyph.key);
        propertyGlyphs.put(property -> property.name().equals("parts"), UIGlyph.puzzle_piece);
        propertyGlyphs.put(property -> property.name().equals("passengers"), UIGlyph.users);

        testObject.show(car);

        testObject.assertScreenContains(UIGlyph.key, UIGlyph.users, UIGlyph.puzzle_piece);
    }

    @Test
    public void assertScreenContains_fails_using_listener_when_value_is_not_on_screen() {
        UAT testObject = failureRecordingUAT();
        try {
            List list = new ArrayList();
            testObject.show(list);
            testObject.assertScreenContains("fantastic");
        } catch (AssertionError e) {
            assertTrue(failure.message, failure.message.startsWith("[fantastic] not found in"));
            assertSame(failure.state,testObject.state);
            return;
        }
        fail();
    }

    @Test
    public void select_fails_using_listener_when_object_is_not_on_screen() {
        UAT testObject = failureRecordingUAT();
        String object = this + "";
        try {
            List list = new ArrayList();
            testObject.show(list);
            testObject.select(object);
        } catch (AssertionError e) {
            assertSame(failure.state, testObject.state);
            assertEquals(failure.message,String.format("[%s] is not on screen [%s]",object,testObject.state));
            return;
        }
        fail();
    }

    @Test
    public void execute_fails_using_listener_when_object_is_not_on_screen() {
        UAT testObject = failureRecordingUAT();
        String object = this + "";
        try {
            List list = new ArrayList();
            testObject.show(list);
            testObject.execute(object);
        } catch (AssertionError e) {
            assertSame(failure.state,testObject.state);
            String expected = String.format("[%s] is not on screen [%s]", object, testObject.state);
            assertEquals(expected,failure.message);
            assertEquals(expected,e.getMessage());
            return;
        }
        fail();
    }

    @Test
    public void execute_fails_using_listener_when_object_is_not_an_operation() {
        UAT testObject = failureRecordingUAT();

        Car car = new Car();
        try {
            testObject.show(car);
            testObject.execute(car.passengers);
        } catch (AssertionError e) {
            assertSame(failure.state,testObject.state);
            String expected = String.format("[%s] does not have an operation to select [%s]", car.passengers, testObject.state);
            assertEquals(expected,failure.message);
            assertEquals(expected,e.getMessage());
            return;
        }
        fail();
    }

    FailedAssertion failure;
    private UAT failureRecordingUAT() {
        return new UATBuilder().withFailureListener((failure) -> {
            this.failure = failure;
        }).build();
    }

    @Test(timeout=100)
    public void assertScreenContains_relays_state_failure_to_given_runner_and_throws_failure() {
        try {
            UAT testObject = modelRecordingUAT();
            List list = new ArrayList();

            testObject.show(list);
            testObject.assertScreenContains("fantastic");
        } catch (AssertionError e) {
            assertNotNull(model);
            String message = model.properties().get("message").get().toString();
            assertTrue(message, message.startsWith("[fantastic] not found in"));
            return;
        }
        fail();
    }

    Model model;
    private UAT modelRecordingUAT() {
        SyncRunner runner = new SyncRunner() {
            @Override public Model generateNextModel(Model current, Model selected) { return selected; }
            @Override public Model display(Model current)     { UATTest.this.model = current; return current; }
            @Override public void onChange(DeviceState state) { }
            @Override public void onChange(AppContext context) {}
        };
        return new UATBuilder().withFailureRunner(runner).build();
    }

    @Test
    public void execute_returns_intent_when_selected() {
        Car car = new Car();

        testObject.show(car);

        Intent intent = testObject.execute(car.key);

        assertTrue(intent instanceof StartIntent);
        assertSame(car,intent.target);
    }
}
