/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Asistencia;
import entidades.Medidor;
import entidades.Planificacion;
import entidadesCruds.AsistenciaJpaController;
import entidadesCruds.PagosasistenciaJpaController;
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
public class classAsistencia {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public AsistenciaJpaController asistenciaJpacontrolador = new AsistenciaJpaController(emf);
    classPlanificacion cp = new classPlanificacion();
    classMedidor cm = new classMedidor();

    public List<Asistencia> getAsistencia() {
        return asistenciaJpacontrolador.findAsistenciaEntities();
    }

    public void guardarAsistencia(int idPlanificacion, int NumMedidor, String asistencia, float valorMulta, String descripcion) {
        try {

            Planificacion idPlan = cp.planificacionJpacontrolador.findPlanificacion(cp.buscarIdPlanificacion(idPlanificacion).getIdplanificacion());
            Medidor idMed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(NumMedidor).getIdmedidor());
            Asistencia dat = new Asistencia();
            dat.setIdplanificacion(idPlan);
            dat.setIdmedidor(idMed);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            asistenciaJpacontrolador.create(dat);
            JOptionPane.showMessageDialog(null, "Guardado", "Información", 1);


        } catch (Exception e) {

        }
    }

    public boolean modificarAsistencia(int idAsistencia, int idPlanificacion, int NumMedidor, String asistencia, float valorMulta, String descripcion) {
        try {
            Asistencia dat = asistenciaJpacontrolador.findAsistencia(idAsistencia);
            if (dat == null) {
                return false;
            }
            Planificacion idPlan = cp.planificacionJpacontrolador.findPlanificacion(cp.buscarIdPlanificacion(idPlanificacion).getIdplanificacion());
            Medidor idMed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(NumMedidor).getIdmedidor());
            dat.setIdplanificacion(idPlan);
            dat.setIdmedidor(idMed);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            asistenciaJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarAsistencia(int idAsistencia) throws IllegalOrphanException {

        try {
            asistenciaJpacontrolador.destroy(idAsistencia);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (Exception ex) {
        }

    }

    public Asistencia buscarIdAsistencia(int idAsistencia) {

        for (Asistencia dat : getAsistencia()) {
            if (dat.getIdasistencia().equals(idAsistencia)) {
                return dat;
            }
        }
        return null;
    }

}
