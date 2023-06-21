package model.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelPersonas {

    private Statement stmt;


    public DefaultTableModel CargaDatos() {
        String[] titulos = {"NIF", "Nombre", "Apellido1", "Apellido2", "Ciudad", "Dirección", "Teléfono", "Fecha Nacimiento", "Sexo", "Tipo"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("select * from persona");
            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("nif");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido1");
                fila[3] = rs.getString("apellido2");
                fila[4] = rs.getString("ciudad");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getString("fecha_nacimiento");
                fila[8] = rs.getString("sexo");
                fila[9] = rs.getString("tipo");
                m.addRow(fila);
            }
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return m;
    }

    public DefaultTableModel buscarPersonaNombre(String nombre){

        String[] titulos = {"NIF", "Nombre", "Apellido1", "Apellido2", "Ciudad", "Dirección", "Teléfono", "Fecha Nacimiento", "Sexo", "Tipo"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);

        stmt = ConectionBD.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM persona WHERE nombre = '" + nombre + "'");


            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("nif");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido1");
                fila[3] = rs.getString("apellido2");
                fila[4] = rs.getString("ciudad");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getString("fecha_nacimiento");
                fila[8] = rs.getString("sexo");
                fila[9] = rs.getString("tipo");
                m.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar en la base de datos");
        }

        return m;

    }

    public void borradoPersona(String dni){

        stmt = ConectionBD.getStmt();

        try {

            stmt.executeUpdate("DELETE FROM persona WHERE nif = '" + dni + "'");

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public String[] buscarTablaModif(String dni){

        String[] datos = new String[10];

        stmt = ConectionBD.getStmt();

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM persona WHERE nif = '" + dni + "'");

            if (!rs.first()){

                JOptionPane.showMessageDialog(null, "No se ha encontrado la persona");
            }
            else {

                datos[0] = rs.getString("nif");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("apellido1");
                datos[3] = rs.getString("apellido2");
                datos[4] = rs.getString("ciudad");
                datos[5] = rs.getString("direccion");
                datos[6] = rs.getString("telefono");
                datos[7] = rs.getString("fecha_nacimiento");
                datos[8] = rs.getString("sexo");
                datos[9] = rs.getString("tipo");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }

    public void agregarNuevoRegistro(String sentencia, String dni){

        stmt = ConectionBD.getStmt();

        try {

            ResultSet rs = stmt.executeQuery("SELECT * FROM persona WHERE nif = '" + dni + "'");

            if (rs.first()){ // Método del resulset para saber si hay dni al principio de la tabla (si no lo hay no existe)

                JOptionPane.showMessageDialog(null, "El dni ya existe en la base de datos", "Error", JOptionPane.WARNING_MESSAGE);


            }else

                stmt.executeUpdate(sentencia);


        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al insertar la persona");
        }

    }

    public void modificarPersona(String senteciaMod){

        stmt = ConectionBD.getStmt();

        try {

            stmt.executeUpdate(senteciaMod);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al modificar la persona");
        }
    }

}
