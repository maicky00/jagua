/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jap;

import Controladores.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
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

    public ReportesControlador() {
        con = new Conexion();
    }

    public static void main(String[] args) {
        ReportesControlador b = new ReportesControlador();
        b.factura("numfact","1","factura.jasper");
    }

    public void reporte(String archivo) {
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

        } catch (Exception e) {
            System.out.println("Error al cargar reporte.");
        }

    }

    public void factura(String bddVar, String numfact, String archivo) {
        try {
            JasperReport reporte = null;
            try {
                URL in = this.getClass().getResource(archivo);
                reporte = (JasperReport) JRLoader.loadObject(in);
            } catch (JRException jr) {

            }
            Map parametro = new HashMap();
            parametro.put(bddVar, numfact);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getCon());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("FACTURA");
            jv.setVisible(true);
//            JasperPrintManager.printReport(jasperPrint, false); imprime

        } catch (Exception e) {
        }

    }
}
