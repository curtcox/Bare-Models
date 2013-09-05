package net.baremodels.javafx;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JavaFxComponentTranslatorTest {

    JavaFxComponentTranslator testObject = new JavaFxComponentTranslator();

    @Before
    public void setUp() {
        com.sun.javafx.application.PlatformImpl.startup(() -> System.out.println("started"));
    }
    @Test
    public void button() {
        test(new UIButton(""),Button.class);
    }

    @Test
    public void container_with_two_buttons() {
        UIComponent one = new UIButton("one");
        UIComponent two = new UIButton("two");
        UIComponent ui = new SimpleUIContainer("c",one,two);

        Region actual = testObject.translate(ui);

        Pane pane = (Pane) actual;
        assertEquals(2,pane.getChildren().size());
        assertEquals("c",actual.getId());

        Button buttonOne = (Button) pane.getChildren().get(0);
        assertEquals("one",buttonOne.getId());
        assertEquals("one",buttonOne.getText());

        Button buttonTwo = (Button) pane.getChildren().get(1);
        assertEquals("two",buttonTwo.getId());
        assertEquals("two",buttonTwo.getText());
    }

    @Test
    public void button_text() {
        Button button = (Button) testObject.translate(new UIButton("a"));
        assertEquals("a",button.getText());
    }

    private void test(UIComponent ui, Class clazz) {
        assertTrue(clazz.isInstance(testObject.translate(ui)));
    }

}
