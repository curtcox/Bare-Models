package net.baremodels.device.text;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A device for integration testing with a simulated user.
 */
public final class TextDevice
    implements GenericDevice
{
    final FakeUser user;
    final SimpleComponentListener componentListener = new SimpleComponentListener();
    final Intent.Listener intentListener;
    final SimpleComponentTranslator translator;

    public TextDevice(FakeUser user, Intent.Listener intentListener) {
        this(user,new SimpleComponentTranslator(new TextWidgetSupplier()),intentListener);
    }

    private TextDevice(FakeUser user, SimpleComponentTranslator translator, Intent.Listener intentListener) {
        this.user = user;
        this.translator = translator;
        this.intentListener = intentListener;
    }

    @Override
    public Model display(UIComponent ui) {
        return user.pickModelFrom(generateUiState(ui));
    }

    private TextUiState generateUiState(UIComponent ui) {
        return new TextUiState(ui.getModel(),ui, translator.translate(ui, componentListener),extractModels(ui));
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
        intentListener.onIntent(intent);
    }

}
