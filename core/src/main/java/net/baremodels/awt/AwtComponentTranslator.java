package net.baremodels.awt;

import net.baremodels.ui.UIComponent;

import javax.swing.*;
import java.awt.*;

public class AwtComponentTranslator {

    public Component translate(UIComponent ui) {
        return new Button();
    }
}
