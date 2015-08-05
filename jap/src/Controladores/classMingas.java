/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import entidades.Corte;
import entidades.Medidor;
import entidades.Mingas;
import entidadesCruds.CorteJpaController;
import entidadesCruds.MingasJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author JC-PC
 */
public class classMingas {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public MingasJpaController mingasJpacontrolador = new MingasJpaController(emf);
    classMedidor cm = new classMedidor();

    public List<Mingas> getMingas() {
        return mingasJpacontrolador.findMingasEntities();
    }

    public void guardarMingas(int idMedidor, String lugar, Date fecha, String asistencia, float valorMulta,String descripcion) {
        try {

            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            Mingas dat = new Mingas();
            dat.setIdmedidor(idmed);
            dat.setLugar(lugar);
            dat.setFecha(fecha);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            mingasJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarMingas(int idMinga,int idMedidor, String lugar, Date fecha, String asistencia, float valorMulta,String descripcion) {
        try {
            Mingas dat = mingasJpacontrolador.findMingas(idMinga);
            if (dat == null) {
                return false;
            }
            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            dat.setIdmedidor(idmed);
            dat.setLugar(lugar);
            dat.setFecha(fecha);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            mingasJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarMingas(int idMinga) throws IllegalOrphanException {

        try {
            mingasJpacontrolador.destroy(idMinga);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Mingas buscarIdMingas(int idMingas) {

        for (Mingas dat : getMingas()) {
            if (dat.getIdminga().equals(idMingas)) {
                return dat;
            }
        }
        return null;
    }

}
