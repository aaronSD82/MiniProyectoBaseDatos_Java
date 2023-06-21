package Controler.com.company;



import model.com.company.ModelPersonas;
import view.com.company.PanelPersonas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPersonas implements ActionListener {

    private final PanelPersonas panelPers = new PanelPersonas();

    private final ModelPersonas person;

    public ControladorPersonas(JButton botonArranque){

        person = new ModelPersonas();
        iniciarEventosPersonas();
        botonArranque.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String selec = e.getActionCommand();

        switch (selec){

            case "Personas": //Muestra la tabla completa de personas.
                mostrarTabla();
                break;


            case "Añadir":
                agregarPersona();
                mostrarTabla();
                break;

            case "Control personas": //Visible ventana asignaturas desde controlador principal.
                iniciarVentanaPersonas();
                break;


            case "Buscar en la tabla": //Busqueda por nombre de la persona.
                filtrarPersona();
                panelPers.getTextoBuscarNombre().setText("");
                break;

            case "Buscar": //Busqueda para rellenar la ventana de modificación o borrado con los datos pertinentes.
                buscarBaseDatos();
                break;

            case "Borrar":
                borradoPersona();
                mostrarTabla();
                break;

            case "Modificar":
                modificarPersona();
                mostrarTabla();
                break;

            case "Limpiar": //Borrado de los datos por si queremos introducir otro distinto al elegido inicialmente.
                limpiarPanelModificar();
                break;

        }
    }

    private void iniciarEventosPersonas(){

        panelPers.getButtonPersonas().addActionListener(this);
        panelPers.getButtonBuscar().addActionListener(this);
        panelPers.getButtonAniadir().addActionListener(this);
        panelPers.getButtonBuscarNombre().addActionListener(this);
        panelPers.getButtonBorrar().addActionListener(this);
        panelPers.getButtonLimpiar().addActionListener(this);
        panelPers.getButtonModificar().addActionListener(this);

    }

    private void iniciarVentanaPersonas(){

        panelPers.setVisible(true);

    }

    private void agregarPersona(){

        boolean datosAgregar;

        if (panelPers.getTextoTlfAgregar().getText().equals("") || panelPers.getTextoTlfAgregar().getText() == null){

            panelPers.getTextoTlfAgregar().setText("Sin Tlf");

        }

        if (panelPers.getTextoNifAgregar().getText().equals("") || panelPers.getTextoNomAgregar().getText().equals("")
                || panelPers.getTextoApell1Agregar().getText().equals("")){

            JOptionPane.showMessageDialog(panelPers, "Debe introducir los campos de dni, nombre y apellido");

            datosAgregar = false;
        }

        else if (panelPers.getTextoAnioAgregar().getText().length() < 4 || panelPers.getTextoAnioAgregar().getText().length() > 4 ||
                panelPers.getTextoMesAgregar().getText().length() < 2 || panelPers.getTextoMesAgregar().getText().length() > 2 ||
                panelPers.getTextoDiaAgregar().getText().length() < 2 || panelPers.getTextoDiaAgregar().getText().length() > 2){

            JOptionPane.showMessageDialog(panelPers, "La fecha introducida no es correcta. El formato es DD/MM/AAAA");

            datosAgregar = false;

        } else if (!panelPers.getTextoSexoAgregar().getText().equals("H") && !panelPers.getTextoSexoAgregar().getText().equals("M")) {

            JOptionPane.showMessageDialog(panelPers, "El formato del sexo ha de ser 'H' o 'M'");

            datosAgregar = false;

        } else if (!panelPers.getTextoTipoAgregar().getText().equals("alumno") && !panelPers.getTextoTipoAgregar().getText().equals("profesor")) {

            JOptionPane.showMessageDialog(panelPers, "El tipo de persona solo puede ser alumno o profesor");

            datosAgregar = false;

        } else if (panelPers.getTextoTlfAgregar().getText().length() > 9) {

            JOptionPane.showMessageDialog(panelPers, "El teléfono introducido no es válido. Es muy largo");

            datosAgregar = false;

        } else

            datosAgregar = true;

        if (datosAgregar){

            String sentenciaAgreg = "INSERT INTO persona (nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, sexo, tipo) VALUES(";
            sentenciaAgreg += "'" + panelPers.getTextoNifAgregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoNomAgregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoApell1Agregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoApell2Agregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoCiudadAgregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoDireccAgregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoTlfAgregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoAnioAgregar().getText() + "-" + panelPers.getTextoMesAgregar().getText() + "-" + panelPers.getTextoDiaAgregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoSexoAgregar().getText() + "', ";
            sentenciaAgreg += "'" + panelPers.getTextoTipoAgregar().getText() + "')";

            System.out.println(sentenciaAgreg);

            person.agregarNuevoRegistro(sentenciaAgreg, panelPers.getTextoNifAgregar().getText());

            panelPers.getTextoNifAgregar().setText("");
            panelPers.getTextoNomAgregar().setText("");
            panelPers.getTextoApell1Agregar().setText("");
            panelPers.getTextoApell2Agregar().setText("");
            panelPers.getTextoCiudadAgregar().setText("");
            panelPers.getTextoDireccAgregar().setText("");
            panelPers.getTextoTlfAgregar().setText("");
            panelPers.getTextoAnioAgregar().setText("");
            panelPers.getTextoMesAgregar().setText("");
            panelPers.getTextoDiaAgregar().setText("");
            panelPers.getTextoSexoAgregar().setText("");
            panelPers.getTextoTipoAgregar().setText("");


        }


    }

    private void mostrarTabla(){

        panelPers.getTablaPersonas().setModel(person.CargaDatos());

    }

    private void buscarBaseDatos(){

        if (panelPers.getTextoNifMod().getText().equals("") || panelPers.getTextoNifMod().getText() == null){

            JOptionPane.showMessageDialog(panelPers, "No se ha introducido el DNI a buscar", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {

            String[] datosModifBorrar = person.buscarTablaModif(panelPers.getTextoNifMod().getText());


            if (datosModifBorrar[1] != null){

                panelPers.getTextoNifMod().setText(datosModifBorrar[0]);
                panelPers.getTextoNombreMod().setText(datosModifBorrar[1]);
                panelPers.getTextoApell1Mod().setText(datosModifBorrar[2]);
                panelPers.getTextoApell2Mod().setText(datosModifBorrar[3]);
                panelPers.getTextoCiudadMod().setText(datosModifBorrar[4]);
                panelPers.getTextoDirecMod().setText(datosModifBorrar[5]);
                panelPers.getTextoTlfMod().setText(datosModifBorrar[6]);
                panelPers.getTextoDiaMod().setText(datosModifBorrar[7].substring(8));
                panelPers.getTextoMesMod().setText(datosModifBorrar[7].substring(5, 7));
                panelPers.getTextoAnioMod().setText(datosModifBorrar[7].substring(0, 4));
                panelPers.getTextoSexoMod().setText(datosModifBorrar[8]);
                panelPers.getTextoTipoMod().setText(datosModifBorrar[9]);


                panelPers.getTextoNifMod().setEditable(false);
                panelPers.getTextoNombreMod().setEditable(true);
                panelPers.getTextoApell1Mod().setEditable(true);
                panelPers.getTextoApell2Mod().setEditable(true);
                panelPers.getTextoCiudadMod().setEditable(true);
                panelPers.getTextoDirecMod().setEditable(true);
                panelPers.getTextoTlfMod().setEditable(true);
                panelPers.getTextoDiaMod().setEditable(true);
                panelPers.getTextoMesMod().setEditable(true);
                panelPers.getTextoAnioMod().setEditable(true);
                panelPers.getTextoSexoMod().setEditable(true);
                panelPers.getTextoTipoMod().setEditable(true);
                panelPers.getButtonModificar().setEnabled(true);
                panelPers.getButtonBorrar().setEnabled(true);
            }
        }

    }

    private void borradoPersona(){

        person.borradoPersona(panelPers.getTextoNifMod().getText());

        panelPers.getTextoNifMod().setText("");
        panelPers.getTextoNombreMod().setText("");
        panelPers.getTextoApell1Mod().setText("");
        panelPers.getTextoApell2Mod().setText("");
        panelPers.getTextoCiudadMod().setText("");
        panelPers.getTextoDirecMod().setText("");
        panelPers.getTextoTlfMod().setText("");
        panelPers.getTextoDiaMod().setText("");
        panelPers.getTextoMesMod().setText("");
        panelPers.getTextoAnioMod().setText("");
        panelPers.getTextoSexoMod().setText("");
        panelPers.getTextoTipoMod().setText("");

        panelPers.getTextoNifMod().setEditable(true);
        panelPers.getTextoNombreMod().setEditable(false);
        panelPers.getTextoApell1Mod().setEditable(false);
        panelPers.getTextoApell2Mod().setEditable(false);
        panelPers.getTextoCiudadMod().setEditable(false);
        panelPers.getTextoDirecMod().setEditable(false);
        panelPers.getTextoTlfMod().setEditable(false);
        panelPers.getTextoDiaMod().setEditable(false);
        panelPers.getTextoMesMod().setEditable(false);
        panelPers.getTextoAnioMod().setEditable(false);
        panelPers.getTextoSexoMod().setEditable(false);
        panelPers.getTextoTipoMod().setEditable(false);
        panelPers.getButtonModificar().setEnabled(false);
        panelPers.getButtonBorrar().setEnabled(false);

    }

    private void modificarPersona() {

        boolean datosModificar;

        if (panelPers.getTextoTlfMod().getText().equals("") || panelPers.getTextoTlfMod().getText() == null) {

            panelPers.getTextoTlfMod().setText("Sin Tlf");

        }

        if (panelPers.getTextoAnioMod().getText().length() < 4 || panelPers.getTextoAnioMod().getText().length() > 4 ||
                panelPers.getTextoMesMod().getText().length() < 2 || panelPers.getTextoMesMod().getText().length() > 2 ||
                panelPers.getTextoDiaMod().getText().length() < 2 || panelPers.getTextoDiaMod().getText().length() > 2) {

            JOptionPane.showMessageDialog(panelPers, "La fecha introducida no es correcta. El formato es DD/MM/AAAA");

            datosModificar = false;

        } else if (!panelPers.getTextoSexoMod().getText().equals("H") && !panelPers.getTextoSexoMod().getText().equals("M")) {

            JOptionPane.showMessageDialog(panelPers, "El formato del sexo ha de ser 'H' o 'M'");

            datosModificar = false;

        } else if (!panelPers.getTextoTipoMod().getText().equals("alumno") && !panelPers.getTextoTipoMod().getText().equals("profesor")) {

            JOptionPane.showMessageDialog(panelPers, "El tipo de persona solo puede ser alumno o profesor");

            datosModificar = false;

        } else if (panelPers.getTextoTlfMod().getText().length() > 9) {

            JOptionPane.showMessageDialog(panelPers, "El teléfono introducido no es válido. Es muy largo");

            datosModificar = false;

        } else
            datosModificar = true;

        if (datosModificar) {

            String senteciaModificar = "UPDATE persona SET ";
            senteciaModificar += "nombre = '" + panelPers.getTextoNombreMod().getText() + "', ";
            senteciaModificar += "apellido1 = '" + panelPers.getTextoApell1Mod().getText() + "', ";
            senteciaModificar += "apellido2 = '" + panelPers.getTextoApell2Mod().getText() + "', ";
            senteciaModificar += "ciudad = '" + panelPers.getTextoCiudadMod().getText() + "', ";
            senteciaModificar += "direccion = '" + panelPers.getTextoDirecMod().getText() + "', ";
            senteciaModificar += "telefono = '" + panelPers.getTextoTlfMod().getText() + "', ";
            senteciaModificar += "fecha_nacimiento = '" + panelPers.getTextoAnioMod().getText() + "-" + panelPers.getTextoMesMod().getText() + "-" + panelPers.getTextoDiaMod().getText() + "', ";
            senteciaModificar += "sexo = '" + panelPers.getTextoSexoMod().getText() + "', ";
            senteciaModificar += "tipo = '" + panelPers.getTextoTipoMod().getText() + "' ";
            senteciaModificar += "WHERE nif = '" + panelPers.getTextoNifMod().getText() + "'";

            System.out.println(senteciaModificar);

            person.modificarPersona(senteciaModificar);

            panelPers.getTextoNifMod().setText("");
            panelPers.getTextoNombreMod().setText("");
            panelPers.getTextoApell1Mod().setText("");
            panelPers.getTextoApell2Mod().setText("");
            panelPers.getTextoCiudadMod().setText("");
            panelPers.getTextoDirecMod().setText("");
            panelPers.getTextoTlfMod().setText("");
            panelPers.getTextoAnioMod().setText("");
            panelPers.getTextoMesMod().setText("");
            panelPers.getTextoDiaMod().setText("");
            panelPers.getTextoSexoMod().setText("");
            panelPers.getTextoTipoMod().setText("");

            panelPers.getTextoNifMod().setEditable(true);
            panelPers.getTextoNombreMod().setEditable(false);
            panelPers.getTextoApell1Mod().setEditable(false);
            panelPers.getTextoApell2Mod().setEditable(false);
            panelPers.getTextoCiudadMod().setEditable(false);
            panelPers.getTextoDirecMod().setEditable(false);
            panelPers.getTextoTlfMod().setEditable(false);
            panelPers.getTextoDiaMod().setEditable(false);
            panelPers.getTextoMesMod().setEditable(false);
            panelPers.getTextoAnioMod().setEditable(false);
            panelPers.getTextoSexoMod().setEditable(false);
            panelPers.getTextoTipoMod().setEditable(false);
            panelPers.getButtonModificar().setEnabled(false);
            panelPers.getButtonBorrar().setEnabled(false);


        }
    }

    private void filtrarPersona(){

        panelPers.getTablaPersonas().setModel(person.buscarPersonaNombre(panelPers.getTextoBuscarNombre().getText()));

    }

    private void limpiarPanelModificar(){

        panelPers.getTextoNifMod().setText("");
        panelPers.getTextoNombreMod().setText("");
        panelPers.getTextoApell1Mod().setText("");
        panelPers.getTextoApell2Mod().setText("");
        panelPers.getTextoCiudadMod().setText("");
        panelPers.getTextoDirecMod().setText("");
        panelPers.getTextoTlfMod().setText("");
        panelPers.getTextoDiaMod().setText("");
        panelPers.getTextoMesMod().setText("");
        panelPers.getTextoAnioMod().setText("");
        panelPers.getTextoSexoMod().setText("");
        panelPers.getTextoTipoMod().setText("");

        panelPers.getTextoNifMod().setEditable(true);
        panelPers.getTextoNombreMod().setEditable(false);
        panelPers.getTextoApell1Mod().setEditable(false);
        panelPers.getTextoApell2Mod().setEditable(false);
        panelPers.getTextoCiudadMod().setEditable(false);
        panelPers.getTextoDirecMod().setEditable(false);
        panelPers.getTextoTlfMod().setEditable(false);
        panelPers.getTextoDiaMod().setEditable(false);
        panelPers.getTextoMesMod().setEditable(false);
        panelPers.getTextoAnioMod().setEditable(false);
        panelPers.getTextoSexoMod().setEditable(false);
        panelPers.getTextoTipoMod().setEditable(false);
        panelPers.getButtonModificar().setEnabled(false);
        panelPers.getButtonBorrar().setEnabled(false);



    }

    public PanelPersonas getPanel() {
        return panelPers;
    }
}

