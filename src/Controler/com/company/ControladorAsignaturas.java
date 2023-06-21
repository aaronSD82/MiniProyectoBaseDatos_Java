package Controler.com.company;


import model.com.company.ModelAsignaturas;
import view.com.company.DialogoEntrada;
import view.com.company.PanelAsignaturas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControladorAsignaturas extends WindowAdapter implements ActionListener {

    private final PanelAsignaturas panel = new PanelAsignaturas();

    private final ModelAsignaturas asig;

    private final DialogoEntrada agregar = new DialogoEntrada();

    public ControladorAsignaturas(JButton botonArranque){

        asig = new ModelAsignaturas();

        botonArranque.addActionListener(this);

        agregar.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        iniciarEventosAsignatura();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String selec = e.getActionCommand();

        switch (selec){

            case "Asignaturas":

                mostrarTabla();
                break;

            case "Busqueda por nombre":

                buscarBaseDatos();
                break;

            case "Agregar Asignatura":

                agregar.getTextoId().setEditable(true);
                agregar.getButtonOK().setEnabled(true);
                agregar.getModificarButton().setEnabled(false);
                agregar.setVisible(true);
                break;

            case "Control asignaturas": //Visible ventana asignaturas desde controlador principal.

                iniciarVentanaAsignatura();
                break;

            case "Eliminar Asignatura":  // Habilita los botones de borrado.

                panel.getButtonConfirmarBorrado().setEnabled(true);
                panel.getTextoBorrarAsig().setEditable(true);
                panel.getFieldBorrar().setVisible(true);
                break;

            case "Confirmar": // Realiza el borrado.

                String idBorrar = panel.getTextoBorrarAsig().getText();
                confirmarBorrado(idBorrar);
                break;

            case "Modificar Asignatura":

                int fila = panel.getTablaAsig().getSelectedRow();

                if (fila == -1){

                    JOptionPane.showMessageDialog(panel, "Debes seleccionar en la tabla la asignatura a modificar");

                }else {

                    agregar.getTextoId().setText((String) panel.getTablaAsig().getValueAt(fila, 0));
                    agregar.getTextoId().setEditable(false);
                    agregar.getTextoNombre().setText((String) panel.getTablaAsig().getValueAt(fila, 1));
                    agregar.getTextoCreditos().setText((String) panel.getTablaAsig().getValueAt(fila, 2));
                    agregar.getTextoTipo().setText((String) panel.getTablaAsig().getValueAt(fila, 3));
                    agregar.getTextoCurso().setText((String) panel.getTablaAsig().getValueAt(fila, 4));
                    agregar.getTextoCuatri().setText((String) panel.getTablaAsig().getValueAt(fila, 5));
                    agregar.getTextoIdProf().setText((String) panel.getTablaAsig().getValueAt(fila, 6));
                    agregar.getTextoIdGrado().setText((String) panel.getTablaAsig().getValueAt(fila, 7));

                    agregar.getModificarButton().setEnabled(true);
                    agregar.getButtonOK().setEnabled(false);

                    agregar.setVisible(true);
                }
                break;

            case "Busqueda por Id":

                buscarBaseDatosId();
                break;


        }
    }

    public void iniciarEventosAsignatura(){

        panel.getAsignaturasButton().addActionListener(this);
        panel.getButtonAgreAsig().addActionListener(this);
        panel.getButtonEliminar().addActionListener(this);
        panel.getButtonModificar().addActionListener(this);
        panel.getBuscarAsigButton().addActionListener(this);
        panel.getBusquedaIdAsignaturaButton().addActionListener(this);
        panel.getButtonConfirmarBorrado().addActionListener(this);
        panel.getBusquedaIdAsignaturaButton().addActionListener(this);
        agregar.getButtonOK().addActionListener(this::agregarAsignatura);
        agregar.getButtonCancel().addActionListener(this::cancelarDialogo);
        agregar.getModificarButton().addActionListener(this::modificarAsignatura);
        agregar.addWindowListener(this);

    }

    private void iniciarVentanaAsignatura(){

        panel.setVisible(true);

    }

    private void agregarAsignatura(ActionEvent agr){

        if (agregar.getTextoId().getText().equals("") || agregar.getTextoNombre().getText().equals("")){ //Método para obligar a rellenar estos campos para el borrado por Id.

            JOptionPane.showMessageDialog(null, "Es obligatorio introducir el id y el nombre");

        }
        else {

            String sentenciaAgreg = "INSERT INTO asignatura VALUES(";
            sentenciaAgreg += "'" + agregar.getTextoId().getText() + "', ";
            sentenciaAgreg += "'" + agregar.getTextoNombre().getText() + "', ";
            sentenciaAgreg += "'" + agregar.getTextoCreditos().getText() + "', ";
            sentenciaAgreg += "'" + agregar.getTextoTipo().getText() + "', ";
            sentenciaAgreg += "'" + agregar.getTextoCurso().getText() + "', ";
            sentenciaAgreg += "'" + agregar.getTextoCuatri().getText() + "', ";
            sentenciaAgreg += "'" + agregar.getTextoIdProf().getText() + "', ";
            sentenciaAgreg += "'" + agregar.getTextoIdGrado().getText() + "')";

            System.out.println(sentenciaAgreg);

            asig.agregarNuevoRegistro(sentenciaAgreg, agregar.getTextoId().getText());

            mostrarTabla();

            agregar.getModificarButton().setEnabled(true);

            agregar.setVisible(false);
        }

    }

    private void confirmarBorrado(String idBorrar){

        if (idBorrar == null || idBorrar.equals("")){

            JOptionPane.showMessageDialog(null, "No ha rellenado el campo");

            panel.getFieldBorrar().setVisible(false);
            panel.getTextoBorrarAsig().setEditable(false);
            panel.getButtonConfirmarBorrado().setEnabled(false);
        }
        else {

            asig.borradoCarrera(idBorrar);
            panel.getTextoBorrarAsig().setText("");
            panel.getFieldBorrar().setVisible(false);
            panel.getTextoBorrarAsig().setEditable(false);
            panel.getButtonConfirmarBorrado().setEnabled(false);
            mostrarTabla();
        }
    }

    private void modificarAsignatura(ActionEvent mod){


        String sentenciaModificar;
        if (agregar.getTextoIdProf().getText().equals("") || agregar.getTextoIdProf().getText() == null) { // Este condicional es porque muchas de las asignaturas
            // que ya hay no tienen el profesor y por si quieres modificarla
            // sin el profesor también tener esa opción.
            sentenciaModificar = "UPDATE asignatura SET ";
            sentenciaModificar += "nombre = '" + agregar.getTextoNombre().getText() + "', ";
            sentenciaModificar += "creditos = " + agregar.getTextoCreditos().getText() + ", ";
            sentenciaModificar += "tipo = '" + agregar.getTextoTipo().getText() + "', ";
            sentenciaModificar += "curso = " + agregar.getTextoCurso().getText() + ", ";
            sentenciaModificar += "cuatrimestre = " + agregar.getTextoCuatri().getText() + ", ";
            sentenciaModificar += "id_grado = " + agregar.getTextoIdGrado().getText() + " ";
            sentenciaModificar += "WHERE id = " + agregar.getTextoId().getText();

            System.out.println(sentenciaModificar);

            asig.modificarRegistro(sentenciaModificar);

            panel.getBusqueda().setText(agregar.getTextoNombre().getText());//Esto muestra solo la asignatura
            // modificada en la tabla.
            buscarBaseDatos();

            agregar.getTextoId().setText("");
            agregar.getTextoNombre().setText("");
            agregar.getTextoCreditos().setText("");
            agregar.getTextoTipo().setText("");
            agregar.getTextoCurso().setText("");
            agregar.getTextoCuatri().setText("");
            agregar.getTextoIdProf().setText("");
            agregar.getTextoIdGrado().setText("");
            agregar.getTextoId().setEditable(true);
            agregar.getButtonOK().setEnabled(true);

            agregar.setVisible(false);


        } else {

            sentenciaModificar = "UPDATE asignatura SET ";
            sentenciaModificar += "nombre = '" + agregar.getTextoNombre().getText() + "', ";
            sentenciaModificar += "creditos = " + agregar.getTextoCreditos().getText() + ", ";
            sentenciaModificar += "tipo = '" + agregar.getTextoTipo().getText() + "', ";
            sentenciaModificar += "curso = " + agregar.getTextoCurso().getText() + ", ";
            sentenciaModificar += "cuatrimestre = " + agregar.getTextoCuatri().getText() + ", ";
            sentenciaModificar += "id_profesor = " + agregar.getTextoIdProf().getText() + ", ";
            sentenciaModificar += "id_grado = " + agregar.getTextoIdGrado().getText() + " ";
            sentenciaModificar += "WHERE id = " + agregar.getTextoId().getText();

            System.out.println(sentenciaModificar);

            asig.modificarRegistro(sentenciaModificar);

            panel.getBusqueda().setText(agregar.getTextoNombre().getText());//Esto muestra solo la asignatura
            // modificada en la tabla.
            buscarBaseDatos();

            agregar.getTextoId().setText("");
            agregar.getTextoNombre().setText("");
            agregar.getTextoCreditos().setText("");
            agregar.getTextoTipo().setText("");
            agregar.getTextoCurso().setText("");
            agregar.getTextoCuatri().setText("");
            agregar.getTextoIdProf().setText("");
            agregar.getTextoIdGrado().setText("");
            agregar.getTextoId().setEditable(true);
            agregar.getButtonOK().setEnabled(true);

            agregar.setVisible(false);

        }


    }

    private void mostrarTabla(){

        panel.getTablaAsig().setModel(asig.CargaDatos());
    }

    private void buscarBaseDatos(){

        panel.getTablaAsig().setModel(asig.busquedaCarreraNombre(panel.getBusqueda().getText()));
    }

    private void buscarBaseDatosId(){

        if (!panel.getBusqueda().getText().equals("") && panel.getBusqueda().getText() != null){

            panel.getTablaAsig().setModel(asig.busquedaCarreraId(panel.getBusqueda().getText()));

        }

    }

    private void cancelarDialogo(ActionEvent can){

        agregar.getTextoId().setText("");
        agregar.getTextoNombre().setText("");
        agregar.getTextoCreditos().setText("");
        agregar.getTextoTipo().setText("");
        agregar.getTextoCurso().setText("");
        agregar.getTextoCuatri().setText("");
        agregar.getTextoIdProf().setText("");
        agregar.getTextoIdGrado().setText("");
        agregar.getTextoId().setEditable(true);

        agregar.setVisible(false);

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getWindow() == agregar){

            agregar.getTextoId().setText("");
            agregar.getTextoNombre().setText("");
            agregar.getTextoCreditos().setText("");
            agregar.getTextoTipo().setText("");
            agregar.getTextoCurso().setText("");
            agregar.getTextoCuatri().setText("");
            agregar.getTextoIdProf().setText("");
            agregar.getTextoIdGrado().setText("");
            agregar.getTextoId().setEditable(true);

            agregar.setVisible(false);
        }

    }

    public PanelAsignaturas getPanel() {
        return panel;
    }

}

