package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.model.Property;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIIcon;
import net.baremodels.ui.UILayout;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple implementation of AppContext.
 */
public final class SimpleAppContext
    implements AppContext
{
    private final Map<UIComponent.Matcher, UILayout.Constraints> componentConstraints;

    public SimpleAppContext() {
        this(Collections.singletonMap(
            new UIComponent.Matcher(){
                @Override
                public boolean matches(UIComponent component) {
                    return true;
                }
            },
            new UILayout.Constraints("wrap"))
        );
    }

    private SimpleAppContext(Map<UIComponent.Matcher, UILayout.Constraints> componentConstraints) {
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
        }
        return new UILayout(constraints);
    }

    @Override
    public UIIcon getIcon(Property property) {
        return null;
    }
}
