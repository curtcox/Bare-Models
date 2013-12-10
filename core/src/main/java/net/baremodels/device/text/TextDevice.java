package net.baremodels.device.text;

import net.baremodels.device.SyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.awt.font.TextLayout;
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
    final SimpleComponentTranslator translator;

    public TextDevice(FakeUser user, Intent.Handler handler) {
        this(user,new SimpleComponentTranslator(new TextWidgetSupplier(), new TextLayoutSupplier()), handler);
    }

    private TextDevice(FakeUser user, SimpleComponentTranslator translator, Intent.Handler handler) {
        this.user = user;
        this.translator = translator;
        this.handler = handler;
    }

    @Override
    public Model display(UIComponent ui) {
        return user.pickModelFrom(generateUiState(ui));
    }

    private TextUiState generateUiState(UIComponent ui) {
        return new TextUiState(ui.getModel(),ui, translator.translate(ui, listener),extractModels(ui));
    }

    private Model[] extractModels(UIComponent ui) {
        List<Model> models = new ArrayList<>();
        if (ui instanceof UIContainer) {
            models.addAll(Arrays.asList(extractModels((UIContainer) ui)));
        } if (ui instanceof UIList) {
            models.addAll(Arrays.asList(extractModels((UIList) ui)));
        } else {
            models.add(ui.getModel());
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
