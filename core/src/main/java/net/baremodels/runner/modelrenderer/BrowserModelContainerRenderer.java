package net.baremodels.runner.modelrenderer;

import net.baremodels.model.Model;
import net.baremodels.runner.ModelContainerRenderer;
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
        UIContainer containedModel = createContainedModel(model);
        return SimpleUIContainer.of(model,
            newHomeButton(model),newBackButton(model),newForwardButton(model),
            containedModel);
    }

    private UIContainer createContainedModel(Model model) {
        Model objectModel = model.properties().get("object").model();
        return containerRenderer.render(objectModel);
    }

    private UIButton newHomeButton(Model model) {
        return new UIButton(model.operation("home"),"Home", new UIIcon(UIGlyph.home));
    }

    private UIButton newForwardButton(Model model) {
        return new UIButton(model.operation("forward"),"Forward", new UIIcon(UIGlyph.arrow_right));
    }

    private UIButton newBackButton(Model model) {
        return new UIButton(model.operation("back"),"Back", new UIIcon(UIGlyph.arrow_left));
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
