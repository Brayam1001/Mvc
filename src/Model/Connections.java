package Model;

import java.sql.*;
import javax.swing.JOptionPane;

public class Connections {

    private Connections() {

    }

    private static Connection conectar;
    private static Connections instancia;

    public static final String URL = "jdbc:mysql://localhost:3306/mvcaprendiz";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public Connection conectar() {
        try {
            conectar = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conectar;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return conectar;

    }

    public void cerrarConexion() throws SQLException {
        try {
            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            conectar.close();
        }
    }
//IMPLEMENTACION PATRON DE DISEÑO  SINGLENTON
    public static Connections getInstance() {
        if (instancia == null) {
            instancia = new Connections();
        }
        return instancia;

    }

}
