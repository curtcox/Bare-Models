package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.*;
import org.junit.Test;
import test.models.Car;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TextDeviceTest {

    static class MyFakeUser implements FakeUser {
        TextUiState state;

        @Override
        public Model pickModelFrom(TextUiState state) {
            this.state = state;
            return null;
        }
    }

    MyFakeUser user = new MyFakeUser();
    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Intent intent;
    SimpleComponentTranslator translator = new SimpleComponentTranslator(new TextWidgetSupplier(), new SimpleComponentConstraintSupplier(null,new HashMap<>()));

    TextDevice testObject = new TextDevice(user,i -> intent = i);

    @Test
    public void display_supplies_user_with_expected_arguments_for_string() {

        Model model = modelFactory.of("stuff");
        UIComponent ui = new UIButton(model);
        UIContainer container = SimpleUIContainer.of(model,ui);
        String text = translate(container);

        testObject.display(container);

        TextUiState state = user.state;
        assertSame(model,     state.showing);
        assertSame(container, state.ui);
        assertEquals(text,    state.text);
        assertEquals(1,       state.selectable.length);
        assertSame(model,     state.selectable[0]);
    }

    @Test
    public void display_supplies_user_with_expected_arguments_for_car() {
        Car car = new Car();

        Model model = modelFactory.of(car);
        UIContainer ui = SimpleUIContainer.of(model,"Car",
            new UIButton(modelFactory.of(car.parts)),
            new UIButton(modelFactory.of(car.passengers))
        );

        String text = translate(ui);

        testObject.display(ui);

        TextUiState state = user.state;
        assertSame(model,     state.showing);
        assertSame(ui,        state.ui);
        assertEquals(text,    state.text);
        assertEquals("Actual=" + Arrays.asList(state.selectable), 2, state.selectable.length);
        assertSame(modelFactory.of(car.parts),        state.selectable[0]);
        assertSame(modelFactory.of(car.passengers),   state.selectable[1]);
    }

    @Test
    public void display_list_supplies_user_with_expected_list_element_models() {
        List list = Arrays.asList("Tinker","Evars","Chance");
        ListModel model = (ListModel) modelFactory.of(list);
        UIComponent ui = new UIList(model,"name");
        UIContainer container = SimpleUIContainer.of(model,ui);

        testObject.display(container);

        assertSame(container, user.state.ui);
        assertEquals(3,user.state.selectable.length);
        assertSame(modelFactory.of("Tinker"),user.state.selectable[0]);
        assertSame(modelFactory.of("Evars"), user.state.selectable[1]);
        assertSame(modelFactory.of("Chance"),user.state.selectable[2]);
    }

    private String translate(UIContainer ui) {
        return translator.translate(ui,new WaitingComponentListener());
    }

    @Test
    public void onIntent_relays_intent_to_constructor_listener() {
        Intent expected = new Intent(null) {};

        testObject.onIntent(expected);

        assertSame(expected,intent);
    }
}
