/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Login;
import entidades.Usuarios;
import entidadesCruds.LoginJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author JC-PC
 */
public class classLogin {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    private LoginJpaController loginJpacontrolador = new LoginJpaController(emf);

    public List<Login> getLogin() {
        return loginJpacontrolador.findLoginEntities();
    }

    public void guardarLogin(String nombres, String Apellidos, String nombreUsuario, String clave, String tipoRol, String estadoUsuario) {
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
                lg.setUsuario(nombreUsuario);
                lg.setClave(clave);
                lg.setTipo(tipoRol);
                lg.setEstado(estadoUsuario);
                loginJpacontrolador.create(lg);
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarLogin(int id, String nombres, String Apellidos, String nombreUsuario, String clave, String tipoRol, String estadoUsuario) {
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

                Login dat = loginJpacontrolador.findLogin(id);
                if (dat == null) {
                    return false;
                }
                dat.setNombres(nombres);
                dat.setApellidos(Apellidos);
                dat.setUsuario(nombreUsuario);
                dat.setClave(clave);
                dat.setTipo(tipoRol);
                dat.setEstado(estadoUsuario);
                loginJpacontrolador.edit(dat);
                JOptionPane.showMessageDialog(null, "El Usuario se Modifico exitosamente", "Información", 1);

            }
        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarLogin(int id) throws IllegalOrphanException {

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
        }else
        if (BuscarUsu.getClave().equals(password) && BuscarUsu.getUsuario().equals(usuario)
                && BuscarUsu.getEstado().equals("ACTIVO")) {
            return BuscarUsu;
        }else
        if (BuscarUsu.getClave().equals(password) && BuscarUsu.getUsuario().equals(usuario)
                && BuscarUsu.getEstado().equals("INACTIVO")) {
            JOptionPane.showMessageDialog(null, "Usuario Inactivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }
}
