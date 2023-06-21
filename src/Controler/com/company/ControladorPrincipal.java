package Controler.com.company;

import Connecion.ConectionBD;
import view.com.company.PanelPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControladorPrincipal extends WindowAdapter implements ActionListener{

    private final PanelPrincipal frEntrada = new PanelPrincipal();

    private ControladorAsignaturas controlAsig;

    private ControladorPersonas controlPers;


    public ControladorPrincipal() {

        iniciarVentana();

    }

    public void iniciarVentana() {

        frEntrada.setVisible(true);
        frEntrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frEntrada.getButtonConexion().addActionListener(this);

    }

    public void iniciarEventos() { // Agregados aquí los constructores de los controladores para que no se inicien sin primero iniciar la conexión.

        frEntrada.getButtonPanelAsig().addActionListener(this);
        frEntrada.addWindowListener(this);
        frEntrada.getButtonPanelPers().addActionListener(this);
        controlAsig = new ControladorAsignaturas(frEntrada.getButtonPanelAsig());
        controlPers = new ControladorPersonas(frEntrada.getButtonPanelPers());
        controlAsig.getPanel().getBotonVolver().addActionListener(this);
        controlAsig.getPanel().addWindowListener(this);
        controlPers.getPanel().getButtonVolver().addActionListener(this);
        controlPers.getPanel().addWindowListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frEntrada.getButtonConexion()){

            ConectionBD.openConn();
            iniciarEventos();
            JOptionPane.showMessageDialog(frEntrada, "Ahora está conectado a la base de datos");
            frEntrada.getButtonConexion().setEnabled(false);
        }
        else if (e.getSource() == frEntrada.getButtonPanelAsig()){

            controlPers.getPanel().setVisible(false);
            frEntrada.setVisible(false);

        }
        else if (e.getSource() == frEntrada.getButtonPanelPers()){

            controlAsig.getPanel().setVisible(false);
            frEntrada.setVisible(false);

        } else if (e.getSource() == controlAsig.getPanel().getBotonVolver() || e.getSource() == controlPers.getPanel().getButtonVolver()) {

            frEntrada.setVisible(true);

        }


    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getWindow() == controlAsig.getPanel() || e.getWindow() == controlPers.getPanel()){ //Condicional para ver si es la ventana principal la que se cierra o una de las secundarias
                                                      // y volver a hacer visible la ventana principal.
            frEntrada.setVisible(true);

        } else

            ConectionBD.closeConn();

    }

}
