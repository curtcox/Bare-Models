package net.baremodels.text;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

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

}
