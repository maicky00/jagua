/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Controladores.ControlFormularios;
import Controladores.classusuario;

/**
 *
 * @author Marco
 */
public class FrmBusqueda extends javax.swing.JFrame {

    classusuario us = new classusuario();

    /**
     * Creates new form FrmBusqueda2
     */
    public FrmBusqueda() {
        initComponents();
        us.cargarTablaUsuario(jTable1);
        //setLocationRelativeTo(null);
    }
    ControlFormularios cf;

    String idUs = "";
    String ced = "";
    String nombres = "";
    String apodo = "";
    String direccion = "";
    String tel = "";
    String cel = "";
    String sector = "";
    String ref = "";
    String ob = "";

    //ventanas
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        btnOk = new org.edisoncor.gui.button.ButtonAero();
        btnOk1 = new org.edisoncor.gui.button.ButtonAero();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("id:");

        txtId.setText("jLabel5");

        btnOk.setBackground(new java.awt.Color(102, 0, 0));
        btnOk.setText("OK");
        btnOk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnOk1.setBackground(new java.awt.Color(102, 0, 0));
        btnOk1.setText("+");
        btnOk1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Agregar Nuevo Usuario:");

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane2.setViewportView(txtArea);

        jLabel11.setText("Datos del Usario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId)
                                .addGap(133, 133, 133)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                                .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtId)
                    .addComponent(jLabel5)
                    .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        try {
            int n = jTable1.getSelectedRow();

            idUs = jTable1.getValueAt(n, 0).toString();
            ced = jTable1.getValueAt(n, 1).toString();
            nombres = jTable1.getValueAt(n, 2).toString();
            apodo = jTable1.getValueAt(n, 3).toString();
            direccion = jTable1.getValueAt(n, 4).toString();
            tel = jTable1.getValueAt(n, 5).toString();
            cel = jTable1.getValueAt(n, 6).toString();
            sector = jTable1.getValueAt(n, 7).toString();
            ref = jTable1.getValueAt(n, 8).toString();
            ob = jTable1.getValueAt(n, 9).toString();

            txtArea.setText(
                    "Nro. Usuario: " + idUs
                    + "\nCedula: " + ced
                    + "\nNombre: " + nombres
                    + "\nApodo: " + apodo
                    + "\nDireccion: " + direccion
                    + "\nTelefono: " + tel
                    + "\nCelular: " + cel
                    + "\nSector: " + sector
                    + "\nReferencia: " + ref
                    + "\nObservacion: " + ob
            );

            txtId.setText(jTable1.getValueAt(n, 0).toString());

        } catch (Exception e) {
        }
        //medidor
        try {
            int n = jTable1.getSelectedRow();
            FrmMedidor.txtMedidor.setText(jTable1.getValueAt(n, 0).toString());
            FrmMedidor.txtUsuarioCed.setText(jTable1.getValueAt(n, 1).toString());
            FrmMedidor.txtNombre.setText(jTable1.getValueAt(n, 2).toString());
            FrmMedidor.txtUsuarioCed.setEnabled(false);
            FrmMedidor.lblIdUsuario.setText(jTable1.getValueAt(n, 0).toString());
            FrmMedidor.txtvalorConexion.setText("0");
            FrmMedidor.txtPagado.setText("0.0");
            FrmMedidor.txtSaldo.setText("0.0");
            //FrmMedidor.txtMedidor.setText(null);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        try {
            int n = jTable1.getSelectedRow();

            idUs = jTable1.getValueAt(n, 0).toString();
            ced = jTable1.getValueAt(n, 1).toString();
            nombres = jTable1.getValueAt(n, 2).toString();
            apodo = jTable1.getValueAt(n, 3).toString();
            direccion = jTable1.getValueAt(n, 4).toString();
            tel = jTable1.getValueAt(n, 5).toString();
            cel = jTable1.getValueAt(n, 6).toString();
            sector = jTable1.getValueAt(n, 7).toString();
            ref = jTable1.getValueAt(n, 8).toString();
            ob = jTable1.getValueAt(n, 9).toString();

            txtArea.setText(
                    "Nro. Usuario: " + idUs
                    + "\nCedula: " + ced
                    + "\nNombre: " + nombres
                    + "\nApodo: " + apodo
                    + "\nDireccion: " + direccion
                    + "\nTelefono: " + tel
                    + "\nCelular: " + cel
                    + "\nSector: " + sector
                    + "\nReferencia: " + ref
                    + "\nObservacion: " + ob
            );

            txtId.setText(jTable1.getValueAt(n, 0).toString());
//            txtCedula.setText(jTable1.getValueAt(n, 1).toString());
//            txtNombres.setText(jTable1.getValueAt(n, 2).toString());
//            txtApodo.setText(jTable1.getValueAt(n, 3).toString());
//            txtDireccion.setText(jTable1.getValueAt(n, 4).toString());
//            txtTelefono.setText(jTable1.getValueAt(n, 5).toString());
//            txtCelular.setText(jTable1.getValueAt(n, 6).toString());
//            txtSector.setText(jTable1.getValueAt(n, 7).toString());
//            txtReferencia.setText(jTable1.getValueAt(n, 8).toString());
//            txtObservacion.setText(jTable1.getValueAt(n, 9).toString());

        } catch (Exception e) {
        }
        //medidor
        try {
            int n = jTable1.getSelectedRow();
            FrmMedidor.txtMedidor.setText(jTable1.getValueAt(n, 0).toString());
            FrmMedidor.txtUsuarioCed.setText(jTable1.getValueAt(n, 1).toString());
            FrmMedidor.txtNombre.setText(jTable1.getValueAt(n, 2).toString());
            FrmMedidor.txtUsuarioCed.setEnabled(false);
            FrmMedidor.lblIdUsuario.setText(jTable1.getValueAt(n, 0).toString());
            FrmMedidor.txtvalorConexion.setText("0");
            //FrmMedidor.txtMedidor.setText(null);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jTable1KeyReleased

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed

        FrmUsuario us = new FrmUsuario();

        cf = new ControlFormularios();
        cf.ControlaInstancia(us);
        this.dispose();
    }//GEN-LAST:event_btnOk1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBusqueda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero btnOk;
    private org.edisoncor.gui.button.ButtonAero btnOk1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JLabel txtId;
    // End of variables declaration//GEN-END:variables
}
