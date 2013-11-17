package net.baremodels.device.text;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UILabel;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TextUiStateTest {

    @Test
    public void fields_use_values_from_constructor() {
        ModelFactory modelFactory = ModelFactory.DEFAULT;
        Model showing = modelFactory.of("this");
        UIComponent ui = new UILabel("foo");
        String text = "whatever";
        Model[] selectable = new Model[0];

        TextUiState testObject = new TextUiState(showing,ui,text,selectable);

        assertSame(showing,testObject.showing);
        assertSame(ui,testObject.ui);
        assertSame(text,testObject.text);
        assertSame(selectable,testObject.selectable);
    }

    @Test
    public void toString_uses_values_from_constructor() {
        ModelFactory modelFactory = ModelFactory.DEFAULT;
        Model showing = modelFactory.of("this");
        UIComponent ui = new UILabel("foo");
        String text = "whatever";
        Model[] selectable = new Model[] { modelFactory.of("that"), modelFactory.of("other")};

        TextUiState testObject = new TextUiState(showing,ui,text,selectable);

        String expected = String.format("showing=%s ui=%s text=%s selectable=%s",showing,ui,text, Arrays.asList(selectable));
        assertEquals(expected, testObject.toString());
    }

}
