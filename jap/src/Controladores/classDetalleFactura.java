/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmFactura;
import entidades.Detallefactura;
import entidades.Login;
import entidades.Medidor;
import entidades.Tarifas;
import entidadesCruds.DetallefacturaJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

    public void guardarDetallefactura(int idTarifas, int idMedidor, String anioMes, int medidaAnt, int medidaAct, int consumo, int medExcedido, float tarExcedido, float subtotal, float total, String observacion) {
        try {

            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            Tarifas idTar = ct.tarifasJpacontrolador.findTarifas(ct.buscarIdTarifas(idTarifas).getIdtarifas());
            Detallefactura dat = new Detallefactura();
            dat.setAniomes(anioMes);
            dat.setIdtarifas(idTar);
            dat.setIdmedidor(idmed);
            dat.setMedidaant(medidaAnt);
            dat.setMedidaact(medidaAct);
            dat.setConsumo(consumo);
            dat.setMedexcedido(medExcedido);
            dat.setTarexcedido(tarExcedido);
            dat.setSubtotal(subtotal);
            dat.setTotal(total);
            dat.setObservacion(observacion);
            detallefacturaJpacontrolador.create(dat);
            JOptionPane.showMessageDialog(null, "Guardado", "Información", 1);

        } catch (Exception e) {

        }
    }

    public boolean modificarDetallefactura(int idDetallefactura, int idTarifas, int idMedidor, String anioMes, int medidaAnt, int medidaAct, int consumo, int medExcedido, float tarExcedido, float subtotal, float total, String observacion) {
        try {
            Detallefactura dat = detallefacturaJpacontrolador.findDetallefactura(idDetallefactura);
            if (dat == null) {
                return false;
            }
            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            Tarifas idTar = ct.tarifasJpacontrolador.findTarifas(ct.buscarIdTarifas(idTarifas).getIdtarifas());
            dat.setIdtarifas(idTar);
            dat.setAniomes(anioMes);
            dat.setIdmedidor(idmed);
            dat.setMedidaant(medidaAnt);
            dat.setMedidaact(medidaAct);
            dat.setConsumo(consumo);
            dat.setMedexcedido(medExcedido);
            dat.setTarexcedido(tarExcedido);
            dat.setSubtotal(subtotal);
            dat.setTotal(total);
            dat.setObservacion(observacion);
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

    public Detallefactura buscarNumMedDetallefactura(int numMedidor) {

        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getIdmedidor().getNummedidor().equals(numMedidor)) {
                return dat;
            }
        }
        return null;
    }

    public int medidaAnterior(int idMedidor) {
        int r = 0;
        int r2 = 0;
        classMedidor cm = new classMedidor();
        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getIdmedidor().getIdmedidor() == (cm.buscarMedidorNumM2(idMedidor)) && (dat.getIddetallefac() > r2)) {
//                if (dat.getIddetallefac() > r) {
                r2 = dat.getIddetallefac();
                r = dat.getMedidaact();
//                }
            }
        }
        return r;
    }

    public TableModel tablaDetalles(JTable tabla, int numMedidor) {

        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseñoTabla(tabla);
        float subtotal = 0;
        float iva = Float.valueOf(FrmFactura.tabla2.getValueAt(1, 1).toString());
        float total = 0;
        for (Detallefactura u : getDetallefactura()) {
            if (u.getObservacion().equals("NO") && u.getIdmedidor().getNummedidor() == numMedidor) {
                subtotal = subtotal + u.getTotal();
                dtm.addRow(new Object[]{
                    u.getIddetallefac(),
                    u.getAniomes(),
                    //                u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    //                + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre(),
                    //                u.getIdusuario().getApadosn(),
                    u.getMedidaant(),
                    u.getMedidaact(),
                    u.getConsumo(),
                    u.getMedexcedido(),
                    u.getTarexcedido(),
                    u.getSubtotal(),
                    u.getTotal()
                });
            }
        }
        FrmFactura.tabla2.setValueAt(subtotal, 0, 1);
        total = (iva * subtotal) + subtotal;
        FrmFactura.tabla2.setValueAt(total, 2, 1);
        return dtm;
    }

    public void diseñoTabla(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(140);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);


    }
    public boolean modificarTransaccion(int idDetallefactura, String observacion) {
        try {
            Detallefactura dat = detallefacturaJpacontrolador.findDetallefactura(idDetallefactura);
            if (dat == null) {
                return false;
            }
            dat.setObservacion(observacion);
            detallefacturaJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }
}
