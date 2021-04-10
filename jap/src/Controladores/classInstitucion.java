/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Institucion;
import entidadesCruds.InstitucionJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author JC-PC
 */
public class classInstitucion {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public InstitucionJpaController institucionJpacontrolador = new InstitucionJpaController(emf);
    FileInputStream entrada;
    FileOutputStream salida;

    public List<Institucion> getInstitucion() {
        return institucionJpacontrolador.findInstitucionEntities();
    }

    public void guardarInstitucion(String nombreInstitucion, String direccion, String telefono, String email, String ruc, String celular, byte[] foto) {
        int i = 0;
        Institucion usu = new Institucion();
        try {
            for (Institucion us : getInstitucion()) {
                if (us.getNombreinst().equals(nombreInstitucion)) {
                    i = 1;
                    usu = us;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Existente", "Información", 1);
                usu = null;
            } else {

                Institucion dat = new Institucion();
                dat.setNombreinst(nombreInstitucion);
                dat.setDireccion(direccion);
                dat.setTelefono(telefono);
                dat.setEmail(email);
                dat.setRuc(ruc);
                dat.setCelular(celular);

                if (foto != null) {
                    dat.setLogo(foto);
                } else {
                    dat.setLogo(null);
                }
                institucionJpacontrolador.create(dat);
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarInstitucion(int id, String nombreInstitucion, String direccion, String telefono, String email, String ruc, String celular, byte[] foto) {
        int i = 0;
        Institucion usu = new Institucion();

        try {

            Institucion dat = institucionJpacontrolador.findInstitucion(id);
            if (dat == null) {
                return false;
            }
            dat.setNombreinst(nombreInstitucion);
            dat.setDireccion(direccion);
            dat.setTelefono(telefono);
            dat.setEmail(email);
            dat.setRuc(ruc);
            dat.setCelular(celular);
            if (foto != null) {
                dat.setLogo(foto);
            }
            institucionJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Datos Guardados", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarInstitucion(int idInstitucion) throws NonexistentEntityException, IllegalOrphanException {

        try {
            institucionJpacontrolador.destroy(idInstitucion);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Institucion buscarInstitucionNombre(String nombreInstitucion) {

        for (Institucion dat : getInstitucion()) {
            if (dat.getNombreinst().equals(nombreInstitucion)) {
                return dat;
            }
        }
        return null;
    }

    public Institucion buscarIdInstitucion(int idInstitucion) {

        for (Institucion dat : getInstitucion()) {
            if (dat.getIdinstitucion().equals(idInstitucion)) {
                return dat;
            }
        }
        return null;
    }

    public byte[] AbrirAImagen(File archivo) {
        byte[] bytesImg = new byte[1024 * 100];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytesImg);
        } catch (Exception e) {
        }
        return bytesImg;
    }

    public String GuardarAImagen(File archivo, byte[] bytesImg) {
        String respuesta = null;
        try {
            salida = new FileOutputStream(archivo);
            salida.write(bytesImg);
        } catch (Exception e) {
        }
        return respuesta;
    }
}
