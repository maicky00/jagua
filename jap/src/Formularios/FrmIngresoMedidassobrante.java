/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Controladores.ControlFormularios;
import Controladores.classAguasobrante;
import Controladores.classDetalleFactura;
import Controladores.classDetalleFacturasobrante;
import Controladores.classMedidor;
import static Formularios.frmDetalleFacturasobrante.tabla;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author JC-PC
 */
public class FrmIngresoMedidassobrante extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmIngresoMedidas
     */
    public FrmIngresoMedidassobrante() {
        initComponents();
        Dimension desktopSize = FrmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, 4);
    }
    classDetalleFacturasobrante cdf = new classDetalleFacturasobrante();
    frmDetalleFacturasobrante f;
    classAguasobrante cm = new classAguasobrante();
    ControlFormularios cf;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chsAnio = new com.toedter.calendar.JYearChooser();
        mchMes = new com.toedter.calendar.JMonthChooser();
        btnNuevo = new org.edisoncor.gui.button.ButtonNice();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Registro agua sobrante");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione Año y Mes a Registrar Los Consumos"));

        btnNuevo.setBackground(new java.awt.Color(0, 102, 255));
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("NUEVO REGISTRO");
        btnNuevo.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(chsAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mchMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mchMes, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(chsAnio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jCalendar1.setBackground(new java.awt.Color(255, 255, 255));
        jCalendar1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCalendar1.setTodayButtonVisible(true);
        jCalendar1.setWeekOfYearVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        try {
            int mes = mchMes.getMonth() + 1;
            String anioMes = chsAnio.getYear() + "-" + mes;
            f = new frmDetalleFacturasobrante();
            cf = new ControlFormularios();
            cf.ControlaInstancia(f);
            
            frmDetalleFacturasobrante.lblanio.setText(String.valueOf(chsAnio.getYear()));
            frmDetalleFacturasobrante.lblmes.setText(String.valueOf(mchMes.getMonth() + 1));
            f.setMaximum(true);
            //datos registrados el consumo
            cdf.cargarBusquedaAnioMes(frmDetalleFacturasobrante.jTable1, anioMes);
            //cdf.cargarBusquedaAnioMesDatos(anioMes);
            //datos sin registrar consumo
            cdf.cargarTablaMedidorDetalle(tabla, anioMes,cdf.listaMed(anioMes));

            this.dispose();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonNice btnNuevo;
    public static com.toedter.calendar.JYearChooser chsAnio;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JPanel jPanel1;
    public static com.toedter.calendar.JMonthChooser mchMes;
    // End of variables declaration//GEN-END:variables
}