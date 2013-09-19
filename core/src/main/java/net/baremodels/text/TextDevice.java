package net.baremodels.text;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

public final class TextDevice
    implements GenericDevice
{
    final FakeUser screen;
    final TextComponentTranslator translator;

    public TextDevice(FakeUser screen) {
        this(screen,new TextComponentTranslator());
    }

    private TextDevice(FakeUser screen, TextComponentTranslator translator) {
        this.screen = screen;
        this.translator = translator;
    }

    @Override
    public Model display(UIComponent ui) {
        return screen.set(translator.translate(ui));
    }

    @Override
    public void onIntent(Intent intent) {

    }

}
