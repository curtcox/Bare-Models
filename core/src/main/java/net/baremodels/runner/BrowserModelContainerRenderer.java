package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.*;

/**
 * For rendering BrowserS as UIContainerS.
 */
public final class BrowserModelContainerRenderer
    implements ModelContainerRenderer
{
    private final ModelContainerRenderer containerRenderer;

    public BrowserModelContainerRenderer(ModelContainerRenderer containerRenderer) {
        this.containerRenderer = containerRenderer;
    }

    @Override
    public UIContainer render(Model model) {
        validateModel(model);
        Model objectModel = model.properties().get("object").model();
        return SimpleUIContainer.of(model,
            new UIButton(model.operation("goHome"),"Home", new UIIcon(UIGlyph.home)),
            containerRenderer.render(objectModel));
    }

    private void validateModel(Model model) {
        if (!isBrowser(model)) {
            String message = String.format("The given Model must be for a Browser, but is not [%s]",model);
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isBrowser(Model model) {
        return model.properties().keySet().contains("object");
    }
}
