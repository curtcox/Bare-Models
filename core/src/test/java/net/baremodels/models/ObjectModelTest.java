package net.baremodels.models;

import net.baremodels.common.Team;
import net.baremodels.common.User;
import net.baremodels.intent.Intent;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectModelTest {

    Person person;
    Person fred = new Person();
    ModelFactory modelFactory = new ObjectModelFactory();
    ObjectModel model;

    @Before
    public void before() {
        person = new Person();
        fred.first_name = "Fred";
        person.friends = new ArrayList(Arrays.asList(new Person[] {fred}));
        model = new ObjectModel(person,modelFactory);
    }

    static class Vote extends Intent {}
    static class Send extends Intent<Person> {
        Send(Person person) {
           super(person);
        }
    }

    static class Person {

        String first_name;
        int age;
        double weight;
        List<Person> friends;
        boolean voted;

        Vote vote() { return new Vote(); }
        void haveBirthday() { age++; }

        // For Red Rover support
        Send send(String name) {
            for (Person friend : friends) {
                if (friend.first_name.equalsIgnoreCase(name)) {
                    return new Send(friend);
                }
            }
            return new Send(this);
        }
    }

    @Test
    public void model_exposes_properties() {
        assertEquals(5, model.properties().size());
    }

    @Test
    public void model_properties_are_returned_in_declaration_order() {
        assertEquals("first_name", props().get(0).name());
        assertEquals("age",        props().get(1).name());
        assertEquals("weight",     props().get(2).name());
        assertEquals("friends",    props().get(3).name());
        assertEquals("voted",      props().get(4).name());
    }

    private List<Property> props() {
        List<Property> list = new ArrayList<>();
        for (Property prop : model.properties().values()) {
            list.add(prop);
        }
        return list;
    }

    private List<Operation> ops() {
        List<Operation> list = new ArrayList<>();
        for (Operation op : model.operations().values()) {
            list.add(op);
        }
        return list;
    }

    @Test
    public void model_returns_String_property_by_name() {
        Property<String> first_name = model.properties.get("first_name");
        assertEquals("first_name", first_name.name());
    }

    @Test
    public void model_returns_int_property_by_name() {
        Property<String> age = model.properties.get("age");
        assertEquals("age", age.name());
    }

    @Test
    public void model_returns_double_property_by_name() {
        Property<String> age = model.properties.get("weight");
        assertEquals("weight", age.name());
    }

    @Test
    public void property_get_returns_null() {
        assertEquals(null, model.properties.get("field_that_does_not_exist"));
    }

    @Test
    public void property_get_set_string() {
        Property<String> first_name = model.properties.get("first_name");
        first_name.set("Goofy");
        assertEquals("Goofy", first_name.get());
    }

    @Test
    public void property_get_set_double() {
        Property<Double> weight = model.properties.get("weight");
        weight.set(98.6);
        assertEquals(98.6, weight.get(), 0.01);
    }

    @Test
    public void property_get_set_int() {
        Property<Integer> age = model.properties.get("age");
        age.set(101);
        assertEquals(101, age.get(), 0);
    }

    @Test
    public void property_get_set_list() {
        Property<List<Person>> prop = model.properties.get("friends");
        List<Person> friends = prop.get();
        assertTrue(prop.model() instanceof ObjectListModel);
        assertEquals("friends",prop.model().name());
        friends.add(new Person());
        assertEquals(2,friends.size());
        assertEquals(fred, friends.get(0));
    }

    @Test
    public void model_exposes_operations() {
        assertEquals(3, model.operations().size());
    }

    @Test
    public void model_returns_operations_in_alphabetic_order() {
        assertEquals("haveBirthday", ops().get(0).name());
        assertEquals("send",         ops().get(1).name());
        assertEquals("vote",         ops().get(2).name());
    }


    @Test
    public void model_returns_void_operation_by_name() {
        Operation haveBirthday = model.operations.get("haveBirthday");
        assertEquals("haveBirthday", haveBirthday.properties().get(Property.NAME));
    }

    @Test
    public void zero_argument_operation_takes_no_args() {
        Operation vote = model.operations.get("vote");
        assertSame(0,vote.arguments().size());
    }

    @Test
    public void operation_can_return_zero_argument_intent() {
        Operation voteOperation = model.operations.get("vote");
        Vote result = (Vote) voteOperation.invoke();
        assertTrue(result instanceof Vote);
    }

    @Test
    public void one_argument_operation_takes_one_arg() {
        Operation send = model.operations.get("send");
        assertSame(1,send.arguments().size());
    }

    @Test
    public void method_that_takes_args_can_return_an_intent() {
        Operation send = model.operations.get("send");
        send.arguments().get(0).set("fred");
        Send sent = (Send) send.invoke();
        assertSame(fred,sent.target);
    }

    @Test
    public void method_can_change_model_state() {
        int previousAge = person.age;
        Operation haveBirthday = model.operations.get("haveBirthday");
        haveBirthday.invoke();
        assertEquals(previousAge + 1, person.age);
    }

    private ObjectModel newObjectModel(Object object) {
        return new ObjectModel(object,modelFactory);
    }

    @Test
    public void toString_includes_toString_of_object() {
        String unlikely = "ghuaheufgug fughr2u3R833RNJFHUJAHUGRYUgws";
        assertTrue(newObjectModel(unlikely).toString().contains(unlikely));
    }

    @Test
    public void name_returns_friendly_class_name() {
        assertEquals("Person",newObjectModel(new Person()).name());
    }

    @Test
    public void meta_contains_name() {
        assertTrue(newObjectModel(new Person()).meta().containsKey(Property.NAME));
    }

    @Test
    public void name_returns_simple_class_name_when_there_is_no_name_field() {
        Person person = new Person();
        assertEquals("Person",newObjectModel(person).name());
    }

    @Test
    public void name_returns_value_from_name_field_when_it_exists() {
        Team team = new Team();
        team.name = "home";
        assertEquals("home",newObjectModel(team).name());
    }

    @Test
    public void name_returns_value_from_name_method_when_it_exists() {
        User user = new User();
        user.firstName = "Tom";
        user.lastName = "Baker";
        assertEquals("Tom Baker",newObjectModel(user).name());
    }

    @Test
    public void models_are_equal_if_their_objects_are_the_same() {
        assertAreEqual("this");
        assertAreEqual(7);
    }

    @Test
    public void models_are_not_equal_if_their_objects_are_different() {
        assertAreNotEquals("this","that");
    }

    private void assertAreEqual(Object a) {
        assertEquals(newObjectModel(a),newObjectModel(a));
        assertEquals(newObjectModel(a),newObjectModel(a));
        assertEquals(newObjectModel(a).hashCode(),newObjectModel(a).hashCode());
    }

    private void assertAreNotEquals(Object a, Object b) {
        assertFalse(newObjectModel(a).equals(newObjectModel(b)));
        assertFalse(newObjectModel(b).equals(newObjectModel(a)));
    }
}
