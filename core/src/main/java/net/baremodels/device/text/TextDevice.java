package net.baremodels.device.text;

import net.baremodels.device.SyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.ContainerTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;
import net.baremodels.ui.UIList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A device for integration testing with a simulated user.
 */
public final class TextDevice
    implements SyncDevice
{
    final FakeUser user;
    final WaitingComponentListener listener = new WaitingComponentListener();
    final Intent.Handler handler;
    final ContainerTranslator translator;

    public TextDevice(FakeUser user, Intent.Handler handler) {
        this(user,new SimpleContainerTranslator(new TextWidgetSupplier(), new SimpleComponentConstraintSupplier(null,null)), handler);
    }

    private TextDevice(FakeUser user, ContainerTranslator translator, Intent.Handler handler) {
        this.user = user;
        this.translator = translator;
        this.handler = handler;
    }

    @Override
    public Model display(UIContainer container, UILayout layout) {
        return user.pickModelFrom(generateUiState(container,layout));
    }

    private TextUiState generateUiState(UIContainer ui, UILayout layout) {
        return new TextUiState(ui.getModel(),ui, translator.translate(ui,layout,listener),extractModelsFrom(ui));
    }

    private Model[] extractModelsFrom(UIContainer ui) {
        List<Model> models = new ArrayList<>();
        for (UIComponent component : ui) {
            if (component instanceof UIList) {
                models.addAll(Arrays.asList(extractModels((UIList) component)));
            } else if (component instanceof UIContainer) {
                models.addAll(Arrays.asList(extractModels((UIContainer) component)));
            } else {
                models.add(component.getModel());
            }
        }
        return models.toArray(new Model[0]);
    }

    private Model[] extractModels(UIContainer ui) {
        return ui.stream().map(x->x.getModel()).toArray(x -> new Model[x]);
    }

    private Model[] extractModels(UIList ui) {
        return ui.getModel().properties().values().stream().map(x->x.model()).toArray(x -> new Model[x]);
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
