package net.baremodels.device.swing.icon;

import net.baremodels.ui.UIIcon;

import javax.swing.*;

public final class SwingIconSupplier {

    public Icon getIcon(UIIcon icon) {
        return new FontAwesomeIcon(icon.glyph,16);
    }

}
