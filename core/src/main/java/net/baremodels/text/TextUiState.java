package net.baremodels.text;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

/**
 * The state of a TextUI at a given instant.
 */
public final class TextUiState {

    public final UIComponent ui;
    public final String text;
    public final Model[] models;

    public TextUiState(UIComponent ui, String text, Model... models) {
        this.ui = ui;
        this.text = text;
        this.models = models;
    }

}
