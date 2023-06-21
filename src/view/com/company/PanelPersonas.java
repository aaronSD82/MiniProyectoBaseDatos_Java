package view.com.company;

import javax.swing.*;
import java.awt.*;

public class PanelPersonas extends JFrame {
    private JPanel panelPersonas;
    private JTable tablaPersonas;
    private JTextField textoNifAgregar;
    private JTextField textoNomAgregar;
    private JTextField textoApell1Agregar;
    private JTextField textoApell2Agregar;
    private JTextField textoCiudadAgregar;
    private JTextField textoDireccAgregar;
    private JTextField textoTlfAgregar;
    private JTextField textoAnioAgregar;
    private JTextField textoMesAgregar;
    private JTextField textoDiaAgregar;
    private JTextField textoSexoAgregar;
    private JTextField textoTipoAgregar;
    private JTextField textoNifMod;
    private JTextField textoNombreMod;
    private JTextField textoApell1Mod;
    private JTextField textoApell2Mod;
    private JTextField textoDirecMod;
    private JTextField textoTlfMod;
    private JTextField textoAnioMod;
    private JTextField textoMesMod;
    private JTextField textoDiaMod;
    private JTextField textoSexoMod;
    private JTextField textoTipoMod;
    private JTextField textoCiudadMod;
    private JButton buttonAniadir;
    private JButton buttonModificar;
    private JButton buttonBuscar;
    private JButton buttonBorrar;
    private JButton buttonPersonas;
    private JButton buttonVolver;
    private JTextField textoBuscarNombre;
    private JButton buttonBuscarNombre;
    private JButton buttonLimpiar;

    public PanelPersonas(){

        super("Personas");
        add(panelPersonas);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        textoNombreMod.setEditable(false);
        textoApell1Mod.setEditable(false);
        textoApell2Mod.setEditable(false);
        textoCiudadMod.setEditable(false);
        textoDirecMod.setEditable(false);
        textoTlfMod.setEditable(false);
        textoDiaMod.setEditable(false);
        textoMesMod.setEditable(false);
        textoAnioMod.setEditable(false);
        textoSexoMod.setEditable(false);
        textoTipoMod.setEditable(false);
        buttonModificar.setEnabled(false);
        buttonBorrar.setEnabled(false);
    }

    public JPanel getPanelPersonas() {
        return panelPersonas;
    }

    public JTable getTablaPersonas() {
        return tablaPersonas;
    }

    public JTextField getTextoNifAgregar() {
        return textoNifAgregar;
    }

    public JTextField getTextoNomAgregar() {
        return textoNomAgregar;
    }

    public JTextField getTextoApell1Agregar() {
        return textoApell1Agregar;
    }

    public JTextField getTextoApell2Agregar() {
        return textoApell2Agregar;
    }

    public JTextField getTextoCiudadAgregar() {
        return textoCiudadAgregar;
    }

    public JTextField getTextoDireccAgregar() {
        return textoDireccAgregar;
    }

    public JTextField getTextoTlfAgregar() {
        return textoTlfAgregar;
    }

    public JTextField getTextoAnioAgregar() {
        return textoAnioAgregar;
    }

    public JTextField getTextoMesAgregar() {
        return textoMesAgregar;
    }

    public JTextField getTextoDiaAgregar() {
        return textoDiaAgregar;
    }

    public JTextField getTextoSexoAgregar() {
        return textoSexoAgregar;
    }

    public JTextField getTextoTipoAgregar() {
        return textoTipoAgregar;
    }

    public JTextField getTextoNifMod() {
        return textoNifMod;
    }

    public JTextField getTextoNombreMod() {
        return textoNombreMod;
    }

    public JTextField getTextoApell1Mod() {
        return textoApell1Mod;
    }

    public JTextField getTextoApell2Mod() {
        return textoApell2Mod;
    }

    public JTextField getTextoDirecMod() {
        return textoDirecMod;
    }

    public JTextField getTextoTlfMod() {
        return textoTlfMod;
    }

    public JTextField getTextoAnioMod() {
        return textoAnioMod;
    }

    public JTextField getTextoMesMod() {
        return textoMesMod;
    }

    public JTextField getTextoDiaMod() {
        return textoDiaMod;
    }

    public JTextField getTextoSexoMod() {
        return textoSexoMod;
    }

    public JTextField getTextoTipoMod() {
        return textoTipoMod;
    }

    public JTextField getTextoCiudadMod() {
        return textoCiudadMod;
    }

    public JButton getButtonAniadir() {
        return buttonAniadir;
    }

    public JButton getButtonModificar() {
        return buttonModificar;
    }

    public JButton getButtonBuscar() {
        return buttonBuscar;
    }

    public JButton getButtonBorrar() {
        return buttonBorrar;
    }

    public JButton getButtonPersonas() {
        return buttonPersonas;
    }

    public JButton getButtonVolver() {
        return buttonVolver;
    }

    public JTextField getTextoBuscarNombre() {
        return textoBuscarNombre;
    }

    public JButton getButtonBuscarNombre() {
        return buttonBuscarNombre;
    }

    public JButton getButtonLimpiar() {
        return buttonLimpiar;
    }
}
