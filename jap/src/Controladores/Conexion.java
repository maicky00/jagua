/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan_s
 */
public class Conexion {

    private Connection con;
    private String user = "root";
    private String pass = "root";
    private String url = "jdbc:mysql://localhost:3306/sistemaagua2";

    public Conexion() {
        abrir();
    }

    public Connection getCon() {
        return con;
    }

    private void abrir() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cerrar() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
