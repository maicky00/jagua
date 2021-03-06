/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Controladores.classPlanificacion;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class FrmPlanificacion extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmPlanificacion
     */
    classPlanificacion cp = new classPlanificacion();

    public FrmPlanificacion() {
        initComponents();
        cp.cargarTablaPlanificacion(jTable1);
        Dimension desktopSize = FrmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, 4);
//       
    }

    private void mostrar() {
        btnNuevo1.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnEditar1.setEnabled(false);
        btnElimnar1.setEnabled(false);
        btnCancelar1.setEnabled(true);

        comboTipo.setEnabled(true);
        txtLugar.setEnabled(true);
        fecha.setEnabled(true);
        txtValor.setEnabled(true);
        txtDescripcion.setEnabled(true);

    }

    private void ocultar() {
        btnNuevo1.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnEditar1.setEnabled(true);
        btnElimnar1.setEnabled(true);
        btnCancelar1.setEnabled(false);

        comboTipo.setEnabled(false);
        txtLugar.setEnabled(false);
        fecha.setEnabled(false);
        txtValor.setEnabled(false);
        txtDescripcion.setEnabled(false);
    }

    private void limpiar() {
        comboTipo.setSelectedIndex(0);
        txtLugar.setText("");
        fecha.setToolTipText(null);
        txtValor.setText("");
        txtDescripcion.setText("");
        lblId.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboTipo = new org.edisoncor.gui.comboBox.ComboBoxRect();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        txtLugar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo1 = new org.edisoncor.gui.button.ButtonNice();
        btnGuardar = new org.edisoncor.gui.button.ButtonNice();
        btnEditar1 = new org.edisoncor.gui.button.ButtonNice();
        btnElimnar1 = new org.edisoncor.gui.button.ButtonNice();
        btnCancelar1 = new org.edisoncor.gui.button.ButtonNice();
        mensaje = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Planificacion");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("id:");

        jLabel3.setText("Tipo de Planificacion:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "MINGA", "ASAMBLEA" }));
        comboTipo.setEnabled(false);
        comboTipo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        jLabel4.setText("Lugar:");

        jLabel5.setText("Fecha:");

        fecha.setDateFormatString("yyyy/MM/dd");
        fecha.setEnabled(false);

        txtLugar.setEnabled(false);

        jLabel6.setText("Valor Multa:");

        txtValor.setEnabled(false);
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        jLabel7.setText("Descripcion:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setEnabled(false);
        jScrollPane1.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtValor)
                            .addComponent(comboTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .addComponent(txtLugar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevo1.setBackground(new java.awt.Color(0, 102, 255));
        btnNuevo1.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo1.setText("NUEVO");
        btnNuevo1.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.ROUND_LEFT);
        btnNuevo1.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 102, 255));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setEnabled(false);
        btnGuardar.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        btnGuardar.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar1.setBackground(new java.awt.Color(0, 102, 255));
        btnEditar1.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar1.setText("EDITAR");
        btnEditar1.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        btnEditar1.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        btnElimnar1.setBackground(new java.awt.Color(0, 102, 255));
        btnElimnar1.setForeground(new java.awt.Color(255, 255, 255));
        btnElimnar1.setText("ELIMINAR");
        btnElimnar1.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        btnElimnar1.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnElimnar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimnar1ActionPerformed(evt);
            }
        });

        btnCancelar1.setBackground(new java.awt.Color(0, 102, 255));
        btnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setText("CANCELAR");
        btnCancelar1.setEnabled(false);
        btnCancelar1.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.ROUND_RIGHT);
        btnCancelar1.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        mensaje.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnElimnar1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnElimnar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed

        ocultar();
        limpiar();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnElimnar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimnar1ActionPerformed
        try {
            int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                cp.eliminarPlanificacion(Integer.valueOf(lblId.getText()));
                cp.cargarTablaPlanificacion(jTable1);
                JOptionPane.showMessageDialog(null, "Eliminado");
                limpiar();
                ocultar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proceso no Realizado!.",
                "Error",JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnElimnar1ActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed

        mostrar();
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {

            String tipo = String.valueOf(comboTipo.getSelectedItem());
            String lugar = txtLugar.getText();
            String valor = txtValor.getText();
            String descripcion = txtDescripcion.getText();
            if (!comboTipo.getSelectedItem().toString().equals("Seleccione")) {
                if (!txtLugar.getText().equals("") && fecha.getDate() != null && !txtValor.getText().equals("") && !txtDescripcion.getText().equals("")) {
                    if (lblId.getText().equals("")) {
                        int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Registrar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (i == 0) {
                            cp.guardarPlanificacion(tipo, lugar, fecha.getDate(), Float.parseFloat(valor), descripcion);
                            cp.cargarTablaPlanificacion(jTable1);
                            limpiar();
                            ocultar();
                        }
                    } else if (!lblId.getText().equals("")) {
                        int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Modificar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (i == 0) {
                            int idPlan = Integer.valueOf(lblId.getText());
                            cp.modificarPlanificacion(idPlan, tipo, lugar, fecha.getDate(), Float.parseFloat(valor), descripcion);
                            cp.cargarTablaPlanificacion(jTable1);
                            limpiar();
                            ocultar();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos",
                "Error de Ingreso",
                JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de panificacion",
                "Error de Ingreso",
                JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed

        mostrar();
        limpiar();
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int n = jTable1.getSelectedRow();

            lblId.setText(jTable1.getValueAt(n, 0).toString());
            comboTipo.setSelectedItem(jTable1.getValueAt(n, 1).toString());
            txtLugar.setText(jTable1.getValueAt(n, 2).toString());

            //txtMedidor.setText(cu.buscarUsuarioRucCi(txtUsuarioCed.getText()).getIdusuario().toString());
            txtValor.setText(jTable1.getValueAt(n, 4).toString());

            txtDescripcion.setText(jTable1.getValueAt(n, 5).toString());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        char c = evt.getKeyChar();
        if (((c == ','))) {
            evt.consume();
        }
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();

            mensaje.setText("error de ingreso, ingrese digitos");
        } else {
            mensaje.setText("");
        }

    }//GEN-LAST:event_txtValorKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonNice btnCancelar1;
    private org.edisoncor.gui.button.ButtonNice btnEditar1;
    private org.edisoncor.gui.button.ButtonNice btnElimnar1;
    private org.edisoncor.gui.button.ButtonNice btnGuardar;
    private org.edisoncor.gui.button.ButtonNice btnNuevo1;
    private org.edisoncor.gui.comboBox.ComboBoxRect comboTipo;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtLugar;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
