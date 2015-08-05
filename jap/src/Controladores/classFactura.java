/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Detallefactura;
import entidades.Facturas;
import entidadesCruds.FacturasJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author JC-PC
 */
public class classFactura {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public FacturasJpaController facturasJpacontrolador = new FacturasJpaController(emf);
    classDetalleFactura cm = new classDetalleFactura();

    public List<Facturas> getFacturas() {
        return facturasJpacontrolador.findFacturasEntities();
    }

    public void guardarFacturas(int idDetFact, int numFactura, Date fechaEmision, String mes, float valorBase, float exceso, float subtotal, float iva, float total, String usuarioActual) {
        try {

            Detallefactura idDetF = cm.detallefacturaJpacontrolador.findDetallefactura(cm.buscarIdDetallefactura(idDetFact).getIddetallefac());
            Facturas dat = new Facturas();
            dat.setIddetallefac(idDetF);
            dat.setNumfactura(numFactura);
            dat.setFechaemision(fechaEmision);
            dat.setMes(mes);
            dat.setValorbase(valorBase);
            dat.setExceso(exceso);
            dat.setSubtotal(subtotal);
            dat.setIva(iva);
            dat.setTotal(total);
            dat.setUsuarioactual(usuarioActual);
            facturasJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarDetallefactura(int idFactura,int idDetFact, int numFactura, Date fechaEmision, String mes, float valorBase, float exceso, float subtotal, float iva, float total, String usuarioActual) {
        try {
            Facturas dat = facturasJpacontrolador.findFacturas(idFactura);
            if (dat == null) {
                return false;
            }
            Detallefactura idDetF = cm.detallefacturaJpacontrolador.findDetallefactura(cm.buscarIdDetallefactura(idDetFact).getIddetallefac());
            dat.setIddetallefac(idDetF);
            dat.setNumfactura(numFactura);
            dat.setFechaemision(fechaEmision);
            dat.setMes(mes);
            dat.setValorbase(valorBase);
            dat.setExceso(exceso);
            dat.setSubtotal(subtotal);
            dat.setIva(iva);
            dat.setTotal(total);
            dat.setUsuarioactual(usuarioActual);
            facturasJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarFacturas(int idFacturas) throws IllegalOrphanException {

        try {
            facturasJpacontrolador.destroy(idFacturas);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Facturas buscarIdFacturas(int idFacturas) {

        for (Facturas dat : getFacturas()) {
            if (dat.getIdfactura().equals(idFacturas)) {
                return dat;
            }
        }
        return null;
    }

}
