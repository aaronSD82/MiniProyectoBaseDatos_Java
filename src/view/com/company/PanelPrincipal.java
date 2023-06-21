package view.com.company;

import javax.swing.*;

public class PanelPrincipal extends JFrame {

    private JPanel principal;
    private JButton buttonPanelPers;
    private JButton buttonPanelAsig;
    private JButton buttonConexion;


    public PanelPrincipal() {

        add(principal);
        setBounds(460, 340, 1000, 400);

    }

    public JPanel getPrincipal() {
        return principal;
    }

    public JButton getButtonPanelPers() {
        return buttonPanelPers;
    }

    public JButton getButtonPanelAsig() {
        return buttonPanelAsig;
    }

    public JButton getButtonConexion() {
        return buttonConexion;
    }
}
