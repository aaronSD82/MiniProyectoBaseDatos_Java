package model.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelAsignaturas {

    private  Statement stmt;

    public DefaultTableModel CargaDatos() {
        String[] titulos = {"ID", "Nombre", "Créditos", "Tipo", "Curso", "Cuatrimestre", "Id Profesor", "Id Grado"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("select * from asignatura");
            String[] fila = new String[8];

            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("creditos");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("curso");
                fila[5] = rs.getString("cuatrimestre");
                fila[6] = rs.getString("id_profesor");
                fila[7] = rs.getString("id_grado");
                m.addRow(fila);
            }
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return m;
    }

    public DefaultTableModel busquedaCarreraNombre(String busqueda){

        String[] titulos = {"ID", "Nombre", "Créditos", "Tipo", "Curso", "Cuatrimestre", "Id Profesor", "Id Grado"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);

        try {

            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("SELECT * FROM asignatura WHERE asignatura.nombre LIKE" + "'%" + busqueda + "%'");
            String[] fila = new String[8];
            rs.beforeFirst();

            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("creditos");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("curso");
                fila[5] = rs.getString("cuatrimestre");
                fila[6] = rs.getString("id_profesor");
                fila[7] = rs.getString("id_grado");
                m.addRow(fila);

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return m;
    }

    public DefaultTableModel busquedaCarreraId(String id){

        String[] titulos = {"ID", "Nombre", "Créditos", "Tipo", "Curso", "Cuatrimestre", "Id Profesor", "Id Grado"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);

        try {

            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("SELECT * FROM asignatura WHERE asignatura.id = " + id );
            String[] fila = new String[8];
            rs.beforeFirst();

            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("creditos");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("curso");
                fila[5] = rs.getString("cuatrimestre");
                fila[6] = rs.getString("id_profesor");
                fila[7] = rs.getString("id_grado");
                m.addRow(fila);

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return m;
    }

    public void borradoCarrera(String id){


        try {

            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("SELECT * FROM asignatura WHERE id = " + id);

            if (!rs.first()){

                JOptionPane.showMessageDialog(null, "No existe la asignatura con ese id");
            }
            else {

                stmt.executeUpdate("DELETE FROM asignatura WHERE id = " + id);
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }


    }

    public void agregarNuevoRegistro(String sentencia, String id){

        stmt = ConectionBD.getStmt();

        try {

            ResultSet rs = stmt.executeQuery("SELECT * FROM asignatura WHERE id = " + id);

            if (rs.first()){ // Método del resulset para saber si hay algún elemento al principio de la tabla (si no lo hay no existe)

                JOptionPane.showMessageDialog(null, "El id existe debe escoger otro", "Error", JOptionPane.WARNING_MESSAGE);
            }
            else

                stmt.executeUpdate(sentencia);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al insertar la asignatura");
        }

    }

    public void modificarRegistro(String sentenciaModificar){

        stmt = ConectionBD.getStmt();

        try {

            stmt.executeUpdate(sentenciaModificar);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

}
