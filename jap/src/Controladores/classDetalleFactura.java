/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmFactura;
import static Formularios.FrmFactura.lblGrafico;
import Formularios.frmDetalleFactura;
import entidades.Detallefactura;
import entidades.Login;
import entidades.Medidor;
import entidades.Tarifas;
import entidadesCruds.DetallefacturaJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author JC-PC
 */
public class classDetalleFactura {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public DetallefacturaJpaController detallefacturaJpacontrolador = new DetallefacturaJpaController(emf);
    classMedidor cm = new classMedidor();
    classTarifas ct = new classTarifas();
    DefaultTableModel mod;

    public List<Detallefactura> getDetallefactura() {
        return detallefacturaJpacontrolador.findDetallefacturaEntities();
    }

    public void guardarDetallefactura(int idTarifas, int idMedidor, String anioMes, int medidaAnt, int medidaAct, int consumo, int medExcedido, float tarExcedido, float subtotal, float total, String observacion) {
        try {
            int i = 0;
            Detallefactura med = new Detallefactura();
            for (Detallefactura md : getDetallefactura()) {
                if (md.getIdmedidor().getIdmedidor().equals(idMedidor) && md.getAniomes().equals(anioMes)) {
                    i = 1;
                    med = md;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Num. Medidor, Fecha Ya Estan Registrados", "Informacion", 1);
                med = null;

            } else {
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
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarDetallefactura(int idDetallefactura, int idTarifas, int idMedidor, String anioMes, int medidaAnt, int medidaAct, int consumo, int medExcedido, float tarExcedido, float subtotal, float total) {
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
            detallefacturaJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarDetallefactura(int idetallefactura) throws NonexistentEntityException {

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

    public Detallefactura buscarNumMedAnioMes(int numMedidor, String anioMes) {

        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getIdmedidor().getNummedidor().equals(numMedidor) && dat.getAniomes().equals(anioMes)) {
                return dat;
            }
        }
        return null;
    }

    public int buscarAnioMes(String anioMes) {
        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getAniomes().equals(anioMes)) {
                return 1;
            }
        }
        return 0;
    }

    public int buscardiferentes(int id, String anioMes) {

        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getIdmedidor().getIdmedidor().equals(id) && dat.getAniomes().equals(anioMes)) {
                return dat.getIdmedidor().getIdmedidor();
            }
        }
        return 0;
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
        float iva = 0;
        float total = 0;
        for (Detallefactura u : getDetallefactura()) {
            if (u.getObservacion().equals("NO") && u.getIdmedidor().getNummedidor() == numMedidor) {
                subtotal = subtotal + u.getTotal();
                iva = u.getIdtarifas().getIva();
                dtm.addRow(new Object[]{
                    u.getIddetallefac(),
                    u.getAniomes(),
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
        FrmFactura.tabla2.setValueAt(iva, 1, 0);
        FrmFactura.tabla2.setValueAt(iva * subtotal, 1, 1);
        total = (iva * subtotal) + subtotal;
        float recon = Float.valueOf(FrmFactura.txtIdCorte.getText());
        FrmFactura.tabla2.setValueAt(total, 2, 1);
        FrmFactura.txtTotalgeneral.setText(String.valueOf(total + recon));
        FrmFactura.txtTotalgeneral.setText(String.valueOf(total + recon));
        return dtm;
    }

    public void diseñoTabla(JTable tabla) {
//        tabla.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabla.getColumn(tabla.getColumnName(0)).setWidth(0);
        tabla.getColumn(tabla.getColumnName(0)).setMinWidth(0);
        tabla.getColumn(tabla.getColumnName(0)).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(140);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(70);

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

//    public TableModel tablaMedidas(JTable tabla, String anioMes) {
//
//        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
//        dtm.setRowCount(0);
//
//        for (Detallefactura u : getDetallefactura()) {
//            if (u.getObservacion().equals("NO") && u.getAniomes().equals(anioMes)) {
//                dtm.addRow(new Object[]{
//                    u.getIddetallefac(),
//                    u.getAniomes(),
//                    u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
//                    + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre(),
//                    u.getIdusuario().getApadosn(),
//                    u.getMedidaant(),
//                    u.getMedidaact(),
//                    u.getConsumo(),
//                    u.getMedexcedido(),
//                    u.getTarexcedido(),
//                    u.getSubtotal(),
//                    u.getTotal()
//                });
//            }
//        }
//        return dtm;
//    }
//    public List<Detallefactura> getbuscarDetalleFact(String anioMes) {
//        try {
//            classMedidor cm = new classMedidor();
//            List<Detallefactura> datos = new ArrayList<Detallefactura>();
////            List<Medidor> datos = new ArrayList<Medidor>();
//            for (Medidor e : cm.getbuscarMedidor()) {
//                datos.add(e);
//            }
//            return datos;
//        } catch (Exception e) {
//        }
//        return null;
//
//    }
    public void cargarBusquedaAnioMes(JTable tabla, String anioMes) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("N°");
        modelo.addColumn("Tarifa");
        modelo.addColumn("Num. Medidor");
        modelo.addColumn("Usuario");
        modelo.addColumn("Fecha Correspondiente");
        modelo.addColumn("Medida Anterior");
        modelo.addColumn("Medida Actual");

//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Detallefactura u : getDetallefactura()) {
            if (u.getAniomes().equals(anioMes)) {
                fila[0] = u.getIddetallefac();
                fila[1] = u.getIdtarifas().getDescripcion();
                fila[2] = u.getIdmedidor().getNummedidor();
                fila[3] = u.getIdmedidor().getIdusuario().getPrimerapellido() + "  " + u.getIdmedidor().getIdusuario().getSegundoapellido() + "  "
                        + u.getIdmedidor().getIdusuario().getPrimernombre() + "  " + u.getIdmedidor().getIdusuario().getSegundonombre();
                fila[4] = u.getAniomes();
                fila[5] = u.getMedidaant();
                fila[6] = u.getMedidaact();
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(60);
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(90);
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(170);
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(100);
                frmDetalleFactura.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(100);

                modelo.addRow(fila);
            }
        }
    }

    public void tablaCorte(JTable tabla) {

        mod = new DefaultTableModel();
        tabla.setModel(mod);
        Object[] fila = new Object[4];
        mod.addColumn("id");
        mod.addColumn("idNum");
        mod.addColumn("Num medidor");
        mod.addColumn("observacion");

        for (Detallefactura u : getDetallefactura()) {
            System.out.println(u.getIdmedidor().getNummedidor() + " " + u.getIdmedidor().getIdmedidor() + u.getObservacion());
            if (numContar(u.getIdmedidor().getIdmedidor()) > 3 && u.getObservacion().equals("NO")) {
                //System.out.println(u.getIdmedidor().getNummedidor() + " " + u.getIdmedidor().getIdmedidor());
                fila[0] = u.getIddetallefac();
                fila[1] = u.getIdmedidor().getIdmedidor();
                fila[2] = u.getIdmedidor().getNummedidor();
                fila[3] = u.getObservacion();
            }
        }
        mod.addRow(fila);

    }

    public int numContar(int idNumMedidor) {
        int papitas = 0;

        for (Detallefactura dat : getDetallefactura()) {
            if (dat.getIdmedidor().getIdmedidor().equals(idNumMedidor) && dat.getObservacion().equals("NO")) {
                papitas = papitas + 1;
            }
        }
        return papitas;
    }

    public ArrayList<String> listaUltimosMeses(int idmed) {
        ArrayList<String> datos = new ArrayList<String>();
        for (Detallefactura s : getDetallefactura()) {
            if (s.getIdmedidor().getIdmedidor() == idmed) {
                datos.add("" + s.getIddetallefac());
            }
        }
        if (datos.size() < 5) {
            for (int i = datos.size(); i < 5; i++) {
                datos.add("" + 0);
            }
        }
        return datos;
    }

    public ArrayList<String> listaUltimosMeses2(int idmed) {
        ArrayList<String> datos = new ArrayList<String>();
        for (int j = listaUltimosMeses(idmed).size() - 1; j >= listaUltimosMeses(idmed).size() - 5; j--) {
            datos.add("" + listaUltimosMeses(idmed).get(j));
        }
        return datos;
    }

    public void graficador(int numMedidor) {
        try {
            DefaultCategoryDataset datos = new DefaultCategoryDataset();
            for (Detallefactura u : getDetallefactura()) {
                for (int i = 0; i < 5; i++) {
                    if (u.getIdmedidor().getNummedidor() == numMedidor) {
                        if (Integer.valueOf(listaUltimosMeses2(numMedidor).get(i)) == (u.getIddetallefac())) {
                            datos.setValue(u.getConsumo(), "Fecha: " + u.getAniomes(), u.getAniomes());
                        }
                    }
                }

            }

            JFreeChart linea = ChartFactory.createBarChart3D("Historial de Consumo", "Fecha Correspondiente", "m3",
                    datos, PlotOrientation.VERTICAL, true, true, false);
            linea.setBackgroundPaint(Color.cyan);
            linea.getTitle().setPaint(Color.blue);
            CategoryPlot p = linea.getCategoryPlot();
            p.setRangeGridlinePaint(Color.red);
            BufferedImage graficoLinea = linea.createBufferedImage(400, 220);
            FrmFactura.lblGrafico.setSize(400, 100);
            FrmFactura.lblGrafico.setIcon(new ImageIcon(graficoLinea));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public String mesMator(int idmedidor, int año, int mes) {
        String cond = "";
        java.sql.Date fe = new java.sql.Date(año, mes, 1);
      
        int r2 = 0;
        classMedidor cm = new classMedidor();
        for (Detallefactura dat : getDetallefactura()) {
            String f[] = dat.getAniomes().split("-");
            java.sql.Date fe1 = new java.sql.Date(Integer.valueOf(f[0]), Integer.valueOf(f[1]), 1);
//            fe1.setYear(Integer.valueOf(f[0])-1900);
//        fe1.setMonth(Integer.valueOf(f[1])-1);
       

            if (dat.getIdmedidor().getIdmedidor() == (cm.buscarMedidorNumM2(idmedidor)) && (dat.getIddetallefac() > r2)) {
                if (fe.getDate() > fe1.getDate()) {
                    r2 = dat.getIddetallefac();
                    cond = fe.getDate() + "  "  + dat.getAniomes();
                }
            }
        }
        return cond;
    }

    public static void main(String[] args) {
        classDetalleFactura f = new classDetalleFactura();
        System.out.println("" + f.mesMator(1, 2015,5));
    }
}
