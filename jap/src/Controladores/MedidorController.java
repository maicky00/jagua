/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Medidor;
import entidadesCruds.MedidorJpaController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tech-Usuario
 */
public class MedidorController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public MedidorJpaController medidorJpacontrolador = new MedidorJpaController(emf);
    
    
    public List<Medidor> ListaOrdenada() {
        List<Medidor> lista = new ArrayList<>();
        for (Medidor m1 : medidorJpacontrolador.findMedidorEntities()) {
            lista.add(m1);
        }
        Collections.sort(lista, new Comparator<Medidor>() {

            @Override
            public int compare(Medidor o1, Medidor o2) {
                return o1.getNummedidor().compareTo(o2.getNummedidor());
            }
        });
        return lista;
    }
    
    public void cargarTablaMedidor(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        modelo.addColumn("#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("estado");
        modelo.addColumn("N° Medidor");
        
        modelo.addColumn("Modificar");
        modelo.addColumn("Eliminar");
        
        JButton btn_modificar = new JButton("Modificar");
        btn_modificar.setName("m");
        JButton btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setName("e");

        Object[] fila = new Object[9];
        for (Medidor u : ListaOrdenada()) {
            fila[0] = u.getIdmedidor();
            fila[1] = u.getIdusuario().getRucci();
            fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
            fila[3] = u.getIdusuario().getApadosn();
            fila[4] = u.getEstado();
            fila[5] = u.getNummedidor();
            
            fila[6] = btn_modificar;
            fila[7] = btn_eliminar;

            //fila[6] = u.getIdusuario().getIdusuario();
            //FrmMedidor.label.setText(u.getIdusuario().getIdusuario().toString());
            modelo.addRow(fila);
            tabla.setRowHeight(20);

        }
        tabla.setModel(modelo);
    }
}
