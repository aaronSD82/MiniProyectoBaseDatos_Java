package view.com.company;

import javax.swing.*;


public class DialogoEntrada extends JDialog {
    private JPanel panelDialog;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textoNombre;
    private JTextField textoCreditos;
    private JTextField textoTipo;
    private JTextField textoCurso;
    private JTextField textoCuatri;
    private JTextField textoIdProf;
    private JTextField textoIdGrado;
    private JTextField textoId;
    private JButton modificarButton;

    public DialogoEntrada() {

        setContentPane(panelDialog);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 350);

    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JTextField getTextoNombre() {
        return textoNombre;
    }

    public JTextField getTextoCreditos() {
        return textoCreditos;
    }

    public JTextField getTextoTipo() {
        return textoTipo;
    }

    public JTextField getTextoCurso() {
        return textoCurso;
    }

    public JTextField getTextoCuatri() {
        return textoCuatri;
    }

    public JTextField getTextoIdProf() {
        return textoIdProf;
    }

    public JTextField getTextoIdGrado() {
        return textoIdGrado;
    }

    public JTextField getTextoId() {
        return textoId;
    }

    public JButton getModificarButton() {
        return modificarButton;
    }

    public JPanel getPanelDialog() {
        return panelDialog;
    }


}
