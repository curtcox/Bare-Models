package net.baremodels.runner.modelrenderer;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.Browser;
import net.baremodels.runner.ModelContainerRenderer;
import net.baremodels.ui.*;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BrowserModelContainerRenderer_IntegrationTest {

    String home = "where the hearts are";
    Browser browser = new Browser(home);
    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model browserModel = modelFactory.of(browser);
    ModelContainerRenderer containerRenderer = new SimpleModelContainerRenderer();
    UIButton homeButton = new UIButton(browserModel.operation("home"),"Home", new UIIcon(UIGlyph.home));
    UIButton forwardButton = new UIButton(browserModel.operation("forward"),"Forward", new UIIcon(UIGlyph.arrow_right));
    UIButton backButton = new UIButton(browserModel.operation("back"),"Back", new UIIcon(UIGlyph.arrow_left));
    BrowserModelContainerRenderer testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new BrowserModelContainerRenderer(containerRenderer);
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
    public void render_produces_container_with_label_for_string_when_browser_content_is_just_a_string() {
        UIContainer container = testObject.render(browserModel);

        assertContains(container,new UILabel(home));
    }

    @Test
    public void render_renders_Home_as_button_for_operation() {
        UIContainer container = testObject.render(browserModel);

        assertContains(container,homeButton);
    }

    @Test
    public void render_renders_Forward_as_button_for_operation() {
        UIContainer container = testObject.render(browserModel);

        assertContains(container,forwardButton);
    }

    @Test
    public void render_renders_Back_as_button_for_operation() {
        UIContainer container = testObject.render(browserModel);

        assertContains(container,backButton);
    }

    private void assertContains(UIContainer container, UIComponent targetComponent) {
        if (!contains(container,targetComponent)) {
            fail(String.format("%s does not contain %s",container,targetComponent));
        }
    }

    private boolean contains(UIContainer container, UIComponent targetComponent) {
        for (UIComponent component : container) {
            if (component.equals(targetComponent)) {
                return true;
            }
            if (component instanceof UIContainer) {
                UIContainer inner = (UIContainer) component;
                if (contains(inner,targetComponent)) {
                    return true;
                }
            }
        }
        return false;
    }

}
