package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.*;
import net.baremodels.ui.UILayout.Constraints;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static test.mock.Mocks._;

public class SimpleAppContextTest {

    Model model = ModelFactory.DEFAULT.of("");
    Map<String, String> config = new HashMap<>();
    //UIComponent.Matcher componentMatcher;
    Property.Matcher propertyMatcher;
    UIGlyph glyph = UIGlyph.ambulance;
    Property matchingProperty;
    Property unmatchingProperty;
    Constraints constraints = new Constraints("wrap");
    UIComponent component = new UILabel("label text here");
    UIContainer container = SimpleUIContainer.of(model,component);
    DeviceState deviceState = new DeviceState(25,25);

    SimpleAppContext testObject;

    @Before
    public void init() {
        Mocks.init(this);
        _(true);       propertyMatcher.matches(matchingProperty);
        _(false);      propertyMatcher.matches(unmatchingProperty);
        //_(true);       componentMatcher.matches(component);
        _("not_this"); unmatchingProperty.name();

        testObject = new SimpleAppContext(Collections.singletonMap(propertyMatcher,glyph),config);
    }

    @Test
    public void is_an_AppContext() {
        assertTrue(testObject instanceof AppContext);
    }

    @Test
    public void layout_returns_a_UILayout() {
        assertTrue(testObject.layout(container,deviceState) instanceof UILayout);
    }

    @Test
    public void layout_returns_a_UILayout_with_constraints_for_component_in_container() {
        assertEquals(constraints, testObject.layout(container, deviceState).getConstraints(component));
    }

    @Test
    public void layout_returns_a_UILayout_with_constraints_for_component_in_nested_container() {
        UIContainer nestedContainer = SimpleUIContainer.of(model,component);
        UIContainer container = SimpleUIContainer.of(model,nestedContainer);
        //_(true); componentMatcher.matches(nestedContainer);

        UILayout actual = testObject.layout(container, deviceState);

        assertEquals(constraints, actual.getConstraints(component));
    }

    @Test
    public void layout_returns_a_UILayout_with_constraints_for_nested_container() {
        UIContainer nestedContainer = SimpleUIContainer.of(model,component);
        UIContainer container = SimpleUIContainer.of(model,nestedContainer);

        UILayout actual = testObject.layout(container, deviceState);

        assertEquals(constraints, actual.getConstraints(nestedContainer));
    }

    @Test
    public void getIcon_returns_icon_that_matches_specified_property_icon() {
        UIIcon expected = new UIIcon(UIGlyph.ambulance);

        UIIcon actual = testObject.getIcon(matchingProperty);

        assertEquals(expected, actual);
    }

    @Test
    public void getIcon_returns_null_when_property_does_not_match_any_icons() {
        UIIcon actual = testObject.getIcon(unmatchingProperty);

        assertEquals(null, actual);
    }

    @Test
    public void getIcon_returns_Home_icon_when_property_named_home() {
        testObject = new SimpleAppContext();
        _("Home"); matchingProperty.name();
        UIIcon actual = testObject.getIcon(matchingProperty);

        assertEquals(UIGlyph.home, actual.glyph);
    }

    @Test
    public void getIcon_returns_FaceBook_icon_when_property_named_FaceBook() {
        testObject = new SimpleAppContext();
        _("FaceBook"); matchingProperty.name();
        UIIcon actual = testObject.getIcon(matchingProperty);

        assertEquals(UIGlyph.facebook, actual.glyph);
    }

    @Test
    public void config_returns_value_from_constructor() {
        assertSame(config, testObject.config());
    }
}
