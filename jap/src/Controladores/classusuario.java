/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Institucion;
import entidades.Usuarios;
import entidadesCruds.UsuariosJpaController;
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
public class classusuario {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public UsuariosJpaController usuariosJpacontrolador = new UsuariosJpaController(emf);
    classInstitucion ci=new classInstitucion();
    public List<Usuarios> getUsuarios() {
        return usuariosJpacontrolador.findUsuariosEntities();
    }

    public void guardarUsuarios(String nombreInst, String rucCi, String priNombre, String segNombre, String priApellido, String segApellido, String apodoNS, String direccion, String telefono, String celular, String sector, String referencia, byte[] foto, String observacion) {
        int i = 0;
        Usuarios usu = new Usuarios();
        try {
            for (Usuarios us : getUsuarios()) {
                if (us.getRucci().equals(rucCi)) {
                    i = 1;
                    usu = us;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Ruc/CI Existente", "Información", 1);
                usu = null;
            } else {
                Institucion inst = ci.institucionJpacontrolador.findInstitucion(ci.buscarInstitucionNombre(nombreInst).getIdinstitucion());

                Usuarios dat = new Usuarios();
                dat.setIdinstitucion(inst);
                dat.setRucci(rucCi);
                dat.setPrimernombre(priNombre);
                dat.setSegundonombre(segNombre);
                dat.setPrimerapellido(priApellido);
                dat.setSegundoapellido(segApellido);
                dat.setApadosn(apodoNS);
                dat.setDireccion(direccion);
                dat.setTelefono(telefono);
                dat.setCelular(celular);
                dat.setSector(sector);
                dat.setReferencia(referencia);
                dat.setFoto(foto);
                dat.setObservacion(observacion);
                usuariosJpacontrolador.create(dat);
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarUsuario(int id, String rucCi, String priNombre, String segNombre, String priApellido, String segApellido, String apodoNS, String direccion, String telefono, String celular, String sector, String referencia, byte[] foto, String observacion) {
        int i = 0;
        Usuarios usu = new Usuarios();

        try {
            for (Usuarios us : getUsuarios()) {
                if (us.getRucci().equals(rucCi)) {
                    i = 1;
                    usu = us;
                    break;
                }
            }
            if (i == 1) {

                JOptionPane.showMessageDialog(null, "RUC/CI Existente", "Información", 1);
                usu = null;

            } else {

                Usuarios dat = usuariosJpacontrolador.findUsuarios(id);
                if (dat == null) {
                    return false;
                }
                dat.setRucci(rucCi);
                dat.setPrimernombre(priNombre);
                dat.setSegundonombre(segNombre);
                dat.setPrimerapellido(priApellido);
                dat.setSegundoapellido(segApellido);
                dat.setApadosn(apodoNS);
                dat.setDireccion(direccion);
                dat.setTelefono(telefono);
                dat.setCelular(celular);
                dat.setSector(sector);
                dat.setReferencia(referencia);
                dat.setFoto(foto);
                dat.setObservacion(observacion);
                usuariosJpacontrolador.edit(dat);
                JOptionPane.showMessageDialog(null, "El Usuario se Modifico exitosamente", "Información", 1);

            }
        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarUsuario(int id) throws IllegalOrphanException {

        try {
            usuariosJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Usuarios buscarUsuarioRucCi(String rucCi) {

        for (Usuarios dat : getUsuarios()) {
            if (dat.getRucci().equals(rucCi)) {
                return dat;
            }
        }
        return null;
    }

    public Usuarios buscarLoginId(int idUsuario) {

        for (Usuarios dat : getUsuarios()) {
            if (dat.getIdusuario().equals(idUsuario)) {
                return dat;
            }
        }
        return null;
    }
}
