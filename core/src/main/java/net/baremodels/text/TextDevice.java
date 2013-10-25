package net.baremodels.text;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;
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
    final FakeUser screen;
    final SimpleComponentListener listener = new SimpleComponentListener();
    final SimpleComponentTranslator translator;

    public TextDevice(FakeUser screen) {
        this(screen,new SimpleComponentTranslator(new TextWidgetSupplier()));
    }

    private TextDevice(FakeUser screen, SimpleComponentTranslator translator) {
        this.screen = screen;
        this.translator = translator;
    }

    @Override
    public Model display(UIComponent ui) {
        return screen.pickModelFrom(generateUiState(ui));
    }

    private TextUiState generateUiState(UIComponent ui) {
        return new TextUiState(ui, translator.translate(ui, listener),extractModels(ui));
    }

    private Model[] extractModels(UIComponent ui) {
        List<Model> models = new ArrayList<>();
        if (ui instanceof UIList) {
            models.addAll(Arrays.asList(extractModels((UIList) ui)));
        } else {
            models.add(ui.getModel());
        }
        return models.toArray(new Model[0]);
    }

    private Model[] extractModels(UIList ui) {
        return ui.getModel().properties().values().stream().map(x->x.model()).toArray(x -> new Model[x]);
    }

    @Override
    public void onIntent(Intent intent) {

    }

}
