/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jap;

import Controladores.Conexion;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.hyphenation.TernaryTree;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author COMPUAXIR
 */
public class ReportesControlador {

    Conexion con;

    public void carpeta() {
        File directorio = new File("C:\\Archivos");
        if (!directorio.exists()) {
            directorio.mkdir();
        }

    }

    public String subcarpeta(String nom) {
        File directorio = new File("C:\\Archivos\\" + nom);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        return "" + directorio;
    }

    public ReportesControlador() {
        con = new Conexion();
    }

    public static void main(String[] args) {
        ReportesControlador b = new ReportesControlador();

//        b.reporte("us.jasper");
//        b.facturaPlanificacion("numfact", "2", "pagoPlanificacion.jasper");
    }

    public void reporte(String archivo, String nom) {
        try {

            JasperReport reporte = null;
            try {
                URL in = this.getClass().getResource(archivo);
                reporte = (JasperReport) JRLoader.loadObject(in);
            } catch (JRException jr) {
                System.out.println("Error al cargar ." + jr.getMessage());
            }
            Map parametro = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getCon());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("REPORTE");
            jv.setVisible(true);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Archivos\\" + nom + ".pdf");

        } catch (Exception e) {
            System.out.println("Error al cargar reporte.");
        }

    }

    public void factura(String bddVar, String numfact, String bddVar1, String id, String archivo, String carpeta, String nom) {
        try {
            JasperReport reporte = null;
            URL in1 = null;
            try {
                URL in = this.getClass().getResource(archivo);
                in1 = this.getClass().getResource("");
                reporte = (JasperReport) JRLoader.loadObject(in);
            } catch (JRException jr) {
                System.out.println("Error .");
            }
            Map parametro = new HashMap();
            parametro.put(bddVar1, id);
            parametro.put(bddVar, numfact);
            parametro.put("SUBREPORT_DIR", "" + in1);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getCon());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("FACTURA");
            jv.setVisible(true);
//            JasperPrintManager.printReport(jasperPrint, false); imprime
            JasperExportManager.exportReportToPdfFile(jasperPrint, subcarpeta(carpeta) + "\\" + nom + ".pdf");

        } catch (Exception e) {
        }

    }

    public void facturaPlanificacion(String bddVar, String numfact, String archivo, String carpeta, String nom) {
        try {
            JasperReport reporte = null;
            try {
                URL in = this.getClass().getResource(archivo);
                reporte = (JasperReport) JRLoader.loadObject(in);
            } catch (JRException jr) {
                System.out.println("Error .");
            }
            Map parametro = new HashMap();
            parametro.put(bddVar, numfact);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getCon());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("FACTURA");
            jv.setVisible(true);
//            JasperPrintManager.printReport(jasperPrint, false); imprime
            JasperExportManager.exportReportToPdfFile(jasperPrint, subcarpeta(carpeta) + "\\" + nom + ".pdf");

        } catch (Exception e) {
        }

    }

    public void totalesMes(String bddVar1, String anio, String bddVar2, String mes, String archivo) {
        try {
            JasperReport reporte = null;
            URL in1 = null;
            try {
                URL in = this.getClass().getResource(archivo);
                in1 = this.getClass().getResource("");
                reporte = (JasperReport) JRLoader.loadObject(in);
            } catch (JRException jr) {
                System.out.println("Error ");
            }
            Map parametro = new HashMap();
            parametro.put(bddVar1, anio);
            parametro.put(bddVar2, mes);
            parametro.put("dir1", in1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getCon());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("REPORTE");
            jv.setVisible(true);
//            JasperPrintManager.printReport(jasperPrint, false); imprime

        } catch (Exception e) {
            System.out.println("Error al cargar reporte.");
        }

    }

    public void totalesdia(String bddVar1, String anio, String bddVar2, String mes, String bddVar3, String dia, String archivo) {
        try {
            JasperReport reporte = null;
            URL in1 = null;
            try {
                URL in = this.getClass().getResource(archivo);
                in1 = this.getClass().getResource("");
                reporte = (JasperReport) JRLoader.loadObject(in);
            } catch (JRException jr) {

            }
            Map parametro = new HashMap();
            parametro.put(bddVar1, anio);
            parametro.put(bddVar2, mes);
            parametro.put(bddVar3, dia);
            parametro.put("SUBREPORT_DIR", in1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getCon());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("FACTURA");
            jv.setVisible(true);
//            JasperPrintManager.printReport(jasperPrint, false); imprime

        } catch (Exception e) {
        }

    }

    public void Planificacion(String bddVar, String nvar, String archivo) {
        try {
            JasperReport reporte = null;
            try {
                URL in = this.getClass().getResource(archivo);
                reporte = (JasperReport) JRLoader.loadObject(in);
            } catch (JRException jr) {
                System.out.println("Error .");
            }
            Map parametro = new HashMap();
            parametro.put(bddVar, nvar);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getCon());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("");
            jv.setVisible(true);
//            JasperPrintManager.printReport(jasperPrint, false); imprime

        } catch (Exception e) {
        }

    }

    public void abrirManual() throws IOException {
          
            try {
                File path = new File(("MANUAL.pdf"));
                Desktop.getDesktop().open(path);
            } catch (Exception ex) {
                
            }
 
    }
//
//    public void abrirManual() {
//        if (Desktop.isDesktopSupported()) {
//            try {
//                URL in = this.getClass().getResource("Fish.pdf");
//                InputStream is = this.getClass().getResourceAsStream("Fish.pdf");
//                byte[] data = new byte[is.available()];
//                is.read(data);
//                is.close();
//                String tempFile = "file";
//                File temp = File.createTempFile(tempFile, ".pdf");
//                FileOutputStream fos = new FileOutputStream(temp);
//                fos.write(data);
//                fos.flush();
//                fos.close();
//                Desktop.getDesktop().open(temp);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                System.out.println("NO PDF READER INSTALLED");
//            }
//        }
//    }
}
