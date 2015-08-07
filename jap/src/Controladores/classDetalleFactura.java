/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Detallefactura;
import entidades.Medidor;
import entidades.Tarifas;
import entidadesCruds.DetallefacturaJpaController;
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
public class classDetalleFactura {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public DetallefacturaJpaController detallefacturaJpacontrolador = new DetallefacturaJpaController(emf);
    classMedidor cm = new classMedidor();
    classTarifas ct = new classTarifas();

    public List<Detallefactura> getDetallefactura() {
        return detallefacturaJpacontrolador.findDetallefacturaEntities();
    }

    public void guardarDetallefactura(int idTarifas, int idMedidor, int medidaAnt, int medidaAct, int consumo, int medExcedido, float tarExcedido, float subtotal, int total) {
        try {

            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            Tarifas idTar = ct.tarifasJpacontrolador.findTarifas(ct.buscarIdTarifas(idTarifas).getIdtarifas());
            Detallefactura dat = new Detallefactura();
            dat.setIdtarifas(idTar);
            dat.setIdmedidor(idmed);
            dat.setMedidaant(medidaAnt);
            dat.setMedidaact(medidaAct);
            dat.setConsumo(consumo);
            dat.setMedexcedido(medExcedido);
            dat.setTarexcedido(tarExcedido);
            dat.setSubtotal(subtotal);
            dat.setTotal(subtotal);
            detallefacturaJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarDetallefactura(int idDetallefactura, int idTarifas, int idMedidor, int medidaAnt, int medidaAct, int consumo, int medExcedido, float tarExcedido, float subtotal, int total) {
        try {
            Detallefactura dat = detallefacturaJpacontrolador.findDetallefactura(idDetallefactura);
            if (dat == null) {
                return false;
            }
            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            Tarifas idTar = ct.tarifasJpacontrolador.findTarifas(ct.buscarIdTarifas(idTarifas).getIdtarifas());
            dat.setIdtarifas(idTar);
            dat.setIdmedidor(idmed);
            dat.setMedidaant(medidaAnt);
            dat.setMedidaact(medidaAct);
            dat.setConsumo(consumo);
            dat.setMedexcedido(medExcedido);
            dat.setTarexcedido(tarExcedido);
            dat.setSubtotal(subtotal);
            dat.setTotal(subtotal);
            detallefacturaJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarDetallefactura(int idetallefactura) throws IllegalOrphanException {

        try {
            detallefacturaJpacontrolador.destroy(idetallefactura);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Detallefactura buscarIdDetallefactura(int idDetallefactura) {

        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getIddetallefac().equals(idDetallefactura)) {
                return dat;
            }
        }
        return null;
    }

    public int medidaAnterior(int numMedidor) {
        int r = 0;
        classMedidor cm = new classMedidor();
        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getIdmedidor().getIdmedidor().equals(cm.buscarMedidorNumM(numMedidor).getIdmedidor())) {
            if (dat.getIddetallefac() > r) {
                r = dat.getMedidaact();
            }
            }
        }
        return r;
    }
}
