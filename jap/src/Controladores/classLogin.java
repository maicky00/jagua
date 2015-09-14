/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmLogin;
import entidades.Login;
import entidades.Usuarios;
import entidadesCruds.LoginJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author JC-PC
 */
public class classLogin {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    private LoginJpaController loginJpacontrolador = new LoginJpaController(emf);
    DefaultTableModel modelo;

    public List<Login> getLogin() {
        return loginJpacontrolador.findLoginEntities();
    }

    public void guardarLogin(String nombres, String Apellidos, String cedula, String nombreUsuario, String clave, String tipoRol, String estadoUsuario) {
        int i = 0;
        Login usu = new Login();
        try {
            for (Login us : getLogin()) {
                if (us.getUsuario().equals(nombreUsuario)) {
                    i = 1;
                    usu = us;
                    break;
                }
            }
            if (i == 1) {

                if (usu.getEstado().equals("INACTIVO")) {
                    JOptionPane.showMessageDialog(null, "Ya existe el Usuario pero se encuentra Inabilitado", "Información", 1);
                    usu = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre Usuario Ya Existe", "Información", 1);
                    usu = null;
                }
            } else {

                Login lg = new Login();
                lg.setNombres(nombres);
                lg.setApellidos(Apellidos);
                lg.setCedula(cedula);
                lg.setUsuario(nombreUsuario);
                lg.setClave(clave);
                lg.setTipo(tipoRol);
                lg.setEstado(estadoUsuario);
                loginJpacontrolador.create(lg);
                JOptionPane.showMessageDialog(null, "Usuario Guardado", "Información", 1);

            }
        } catch (Exception e) {

        }
    }

    public boolean modificarLogin(int id, String nombres, String Apellidos, String cedula, String nombreUsuario, String clave, String tipoRol, String estadoUsuario) {

        try {

            Login dat = loginJpacontrolador.findLogin(id);
            if (dat == null) {
                return false;
            }
            dat.setNombres(nombres);
            dat.setCedula(cedula);
            dat.setApellidos(Apellidos);
            dat.setUsuario(nombreUsuario);
            dat.setClave(clave);
            dat.setTipo(tipoRol);
            dat.setEstado(estadoUsuario);
            loginJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "El Usuario se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarLogin(int id) throws NonexistentEntityException{

        try {
            loginJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Login buscarLoginUsuario(String nombreUsuario) {

        for (Login dat : getLogin()) {
            if (dat.getUsuario().equals(nombreUsuario)) {
                return dat;
            }
        }
        return null;
    }

    public Login buscarLoginId(int idLogin) {

        for (Login dat : getLogin()) {
            if (dat.getIdlogin().equals(idLogin)) {
                return dat;
            }
        }
        return null;
    }

    public Login buscarRol(String nombreUsuario, String pasword) {

        for (Login lg : loginJpacontrolador.findLoginEntities()) {
            if (lg.getUsuario().equals(nombreUsuario) && lg.getClave().equals(pasword)) {
                return lg;
            }
        }
        return null;
    }

    public Login validarUsuario(String usuario, String password) {
        Login BuscarUsu = buscarRol(usuario, password);
        if (BuscarUsu == null) {
            JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta!.",
                    "Error de Auntetificación", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (BuscarUsu.getClave().equals(password) && BuscarUsu.getUsuario().equals(usuario)
                && BuscarUsu.getEstado().equals("ACTIVO")) {
            return BuscarUsu;
        } else if (BuscarUsu.getClave().equals(password) && BuscarUsu.getUsuario().equals(usuario)
                && BuscarUsu.getEstado().equals("INACTIVO")) {
            JOptionPane.showMessageDialog(null, "Usuario Inactivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }

    public void cargarTablaLogin(JTable tabla) {
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[8];
        modelo.addColumn("Nro");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Clave");
        modelo.addColumn("Tipo De Usuario");
        modelo.addColumn("Estado de Cuenta");
        for (Login u : getLogin()) {
            fila[0] = u.getIdlogin();
            fila[1] = u.getNombres();
            fila[2] = u.getApellidos();
            fila[3] = u.getCedula();
            fila[4] = u.getUsuario();
            fila[5] = u.getClave();
            fila[6] = u.getTipo();
            fila[7] = u.getEstado();
            modelo.addRow(fila);
            //sdfdsfds
        }
    }

    public void diseñoTabla(JTable tabla) {
        tabla.getColumn(tabla.getColumnName(0)).setWidth(0);
        tabla.getColumn(tabla.getColumnName(0)).setMinWidth(0);
        tabla.getColumn(tabla.getColumnName(0)).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(3).setResizable(false);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setResizable(false);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setResizable(false);

    }

    public TableModel fillTabla(JTable tabla) {
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseñoTabla(tabla);
        for (Login u : getLogin()) {
            dtm.addRow(new Object[]{
                u.getIdlogin(),
                u.getNombres(),
                u.getApellidos(),
                u.getCedula(),
                u.getUsuario(),
                u.getClave(),
                u.getTipo(),
                u.getEstado()
            });
        }
        return dtm;
    }

    public String elimiEspacio(String m) {
        String a[] = m.split(" ");
        String s = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] != " ") {
                s += a[i];
            }
        }
        return s;
    }

    public boolean buscarNombres(String inicio) {
        Login lg = new Login();
        if (inicio.isEmpty() || inicio.length() > (lg.getNombres().length() + 2)) {
            return false;
        }
        for (int i = 0; i < inicio.length(); ++i) {
            String r = elimiEspacio(lg.getNombres());
            if (inicio.charAt(i) != (r.charAt(i))) {
                return false;
            }
        }
        return true;
    }

//    public void BuscarCed(String txt, JTable tabla) {
//        modelo = new DefaultTableModel();
//        tabla.setModel(modelo);
//        Object[] fila = new Object[8];
//        modelo.addColumn("Nro");
//        modelo.addColumn("Nombres");
//        modelo.addColumn("Apellidos");
//        modelo.addColumn("Cedula");
//        modelo.addColumn("usuario");
//        modelo.addColumn("Clave");
//        modelo.addColumn("Tipo De Usuario");
//        modelo.addColumn("Estado de Cuenta");
//        diseñoTabla(tabla);
//
//        for (Login u : getLogin()) {
//            if (u.buscarApellidos(u.elimiEspacio(txt))) {
//                fila[0] = u.getIdlogin();
//                fila[1] = u.getNombres();
//                fila[2] = u.getApellidos();
//                fila[3] = u.getCedula();
//                fila[4] = u.getUsuario();
//                fila[5] = u.getClave();
//                fila[6] = u.getTipo();
//                fila[7] = u.getEstado();
//                modelo.addRow(fila);
//            }
//        }
//    }
    public TableModel BuscarCed(String txt, JTable tabla) {
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseñoTabla(tabla);
        for (Login u : getLogin()) {
            if (u.buscarCedula(u.elimiEspacio(txt))) {
                dtm.addRow(new Object[]{
                    u.getIdlogin(),
                    u.getNombres(),
                    u.getApellidos(),
                    u.getCedula(),
                    u.getUsuario(),
                    u.getClave(),
                    u.getTipo(),
                    u.getEstado()
                });
            }
        }
        return dtm;
    }

    public void BuscarApe(String txt, JTable tabla) {
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[8];
        modelo.addColumn("Nro");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Clave");
        modelo.addColumn("Tipo De Usuario");
        modelo.addColumn("Estado de Cuenta");

        for (Login u : getLogin()) {
            if (u.buscarApellidos(u.elimiEspacio(txt))) {
                fila[0] = u.getIdlogin();
                fila[1] = u.getNombres();
                fila[2] = u.getApellidos();
                fila[3] = u.getCedula();
                fila[4] = u.getUsuario();
                fila[5] = u.getClave();
                fila[6] = u.getTipo();
                fila[7] = u.getEstado();
                modelo.addRow(fila);
            }
        }
    }
//    public TableModel BuscarApe(String txt, JTable tabla) {
//        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
//        dtm.setRowCount(0);
//        diseñoTabla(tabla);
//        for (Login u : getLogin()) {
//            if (u.buscarApellidos(u.elimiEspacio(txt))) {
//                dtm.addRow(new Object[]{
//                    u.getIdlogin(),
//                    u.getNombres(),
//                    u.getApellidos(),
//                    u.getCedula(),
//                    u.getUsuario(),
//                    u.getClave(),
//                    u.getTipo(),
//                    u.getEstado()
//                });
//            }
//        }
//        return dtm;
//    }
}
