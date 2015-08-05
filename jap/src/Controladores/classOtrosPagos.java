/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Corte;
import entidades.Otrospagos;
import entidadesCruds.OtrospagosJpaController;
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
public class classOtrosPagos {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    private OtrospagosJpaController otrospagosJpacontrolador = new OtrospagosJpaController(emf);
    classCorte cc = new classCorte();

    public List<Otrospagos> getOtrospagos() {
        return otrospagosJpacontrolador.findOtrospagosEntities();
    }

    public void guardarOtrospagos(int idcorte, String derConx, float mulRecx, float multMS, float interes, String serie) {
        try {

            Corte idOtpg = cc.corteJpacontrolador.findCorte(cc.buscarIdCorte(idcorte).getIdcorte());
            Otrospagos dat = new Otrospagos();
            dat.setIdcorte(idOtpg);
            dat.setDerconx(derConx);
            dat.setMulrecx(mulRecx);
            dat.setMultms(multMS);
            dat.setInteres(interes);
            dat.setSerie(serie);
            otrospagosJpacontrolador.create(dat);
            JOptionPane.showMessageDialog(null, "Pago realizado corectamente");

        } catch (Exception e) {

        }
    }

    public boolean modificarOtrospagos(int idOtrospagos, int idcorte, String derConx, float mulRecx, float multMS, float interes, String serie) {

        try {
            Otrospagos dat = otrospagosJpacontrolador.findOtrospagos(idOtrospagos);
            if (dat == null) {
                return false;
            }
            Corte idOtpg = cc.corteJpacontrolador.findCorte(cc.buscarIdCorte(idcorte).getIdcorte());
            dat.setIdcorte(idOtpg);
            dat.setDerconx(derConx);
            dat.setMulrecx(mulRecx);
            dat.setMultms(multMS);
            dat.setInteres(interes);
            dat.setSerie(serie);
            otrospagosJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarOtrospagos(int idOtrospagos) throws IllegalOrphanException {

        try {
            otrospagosJpacontrolador.destroy(idOtrospagos);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Otrospagos buscarIdOtrospagos(int idOtrospagos) {

        for (Otrospagos dat : getOtrospagos()) {
            if (dat.getIdotpagos().equals(idOtrospagos)) {
                return dat;
            }
        }
        return null;
    }

}
