/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Controladores.ControlFormularios;
import Controladores.classAsistencia;
import Controladores.classCorte;
import Controladores.classDetalleFactura;
import Controladores.classFactura;
import Controladores.classHistorialfactura;
import Controladores.classInstitucion;
import Controladores.classMedidor;
import Controladores.classOtrosConceptos;
import Controladores.classOtrosPagos;
import Controladores.classPagosNuevoMed;
import Controladores.classusuario;
import entidades.Corte;
import entidades.Detallefactura;
import entidades.Facturas;
import entidades.Medidor;
import jap.ReportesControlador;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Marco
 */
public class FrmFactura extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmFactura
     */
    classDetalleFactura cdf = new classDetalleFactura();
    classFactura cft = new classFactura();
    classInstitucion ci = new classInstitucion();
    classAsistencia ca = new classAsistencia();
    classOtrosPagos co = new classOtrosPagos();
    classCorte cc = new classCorte();
    classOtrosConceptos coc = new classOtrosConceptos();
    classHistorialfactura chf = new classHistorialfactura();
    
    Medidor medidor=new Medidor();
    Corte corte=new Corte();
    Detallefactura detallefactura = new Detallefactura();

    public FrmFactura() {
        initComponents();
        ci = new classInstitucion();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        cargarInf();

        txtIdCorte.setVisible(false);
        txtMultaReconexion.setVisible(false);
        jLabel9.setVisible(false);
        comboPagos.setVisible(false);

    }
    classPagosNuevoMed cpnm = new classPagosNuevoMed();
    classMedidor cm = new classMedidor();

    BufferedImage img;
    ReportesControlador b = new ReportesControlador();

    public void cargarInf() {
        Dimension desktopSize = FrmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, 4);
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String hoy = formato.format(fechaActual);
        lblfecha.setText(hoy);
        lblInstitucion.setText(ci.buscarIdInstitucion(1).getNombreinst());

        byte[] data = ci.buscarIdInstitucion(1).getLogo();
        if (data != null) {
            try {
                img = ImageIO.read(new ByteArrayInputStream(data));
            } catch (IOException ex) {

            }
            jLabel4.setIcon(ajustarImagen(img));
            jLabel4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        } else {
            jLabel4.setIcon(null);
        }
    }

    public void limpiar() {
        txtMultaReconexion.setVisible(false);
        txtIdCorte.setVisible(false);
        txtMultaReconexion.setVisible(false);
        txtIdCorte.setText("0");
        lbldescNuevoMed.setText("");
        comboPagos.removeAllItems();
        txtTotalgeneral.setText("");
        jLabel2.setText("");
        lblGrafico.setIcon(null);
        txtRuc.setText("");
        txtCliente.setText("");
        txtdireccion.setText("");
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            for (int j = 0; j < 9; j++) {
                jTable1.setValueAt("", i, j);
            }
        }
        for (int i = 0; i < tabla2.getRowCount(); i++) {
            tabla2.setValueAt("", i, 1);
        }
        tabla2.setValueAt("0.0", 2, 1);
    }

    private ImageIcon ajustarImagen(BufferedImage ico) {
        ImageIcon tmpIconAux = new ImageIcon(ico);
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(97, 97, Image.SCALE_DEFAULT));
        return tmpIcon;
    }
    ControlFormularios cf;

    //ventana
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblInstitucion = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblInstitucion1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtnumMedidor = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblfecha = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        lblGrafico = new javax.swing.JLabel();
        txtTotalgeneral = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbldescNuevoMed = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboPagos = new javax.swing.JComboBox();
        txtMultaReconexion = new javax.swing.JLabel();
        txtIdCorte = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/Globe.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblInstitucion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblInstitucion.setForeground(new java.awt.Color(0, 0, 153));
        lblInstitucion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstitucion.setText("jLabel4");

        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblInstitucion1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblInstitucion1.setForeground(new java.awt.Color(0, 0, 153));
        lblInstitucion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstitucion1.setText("INGRESO DE COBRO MENSUAL");

        jLabel10.setText("FACTURA:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInstitucion, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                    .addComponent(lblInstitucion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel10))
                                    .addComponent(lblInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblInstitucion1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("FECHA EMISIÓN:");

        jLabel5.setText("CLIENTE:");

        jLabel6.setText("DIRECCIÓN:");

        txtCliente.setEditable(false);

        txtdireccion.setEditable(false);

        jLabel7.setText("RUC / C.I.:");

        jLabel8.setText("MEDIDOR:");

        txtRuc.setEditable(false);
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        txtnumMedidor.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        txtnumMedidor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtnumMedidor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnumMedidorKeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/door_in.png"))); // NOI18N
        jButton1.setText("OK");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblfecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblfecha.setText("Hora");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente)
                    .addComponent(txtdireccion)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtnumMedidor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtnumMedidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "FECHA CORRESPONDIENTE", "MEDIDA DE INICIO", "MEDIDA FINAL", "CONSUMO", "EXCEDIDO M3", "TARIFA POR EXCESO", "BASICO + ALCANTARILLADO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        tabla2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"SUBTOTAL", "0.0"},
                {"IVA 12%", "0.0"},
                {"TOTAL POR CONSUMO", "0.0"}
            },
            new String [] {
                " DESCRIPCION", " VALOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla2.setAutoscrolls(false);
        tabla2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(tabla2);
        if (tabla2.getColumnModel().getColumnCount() > 0) {
            tabla2.getColumnModel().getColumn(0).setResizable(false);
            tabla2.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rep.png"))); // NOI18N
        jButton2.setText("RALIZAR PAGO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblGrafico.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        txtTotalgeneral.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("TOTAL A PAGAR:");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Otros Pagos"));
        jPanel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbldescNuevoMed.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Cantidad a Pagar ");

        comboPagos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        comboPagos.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione"));
        comboPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPagosActionPerformed(evt);
            }
        });

        txtMultaReconexion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMultaReconexion.setText("MULTA POR RECONEXION:");

        txtIdCorte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIdCorte.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMultaReconexion)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdCorte)
                        .addGap(0, 128, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(10, 10, 10)
                                .addComponent(comboPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE))
                            .addComponent(lbldescNuevoMed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbldescNuevoMed, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(comboPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMultaReconexion)
                    .addComponent(txtIdCorte))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtTotalgeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTotalgeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cd_delete.png"))); // NOI18N
        jButton3.setToolTipText("Eliminar Ultimo Mes Del Pago");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton3)
                        .addContainerGap(356, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped

        if (txtRuc.getText().equals("")) {
            FrmBusqueda bu = new FrmBusqueda();
            bu.setVisible(true);
        }
    }//GEN-LAST:event_txtRucKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        try {

            limpiar();
            int numMed = Integer.valueOf(txtnumMedidor.getText());
            
            medidor = cm.buscarMedidorNumM(numMed);
            int multasporpagar = ca.buscarMultaPagado(numMed);
            corte = cc.buscarMedNum(numMed);
            detallefactura = cdf.buscarNumMedDetallefactura(numMed);
            boolean verificarcortes = cc.verificarOtrPagos(numMed);
            
            if (medidor.getEstado().equals("INACTIVO")) {
                JOptionPane.showMessageDialog(null, "Usuario INACTIVO!.",
                        "INFORMACION", JOptionPane.ERROR_MESSAGE);

            } else {
                //multas por pagar - formulario
                if (multasporpagar > 0) {
                    int i = JOptionPane.showConfirmDialog(this, "¿Tiene Multas Pendientes\n Ver Detalles?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //abrir formulario pagar
                        FrmPagosAsistemcia pago = new FrmPagosAsistemcia();
                        cf = new ControlFormularios();
                        cf.ControlaInstancia(pago);
                        FrmPagosAsistemcia.txtnumMedidor.setText(txtnumMedidor.getText());
                        pago.pagar();
                    }
                }
                //buscar multa por cortes
                if (verificarcortes == true) {
                    txtIdCorte.setVisible(true);
                    txtMultaReconexion.setVisible(true);
                    txtMultaReconexion.setText(corte.getObservacion());
                    txtIdCorte.setText(corte.getMulta().toString());
                }
                if (medidor.getSaldo() > 0) {
                    comboPagos.removeAllItems();
                    comboPagos.setVisible(true);
                    jLabel9.setVisible(true);
                    for (int i = 0; i < medidor.getSaldo() + 1; i++) {
                        comboPagos.addItem(i + ".0");
                    }
                    lbldescNuevoMed.setText("Cuotas Pagadas: " + cpnm.numCuotas(medidor.getIdmedidor()) + " Saldo Faltantate por nueva Conexion: "
                            + medidor.getSaldo().toString());
//                  txtPagoNuevoMed.setText(cm.buscarMedidorNumM(Integer.valueOf(txtnumMedidor.getText())).getSaldo().toString());
                } else {
                    lbldescNuevoMed.setText("");
                    comboPagos.setVisible(false);
                    jLabel9.setVisible(false);
                    comboPagos.removeAllItems();
                    comboPagos.addItem(0);
                }
                if (detallefactura.equals(null)) {
                } else {
                    jLabel2.setText(String.valueOf(cft.numFactura() + 1));
                    String ruc = detallefactura.getIdmedidor().getIdusuario().getRucci();
                    String client = detallefactura.getIdmedidor().getIdusuario().getPrimerapellido() + " "
                            + detallefactura.getIdmedidor().getIdusuario().getSegundoapellido() + " "
                            + detallefactura.getIdmedidor().getIdusuario().getPrimernombre() + " "
                            + detallefactura.getIdmedidor().getIdusuario().getSegundonombre() + " ";
                    String direccioN = detallefactura.getIdmedidor().getIdusuario().getDireccion();
                    txtRuc.setText(ruc);
                    txtCliente.setText(client);
                    cdf.tablaDetalles(jTable1, numMed);
                    txtdireccion.setText(direccioN);
                    cdf.graficador(medidor.getIdmedidor());

                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String usActual = FrmPrincipal.menuUsuarioActual.getText();

        int i = JOptionPane.showConfirmDialog(this, "¿REAlIZAR TRANSACCION?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (i == 0) {

            try {
                int numFact = cft.numFactura() + 1;
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaActual = formato.parse(lblfecha.getText());

                if (Float.valueOf(txtIdCorte.getText()) > 0) {
                    int numM = Integer.valueOf(txtnumMedidor.getText());
                    float derCon = 0;
                    float mulRec = 0;
                    if (cc.buscarMedNum(numM).getCorte().equals("SI")) {
                        mulRec = Float.valueOf(txtIdCorte.getText());
                        derCon = 0;
                    } else if (cc.buscarMedNum(numM).getCorte().equals("NO")) {
                        mulRec = 0;
                        derCon = Float.valueOf(txtIdCorte.getText());
                    }
                    int idotr = cc.buscarMedNum(Integer.valueOf(txtnumMedidor.getText())).getIdcorte();
                    co.guardarOtrospagos(idotr, derCon, mulRec, 0, Float.valueOf(txtIdCorte.getText()), numFact, usActual, fechaActual);
//                    cm.modificarEstado(cm.buscarMedidorNumM(Integer.valueOf(txtnumMedidor.getText())).getIdmedidor(), "ACTIVO");
                    cc.modificarPago(idotr, "SI");
                }
                if (Float.valueOf(comboPagos.getSelectedItem().toString()) > 0) {
                    float r = cm.buscarMedidorNumM(Integer.valueOf(txtnumMedidor.getText())).getSaldo() - Float.valueOf(comboPagos.getSelectedItem().toString());
                    cpnm.guardarPagosnuevomed(Integer.valueOf(txtnumMedidor.getText()), "Pago Por Coneccion", Float.valueOf(comboPagos.getSelectedItem().toString()), fechaActual, numFact);
                    cm.modificarValorConexion(Integer.valueOf(txtnumMedidor.getText()), r);
                }
                if (Float.valueOf(tabla2.getValueAt(2, 1).toString()) > 0) {
                    float subtotal = Float.valueOf(tabla2.getValueAt(0, 1).toString());
                    float iva = Float.valueOf(tabla2.getValueAt(1, 1).toString());
                    float total = Float.valueOf(tabla2.getValueAt(2, 1).toString());

                    List<Facturas> tableData = cft.getTableFacturas(jTable1, numFact, fechaActual, subtotal, iva, total, usActual);
                    cft.IngresarlistFact(tableData);
                }

                if (Float.valueOf(txtTotalgeneral.getText()) > 0) {
                    chf.guardarHistorial(cm.buscarMedidorNumM(Integer.valueOf(txtnumMedidor.getText())).getIdusuario().getIdusuario(), numFact, fechaActual);
                    JOptionPane.showMessageDialog(null, "Pago Realizado Corectamente", "Información", 1);
                    String idr = "" + cm.buscarMedidorNumM(Integer.valueOf(txtnumMedidor.getText())).getIdusuario().getIdusuario();
                    b.factura("id", idr, "numfact", String.valueOf(numFact), "verfacturageneral.jasper", "Consumos " + lblfecha.getText(), "Num.Med " + txtnumMedidor.getText() + " Fact." + numFact,txtnumMedidor.getText());
//                    b.facturaImprimir("id", idr, "numfact", String.valueOf(numFact), "facturageneral.jasper");

                } else {
                    JOptionPane.showMessageDialog(null, "No hay Datos", "Información", 1);
                }
                limpiar();
                txtnumMedidor.setText("");
//            cft.getTableFacturas(jTable1, numFact, fecha, subtotal, iva, total, usActual);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error En la Transaccion",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int n = jTable1.getSelectedRow();
            if (n == jTable1.getRowCount() - 1) {
                modelo.removeRow(n);
                float subtotal = 0;
                float total = 0;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    subtotal = Float.valueOf(jTable1.getValueAt(i, 8).toString()) + subtotal;
                }
                tabla2.setValueAt(subtotal, 0, 1);
                float recon = Float.valueOf(txtIdCorte.getText());
                tabla2.setValueAt(subtotal * Float.valueOf(tabla2.getValueAt(1, 0).toString()), 1, 1);
                tabla2.setValueAt(subtotal + Float.valueOf(tabla2.getValueAt(1, 1).toString()), 2, 1);
                float tg = Float.valueOf(tabla2.getValueAt(2, 1).toString())
                        + Float.valueOf(comboPagos.getSelectedItem().toString()) + recon;

                txtTotalgeneral.setText(String.valueOf(tg));
            } else {
                JOptionPane.showMessageDialog(null, "No se puede Eliminar\n Debe cancelar ", "Información", 1);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPagosActionPerformed
        // TODO add your handling code here:
        try {
            float recon = Float.valueOf(txtIdCorte.getText());
            float cons = Float.valueOf(tabla2.getValueAt(2, 1).toString());
            float conex = Float.valueOf(comboPagos.getSelectedItem().toString());
            float r = cons + conex + recon;
            txtTotalgeneral.setText(String.valueOf(r));
        } catch (Exception e) {
        }

    }//GEN-LAST:event_comboPagosActionPerformed

    private void txtnumMedidorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumMedidorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            try {

                limpiar();
                int numMed = Integer.valueOf(txtnumMedidor.getText());

                medidor = cm.buscarMedidorNumM(numMed);
                int multasporpagar = ca.buscarMultaPagado(numMed);
                corte = cc.buscarMedNum(numMed);
                detallefactura = cdf.buscarNumMedDetallefactura(numMed);
                boolean verificarcortes = cc.verificarOtrPagos(numMed);

                if (medidor.getEstado().equals("INACTIVO")) {
                    JOptionPane.showMessageDialog(null, "Usuario INACTIVO!.",
                            "INFORMACION", JOptionPane.ERROR_MESSAGE);

                } else {
                    //multas por pagar - formulario
                    if (multasporpagar > 0) {
                        int i = JOptionPane.showConfirmDialog(this, "¿Tiene Multas Pendientes\n Ver Detalles?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (i == 0) {
                            //abrir formulario pagar
                            FrmPagosAsistemcia pago = new FrmPagosAsistemcia();
                            cf = new ControlFormularios();
                            cf.ControlaInstancia(pago);
                            FrmPagosAsistemcia.txtnumMedidor.setText(txtnumMedidor.getText());
                            pago.pagar();
                        }
                    }
                    //buscar multa por cortes
                    if (verificarcortes == true) {
                        txtIdCorte.setVisible(true);
                        txtMultaReconexion.setVisible(true);
                        txtMultaReconexion.setText(corte.getObservacion());
                        txtIdCorte.setText(corte.getMulta().toString());
                    }
                    if (medidor.getSaldo() > 0) {
                        comboPagos.removeAllItems();
                        comboPagos.setVisible(true);
                        jLabel9.setVisible(true);
                        for (int i = 0; i < medidor.getSaldo() + 1; i++) {
                            comboPagos.addItem(i + ".0");
                        }
                        lbldescNuevoMed.setText("Cuotas Pagadas: " + cpnm.numCuotas(medidor.getIdmedidor()) + " Saldo Faltantate por nueva Conexion: "
                                + medidor.getSaldo().toString());
//                  txtPagoNuevoMed.setText(cm.buscarMedidorNumM(Integer.valueOf(txtnumMedidor.getText())).getSaldo().toString());
                    } else {
                        lbldescNuevoMed.setText("");
                        comboPagos.setVisible(false);
                        jLabel9.setVisible(false);
                        comboPagos.removeAllItems();
                        comboPagos.addItem(0);
                    }
                    if (detallefactura.equals(null)) {
                    } else {
                        jLabel2.setText(String.valueOf(cft.numFactura() + 1));
                        String ruc = detallefactura.getIdmedidor().getIdusuario().getRucci();
                        String client = detallefactura.getIdmedidor().getIdusuario().getPrimerapellido() + " "
                                + detallefactura.getIdmedidor().getIdusuario().getSegundoapellido() + " "
                                + detallefactura.getIdmedidor().getIdusuario().getPrimernombre() + " "
                                + detallefactura.getIdmedidor().getIdusuario().getSegundonombre() + " ";
                        String direccioN = detallefactura.getIdmedidor().getIdusuario().getDireccion();
                        txtRuc.setText(ruc);
                        txtCliente.setText(client);
                        cdf.tablaDetalles(jTable1, numMed);
                        txtdireccion.setText(direccioN);
                        cdf.graficador(medidor.getIdmedidor());

                    }
                }
            } catch (Exception e) {
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }//GEN-LAST:event_txtnumMedidorKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox comboPagos;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JLabel lblGrafico;
    private javax.swing.JLabel lblInstitucion;
    private javax.swing.JLabel lblInstitucion1;
    public static javax.swing.JLabel lbldescNuevoMed;
    public static javax.swing.JLabel lblfecha;
    public static javax.swing.JTable tabla2;
    public static javax.swing.JTextField txtCliente;
    public static javax.swing.JLabel txtIdCorte;
    public static javax.swing.JLabel txtMultaReconexion;
    public static javax.swing.JTextField txtRuc;
    public static javax.swing.JTextField txtTotalgeneral;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtnumMedidor;
    // End of variables declaration//GEN-END:variables
}
