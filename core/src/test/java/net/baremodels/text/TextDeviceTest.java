package net.baremodels.text;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

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

    @Test
    public void display_supplies_user_with_expected_arguments() {
        MyFakeUser fakeUser = new MyFakeUser();
        TextDevice testObject = new TextDevice(fakeUser);

        Model model = ModelFactory.DEFAULT.of("stuff");
        UIComponent ui = new UIButton(model);
        UIComponent text = translate(ui);

        testObject.display(ui);

        assertSame(ui,user.state.ui);
        assertSame(text,user.state.ui);
        assertEquals(1,user.state.models.length);
        assertSame(model,user.state.models[0]);
    }

    private UIComponent translate(UIComponent ui) {
        return new SimpleComponentTranslator(new TextWidgetSupplier()).translate(ui,new SimpleComponentListener());
    }
}
