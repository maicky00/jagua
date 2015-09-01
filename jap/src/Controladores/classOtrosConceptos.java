/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Otrosconceptos;
import entidadesCruds.OtrosconceptosJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JC-PC
 */
public class classOtrosConceptos {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    private OtrosconceptosJpaController otrosconceptosJpacontrolador = new OtrosconceptosJpaController(emf);

    public List<Otrosconceptos> getOtrosconceptos() {
        return otrosconceptosJpacontrolador.findOtrosconceptosEntities();
    }

    public void guardarOtrosconceptos(String descr, float cantidad, int tiempo) {
        try {

            Otrosconceptos lg = new Otrosconceptos();
            lg.setDescripcion(descr);
            lg.setCantidad(cantidad);
            lg.setTiempo(tiempo);
            otrosconceptosJpacontrolador.create(lg);

        } catch (Exception e) {

        }
    }

    public boolean modificarOtrosconceptos(int id, String descr, float cantidad, int tiempo) {

        try {

            Otrosconceptos dat = otrosconceptosJpacontrolador.findOtrosconceptos(id);
            if (dat == null) {
                return false;
            }
            dat.setDescripcion(descr);
            dat.setCantidad(cantidad);
            dat.setTiempo(tiempo);
            otrosconceptosJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarOtrosconceptos(int id) throws IllegalOrphanException {

        try {
            otrosconceptosJpacontrolador.destroy(id);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Otrosconceptos buscarOtrosconceptos(int id) {

        for (Otrosconceptos dat : getOtrosconceptos()) {
            if (dat.getId().equals(id)) {
                return dat;
            }
        }
        return null;
    }

    public Otrosconceptos multa(int id, int numMed) {
        classDetalleFactura cd = new classDetalleFactura();
        if (cd.numContar(numMed) > 1) {
            return buscarOtrosconceptos(id);
        }
        return null;
    }

}
