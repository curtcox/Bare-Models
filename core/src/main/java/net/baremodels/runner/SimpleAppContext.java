package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.model.Property;
import net.baremodels.ui.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple implementation of AppContext.
 */
public final class SimpleAppContext
    implements AppContext
{
    private final Map<Property.Matcher, UIGlyph> propertyGlyphs;
    private final Map<UIComponent.Matcher, UILayout.Constraints> componentConstraints;

    public SimpleAppContext() {
        this(Collections.emptyMap());
    }

    public SimpleAppContext(Map<Property.Matcher, UIGlyph> propertyGlyphs) {
        this(propertyGlyphs, Collections.singletonMap(
            new UIComponent.Matcher(){
                @Override
                public boolean matches(UIComponent component) {
                    return true;
                }
            },
            new UILayout.Constraints("wrap"))
        );
    }

    SimpleAppContext(
        Map<Property.Matcher, UIGlyph> propertyGlyphs,
        Map<UIComponent.Matcher, UILayout.Constraints> componentConstraints)
    {
        this.propertyGlyphs = propertyGlyphs;
        this.componentConstraints = componentConstraints;
    }

    @Override
    public UILayout layout(UIContainer container, DeviceState deviceState) {
        Map<UIComponent,UILayout.Constraints> constraints = new HashMap<>();
        for (UIComponent component : container) {
            for (UIComponent.Matcher matcher : componentConstraints.keySet()) {
                if (matcher.matches(component)) {
                    constraints.put(component,componentConstraints.get(matcher));
                }
            }
            if (component instanceof UIContainer) {
                UIContainer innerContainer = (UIContainer) component;
                UILayout innerLayout = layout(innerContainer, deviceState);
                for (UIComponent innerComponent : innerContainer) {
                    constraints.put(innerComponent,innerLayout.getConstraints(innerComponent));
                }
            }
        }
        return new UILayout(constraints);
    }

    @Override
    public UIIcon getIcon(Property property) {
        for (Property.Matcher matcher : propertyGlyphs.keySet()) {
            if (matcher.matches(property)) {
                return new UIIcon(propertyGlyphs.get(matcher));
            }
        }

        for (UIGlyph glyph : UIGlyph.values()) {
            if (glyph.name().equalsIgnoreCase(property.name())) {
                return new UIIcon(glyph);
            }
        }

        return null;
    }
}
