package net.baremodels.javafx;

import javafx.scene.control.Button;
import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaFxWidgetSupplierTest {

    Model model = ObjectModel.of(new Nucleus());
    Model teams = model.properties().get("teams").model();
    UIComponent.Listener listener = null;

    JavaFxWidgetSupplier testObject = new JavaFxWidgetSupplier();

    @Before
    public void setUp() {
        com.sun.javafx.application.PlatformImpl.startup(() -> System.out.println("started"));
    }

    @Test
    public void button_text() {
        Button button = testObject.button(new UIButton(teams,"a"),listener);
        assertEquals("a",button.getText());
    }

}
