package view.com.company;

import javax.swing.*;
import java.awt.*;

public class PanelAsignaturas extends JFrame {
    private JTextField busqueda;
    private JButton busquedaIdAsigButton;
    private JPanel panelAsignatura;

    private JTable tablaAsig;
    private JButton asignaturasButton;
    private JButton buscarAsigButton;

    private JButton buttonAgreAsig;
    private JButton buttonEliminar;
    private JButton buttonModificar;
    private JTextField textoBorrarAsig;
    private JButton buttonConfirmarBorrado;
    private JLabel fieldBorrar;
    private JButton buttonVolver;


    public PanelAsignaturas() {

        super("Asignaturas");
        setContentPane(panelAsignatura);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        textoBorrarAsig.setEditable(false);
        buttonConfirmarBorrado.setEnabled(false);
        fieldBorrar.setVisible(false);

    }

    public JTextField getBusqueda() {
        return busqueda;
    }

    public JTable getTablaAsig() {
        return tablaAsig;
    }

    public JButton getAsignaturasButton() {
        return asignaturasButton;
    }

    public JButton getBusquedaIdAsignaturaButton() {
        return busquedaIdAsigButton;
    }

    public JButton getBuscarAsigButton() {
        return buscarAsigButton;
    }

    public JButton getButtonAgreAsig() {
        return buttonAgreAsig;
    }

    public JButton getButtonEliminar() {
        return buttonEliminar;
    }

    public JButton getButtonModificar() {
        return buttonModificar;
    }

    public JTextField getTextoBorrarAsig() {
        return textoBorrarAsig;
    }

    public JButton getButtonConfirmarBorrado() {
        return buttonConfirmarBorrado;
    }

    public JLabel getFieldBorrar() {
        return fieldBorrar;
    }

    public JButton getBotonVolver(){
        return buttonVolver;
    }

}
