package net.baremodels.device.swing.icon;

import net.baremodels.ui.UIGlyph;

import javax.swing.*;
import java.awt.*;

public class FontAwesomeIconMain {

    public static void main(String[] args) {
        MainFrame.showFrameLater(()-> {
            JPanel panel = new JPanel(new GridLayout(74,10));
            for (UIGlyph glyph : UIGlyph.values()) {
                panel.add(new JLabel(glyph.name()));
                panel.add(new JLabel(new FontAwesomeIcon(glyph, 13)));
            }
            return panel;
        });
    }

}
