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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    public void guardarFacturas(int idDetFact, int numFactura, Date fechaEmision, float subtotal, float iva, float total, String usuarioActual) {
        try {

            Detallefactura idDetF = cm.detallefacturaJpacontrolador.findDetallefactura(cm.buscarIdDetallefactura(idDetFact).getIddetallefac());
            Facturas dat = new Facturas();
            dat.setIddetallefac(idDetF);
            dat.setNumfactura(numFactura);
            dat.setFechaemision(fechaEmision);
            dat.setSubtotal(subtotal);
            dat.setIva(iva);
            dat.setTotal(total);
            dat.setUsuarioactual(usuarioActual);
            facturasJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarDetallefactura(int idFactura, int idDetFact, int numFactura, Date fechaEmision, float subtotal, float iva, float total, String usuarioActual) {
        try {
            Facturas dat = facturasJpacontrolador.findFacturas(idFactura);
            if (dat == null) {
                return false;
            }
            Detallefactura idDetF = cm.detallefacturaJpacontrolador.findDetallefactura(cm.buscarIdDetallefactura(idDetFact).getIddetallefac());
            dat.setIddetallefac(idDetF);
            dat.setNumfactura(numFactura);
            dat.setFechaemision(fechaEmision);
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

    public Facturas buscarRucCi(String rucCi) {
        int i = 0;
        for (Facturas dat : getFacturas()) {
            if (dat.getIddetallefac().getIdmedidor().getIdusuario().getRucci().equals(rucCi)) {
                i = i + 1;
                if (i > 1) {
                    JOptionPane.showMessageDialog(null, "Usuario Con mas de un Medidor \n Busque por Num. medidor", "Información", 1);
                }
                return dat;
            }
        }
        return null;
    }

    public int numFactura() {
        int i = 0;
        for (Facturas dat : getFacturas()) {
            if (dat.getNumfactura() > i) {
                i = dat.getNumfactura();
            }
        }
        return i;
    }

    public void IngresarlistFact(List<Facturas> listFact) {
        try {
            if (listFact.size()>0) {
               for (Facturas dat : listFact) {
                facturasJpacontrolador.create(dat);
            }
            JOptionPane.showMessageDialog(null, "Realizado", "Información", 1);
      
            }else{
                JOptionPane.showMessageDialog(null, "No hay valores a Pagar", "Información", 1);
      
            }
               } catch (Exception ex) {
        }

    }

    public List<Facturas> getTableFacturas(JTable table, int numFactura, Date fechaEmision, float subtotal, float iva, float total, String usuarioActual) {

        classDetalleFactura cdf = new classDetalleFactura();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        List<Facturas> lst = new ArrayList<>();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();

        for (int i = 0; i < nRow; i++) {
            Detallefactura idFact = cdf.detallefacturaJpacontrolador.findDetallefactura(cdf.buscarIdDetallefactura(Integer.valueOf(dtm.getValueAt(i, 0).toString())).getIddetallefac());

            Facturas dat = new Facturas();
            dat.setIddetallefac(idFact);
            dat.setNumfactura(numFactura);
            dat.setFechaemision(fechaEmision);
            dat.setSubtotal(subtotal);
            dat.setIva(iva);
            dat.setTotal(total);
            dat.setUsuarioactual(usuarioActual);
            lst.add(dat);
            cdf.modificarTransaccion(cdf.buscarIdDetallefactura(Integer.valueOf(dtm.getValueAt(i, 0).toString())).getIddetallefac(), "SI");
        }
        return lst;
    }
}
