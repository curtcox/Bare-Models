package net.baremodels.device.text;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

import java.util.Arrays;

/**
 * The state of a TextUI at a given instant.
 */
public final class TextUiState {

    public final Model showing;
    public final UIComponent ui;
    public final String text;
    public final Model[] selectable;

    public TextUiState(Model showing, UIComponent ui, String text, Model... selectable) {
        this.showing = showing;
        this.ui = ui;
        this.text = text;
        this.selectable = selectable;
    }

    public String toString() {
        return String.format("showing=%s ui=%s text=%s selectable=%s",showing,ui,text, Arrays.asList(selectable));
    }
}
