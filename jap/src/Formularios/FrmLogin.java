/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Controladores.classLogin;
import entidadesCruds.exceptions.IllegalOrphanException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class FrmLogin extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmLogin
     */
    classLogin cl = new classLogin();

    public FrmLogin() {
        initComponents();
        cl.cargarTablaLogin(jTable1);
    }

    private void mostrar() {

        txtnombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtUsuario.setEnabled(true);
        txtClave.setEnabled(true);
        txtcedula.setEnabled(true);

        btnGuardar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(true);
        comboTipo.setEnabled(true);
        comboEstado.setEnabled(true);
        comboTipo.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);

    }

    private void ocultar() {

        txtnombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtUsuario.setEnabled(false);
        txtClave.setEnabled(false);
        txtcedula.setEnabled(false);

        btnGuardar.setEnabled(false);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnCancelar.setEnabled(false);
        comboTipo.setEnabled(false);
        comboEstado.setEnabled(false);
        comboTipo.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
    }

    private void limpiar() {
        lblid.setText("");
        txtnombres.setText("");
        txtApellidos.setText("");
        txtUsuario.setText("");
        txtClave.setText("");
        txtcedula.setText("");

    }
    String nombres, apellidos, cedula,nombreUsuario, clave, tipo, estado;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTranslucido1 = new org.edisoncor.gui.panel.PanelTranslucido();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        lblid = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtClave = new javax.swing.JPasswordField();
        comboTipo = new org.edisoncor.gui.comboBox.ComboBoxRect();
        comboEstado = new org.edisoncor.gui.comboBox.ComboBoxRect();
        txtcedula = new javax.swing.JTextField();
        lblcedula = new javax.swing.JLabel();
        panelTranslucidoComplete21 = new org.edisoncor.gui.panel.PanelTranslucidoComplete2();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        panelReflect1 = new org.edisoncor.gui.panel.PanelReflect();
        btnNuevo = new org.edisoncor.gui.button.ButtonAero();
        btnGuardar = new org.edisoncor.gui.button.ButtonAero();
        btnEditar = new org.edisoncor.gui.button.ButtonAero();
        btnEliminar = new org.edisoncor.gui.button.ButtonAero();
        btnCancelar = new org.edisoncor.gui.button.ButtonAero();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FORMULARIO DE USUARIO DE SISTEMA");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("id:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Usuario:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clave");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipo");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Estado");

        txtnombres.setEnabled(false);

        txtApellidos.setEnabled(false);

        txtUsuario.setEnabled(false);

        txtClave.setEnabled(false);

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "ADMINISTRADOR", "INVITADO" }));
        comboTipo.setEnabled(false);
        comboTipo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "ACTIVO", "INACTIVO" }));
        comboEstado.setEnabled(false);
        comboEstado.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        txtcedula.setEnabled(false);

        lblcedula.setForeground(new java.awt.Color(255, 255, 255));
        lblcedula.setText("Cedula:");

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(lblcedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcedula)
                    .addComponent(txtnombres)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(txtClave)
                    .addComponent(comboTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

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

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Buscar:");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucidoComplete21Layout = new javax.swing.GroupLayout(panelTranslucidoComplete21);
        panelTranslucidoComplete21.setLayout(panelTranslucidoComplete21Layout);
        panelTranslucidoComplete21Layout.setHorizontalGroup(
            panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4)))
                .addContainerGap())
        );
        panelTranslucidoComplete21Layout.setVerticalGroup(
            panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNuevo.setBackground(new java.awt.Color(102, 0, 0));
        btnNuevo.setText("NUEVO");
        btnNuevo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(102, 0, 0));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setEnabled(false);
        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(102, 0, 0));
        btnEditar.setText("EDITAR");
        btnEditar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(102, 0, 0));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(102, 0, 0));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setEnabled(false);
        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelReflect1Layout = new javax.swing.GroupLayout(panelReflect1);
        panelReflect1.setLayout(panelReflect1Layout);
        panelReflect1Layout.setHorizontalGroup(
            panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReflect1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelReflect1Layout.setVerticalGroup(
            panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReflect1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReflect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTranslucidoComplete21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelReflect1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelReflect1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucidoComplete21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        nombres = txtnombres.getText();
        apellidos = txtApellidos.getText();
        cedula =txtcedula.getText();
        nombreUsuario = txtUsuario.getText();
        clave = txtClave.getText();
        tipo = comboTipo.getSelectedItem().toString();
        estado = comboEstado.getSelectedItem().toString();
        if (tipo.equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Seleccione un Tipo de Usuario", "Información", 1);

        } else if (estado.equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Seleccione el Estado del Usuario", "Información", 1);

        } else {
            int i = JOptionPane.showConfirmDialog(this, "Gguardar", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                cl.guardarLogin(nombres, apellidos,cedula, nombreUsuario, clave, tipo, estado);
                cl.cargarTablaLogin(jTable1);
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        mostrar();
        int id = Integer.valueOf(lblid.getText());
        nombres = txtnombres.getText();
        apellidos = txtApellidos.getText();
        nombreUsuario = txtUsuario.getText();
        clave = txtClave.getText();
        tipo = comboTipo.getSelectedItem().toString();
        estado = comboEstado.getSelectedItem().toString();
        cedula =txtcedula.getText();
        if (tipo.equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Seleccione un Tipo de Usuario", "Información", 1);

        } else if (estado.equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Seleccione el Estado del Usuario", "Información", 1);

        } else {
            int i = JOptionPane.showConfirmDialog(this, "Guardar", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                cl.modificarLogin(id, nombres, apellidos, cedula,nombreUsuario, clave, tipo, estado);
                cl.cargarTablaLogin(jTable1);
            }

        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            int i = JOptionPane.showConfirmDialog(this, "Desea Eliminar", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                int id = Integer.valueOf(lblid.getText());
                cl.eliminarLogin(id);
                cl.cargarTablaLogin(jTable1);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se Puede Eliminar", "Información", 1);

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        mostrar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        ocultar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        try {
            int n = jTable1.getSelectedRow();
            lblid.setText(jTable1.getValueAt(n, 0).toString());
            txtnombres.setText(jTable1.getValueAt(n, 1).toString());
            txtApellidos.setText(jTable1.getValueAt(n, 2).toString());
            txtcedula.setText(jTable1.getValueAt(n, 3).toString());
            txtUsuario.setText(jTable1.getValueAt(n, 4).toString());
            txtClave.setText(jTable1.getValueAt(n, 5).toString());
            comboTipo.setSelectedItem(jTable1.getValueAt(n, 6).toString());
            comboEstado.setSelectedItem(jTable1.getValueAt(n, 7).toString());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        try {
            int n = jTable1.getSelectedRow();
            lblid.setText(jTable1.getValueAt(n, 0).toString());
            txtnombres.setText(jTable1.getValueAt(n, 1).toString());
            txtApellidos.setText(jTable1.getValueAt(n, 2).toString());
            txtcedula.setText(jTable1.getValueAt(n, 3).toString());
            txtUsuario.setText(jTable1.getValueAt(n, 4).toString());
            txtClave.setText(jTable1.getValueAt(n, 5).toString());
            comboTipo.setSelectedItem(jTable1.getValueAt(n, 6).toString());
            comboEstado.setSelectedItem(jTable1.getValueAt(n, 7).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        try {
            cl.BuscarApe();
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_jTextField4KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero btnCancelar;
    private org.edisoncor.gui.button.ButtonAero btnEditar;
    private org.edisoncor.gui.button.ButtonAero btnEliminar;
    private org.edisoncor.gui.button.ButtonAero btnGuardar;
    private org.edisoncor.gui.button.ButtonAero btnNuevo;
    private org.edisoncor.gui.comboBox.ComboBoxRect comboEstado;
    private org.edisoncor.gui.comboBox.ComboBoxRect comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblcedula;
    private javax.swing.JLabel lblid;
    private org.edisoncor.gui.panel.PanelReflect panelReflect1;
    private org.edisoncor.gui.panel.PanelTranslucido panelTranslucido1;
    private org.edisoncor.gui.panel.PanelTranslucidoComplete2 panelTranslucidoComplete21;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtnombres;
    // End of variables declaration//GEN-END:variables
}
