package net.baremodels.device.text;

import net.baremodels.device.DeviceState;
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
public final class TextSyncDevice
    implements SyncDevice
{
    final FakeUser user;
    final WaitingComponentListener listener = new WaitingComponentListener();
    final Intent.Handler handler;
    final ContainerTranslator translator;

    public TextSyncDevice(FakeUser user, Intent.Handler handler) {
        this(user,new SimpleContainerTranslator(new TextWidgetSupplier(), new SimpleComponentConstraintSupplier(null)), handler);
    }

    private TextSyncDevice(FakeUser user, ContainerTranslator translator, Intent.Handler handler) {
        this.user = user;
        this.translator = translator;
        this.handler = handler;
    }

    @Override
    public Model display(UIContainer container, UILayout layout) {
        return user.pickModelFrom(generateUiState(container,layout));
    }

    @Override
    public void redisplay(UIContainer container, UILayout layout) {

    }

    @Override
    public DeviceState getDeviceState() {
        return null;
    }

    private TextUiState generateUiState(UIContainer ui, UILayout layout) {
        return new TextUiState(ui.getInspectable(),ui, translator.translate(ui,layout,listener),extractModelsFrom(ui));
    }

    private Model[] extractModelsFrom(UIContainer ui) {
        List<Model> models = new ArrayList<>();
        for (UIComponent component : ui) {
            if (component instanceof UIList) {
                models.addAll(Arrays.asList(extractModels((UIList) component)));
            } else if (component instanceof UIContainer) {
                models.addAll(Arrays.asList(extractModels((UIContainer) component)));
            } else if (component.getInspectable() instanceof Model) {
                models.add((Model) component.getInspectable());
            }
        }
        return models.toArray(new Model[0]);
    }

    private Model[] extractModels(UIContainer ui) {
        return ui.stream().map(x->x.getInspectable()).toArray(x -> new Model[x]);
    }

    private Model[] extractModels(UIList ui) {
        return ui.getInspectable().properties().values().stream().map(x->x.model()).toArray(x -> new Model[x]);
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
