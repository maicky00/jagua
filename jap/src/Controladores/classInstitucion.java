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

    public List<Institucion> getInstitucion() {
        return institucionJpacontrolador.findInstitucionEntities();
    }

    public void guardarUsuarios(String nombreInstitucion) {
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
                institucionJpacontrolador.create(dat);
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarInstitucion(String nombreIntitucion) {
        int i = 0;
        Institucion usu = new Institucion();

        try {


                Institucion dat = institucionJpacontrolador.findInstitucion(buscarInstitucionNombre(nombreIntitucion).getIdinstitucion());
                if (dat == null) {
                    return false;
                }
                dat.setNombreinst(nombreIntitucion);
                institucionJpacontrolador.edit(dat);
                JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarInstitucion(int idInstitucion) throws IllegalOrphanException {

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
            if (dat.getNombreinst().equals(idInstitucion)) {
                return dat;
            }
        }
        return null;
    }

}
