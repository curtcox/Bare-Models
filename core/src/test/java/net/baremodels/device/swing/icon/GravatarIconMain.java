package net.baremodels.device.swing.icon;

import javax.swing.*;

public class GravatarIconMain {

    public static void main(String[] args) {
        MainFrame.showFrameLater(() -> {
            JPanel panel = new JPanel();
            String email = "curtcox@gmail.com";
            panel.add(new JLabel(email));
            panel.add(new JLabel(new ImageIcon(GravatarIcon.gravatarUrlFromEmail(email))));
            return panel;
        });
    }

}
