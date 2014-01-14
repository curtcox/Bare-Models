package net.baremodels.device.text;

import net.baremodels.model.Model;
import net.baremodels.ui.UIContainer;

import java.util.Arrays;

/**
 * The immutable state of a TextUI at a given instant.
 */
public final class TextUiState {

    /**
     * The model that is showing on the screen.
     */
    public final Model showing;

    /**
     * The UI container that is on screen.
     */
    public final UIContainer container;

    /**
     * The text that is on screen.
     */
    public final String text;

    /**
     * The on-screen models that could be selected.
     */
    public final Model[] selectable;

    /**
     * The Model that the "user" selected.
     */
    public final Model selected;

    /**
     * The next Model to be presented as showing.
     */
    public final Model next;

    public TextUiState(Model showing, UIContainer container, String text, Model... selectable) {
        this(showing,null,null,container,text,selectable);
    }

    TextUiState(Model showing, Model selected, Model next, UIContainer container, String text, Model... selectable) {
        this.showing = showing;
        this.selected = selected;
        this.next = next;
        this.container = container;
        this.text = text;
        this.selectable = selectable;
    }

    public String toString() {
        return String.format("showing=%s container=%s text=%s selectable=%s",showing, container,text, Arrays.asList(selectable));
    }

    public TextUiState withSelected(Model selected) {
        return new TextUiState(showing,selected,next,container,text,selectable);
    }

    public TextUiState withNext(Model next) {
        return new TextUiState(showing,selected,next,container,text,selectable);
    }
}
