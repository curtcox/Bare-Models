package net.baremodels.device.text;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILabel;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class TextUiStateTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model showing = modelFactory.of("this");
    UIContainer container = SimpleUIContainer.of(showing,new UILabel("foo"));
    String text = "whatever";
    Model[] selectable = new Model[] { modelFactory.of("that"), modelFactory.of("other")};
    TextUiState testObject = new TextUiState(showing,container,text,selectable);

    @Test
    public void fields_use_values_from_constructor() {
        assertSame(showing,testObject.showing);
        assertSame(container,testObject.container);
        assertSame(text,testObject.text);
        assertSame(selectable,testObject.selectable);
    }

    @Test
    public void selected_is_set_to_null_by_public_constructor() {
        assertTrue(testObject.selected == null);
    }

    @Test
    public void next_is_set_to_null_by_public_constructor() {
        assertTrue(testObject.next == null);
    }

    @Test
    public void toString_uses_values_from_constructor() {
        TextUiState testObject = new TextUiState(showing,container,text,selectable);

        String expected = String.format("showing=%s container=%s text=%s selectable=%s",showing,container,text, Arrays.asList(selectable));
        assertEquals(expected, testObject.toString());
    }

    @Test
    public void withNext_returns_new_state_that_is_the_same_except_for_next() {
        Model next = modelFactory.of("that");

        TextUiState testObject = new TextUiState(showing,container,text,selectable).withNext(next);

        assertSame(next,testObject.next);
        assertSame(container,testObject.container);
        assertSame(text,testObject.text);
        assertSame(selectable, testObject.selectable);
    }

    @Test
    public void withSelected_returns_new_state_that_is_the_same_except_for_selected() {
        Model selected = modelFactory.of("that");

        TextUiState testObject = new TextUiState(showing,container,text,selectable).withSelected(selected);

        assertSame(selected,testObject.selected);
        assertSame(container,testObject.container);
        assertSame(text,testObject.text);
        assertSame(selectable, testObject.selectable);
    }

}
