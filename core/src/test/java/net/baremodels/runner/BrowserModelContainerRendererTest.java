package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.*;
import static test.mock.Mocks._;

public class BrowserModelContainerRendererTest {

    Object home = "where the hearts are";
    Browser browser = new Browser(home);
    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model browserModel = modelFactory.of(browser);
    ModelContainerRenderer containerRenderer;
    BrowserModelContainerRenderer testObject = new BrowserModelContainerRenderer(containerRenderer);

    @Before
    public void init() {
        Mocks.init(this);
    }

    @Test
    public void render_throws_IllegalArgumentException_if_not_given_browser() {
        Model model = ModelFactory.DEFAULT.of("other");

        try {
            testObject.render(model);
            fail();
        } catch (IllegalArgumentException e) {
            String message = String.format("The given Model must be for a Browser, but is not [%s]",model);
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void render_uses_containerRenderer_from_constructor_to_render_contents() {
        UIContainer contents = SimpleUIContainer.of(modelFactory.of(""));
        _(contents); containerRenderer.render(modelFactory.of(browser.object));

        UIContainer container = testObject.render(browserModel);

        assertContains(container,contents);
    }

    private void assertContains(UIContainer container, UIContainer contents) {
        for (UIComponent component : container) {
            if (component==contents) {
                return;
            }
        }
        fail(String.format("%s does not contain %s",container,contents));
    }
}
