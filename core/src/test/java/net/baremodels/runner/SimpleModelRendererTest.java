package net.baremodels.runner;

import net.baremodels.apps.Nucleus;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleModelRendererTest {

    SimpleModelRenderer testObject = new SimpleModelRenderer();

    @Test
    public void Nucleus() {
        Nucleus nucleus = new Nucleus();
        UIComponent actual = testObject.render(ObjectModel.of(nucleus));
        assertTrue(actual instanceof UIContainer);
        UIContainer columnContainer = (UIContainer) actual;
        assertEquals(4,columnContainer.size());
        UIComponent teamsButton = new UIButton("teams");
        assertEquals(teamsButton,columnContainer.get(0));
        UIComponent usersButton = new UIButton("users");
        assertEquals(usersButton,columnContainer.get(1));
        UIComponent badgesButton = new UIButton("badges");
        assertEquals(badgesButton,columnContainer.get(2));
        UIComponent skillsButton = new UIButton("skills");
        assertEquals(skillsButton,columnContainer.get(3));
    }
}

