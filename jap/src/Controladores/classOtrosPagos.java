    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Corte;
import entidades.Login;
import entidades.Otrospagos;
import entidadesCruds.OtrospagosJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JC-PC
 */
public class classOtrosPagos {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    private OtrospagosJpaController otrospagosJpacontrolador = new OtrospagosJpaController(emf);
    classCorte cc = new classCorte();
    DefaultTableModel modelo;

    public List<Otrospagos> getOtrospagos() {
        return otrospagosJpacontrolador.findOtrospagosEntities();
    }

    public void guardarOtrospagos(int idcorte, float derConx, float mulRecx, float interes, float total, int numFact, String usuarioActual, Date fechaPago) {
        try {

            Corte idOtpg = cc.corteJpacontrolador.findCorte(cc.buscarIdCorte(idcorte).getIdcorte());
            Otrospagos dat = new Otrospagos();
            dat.setIdcorte(idOtpg);
            dat.setDerconx(derConx);
            dat.setMulrecx(mulRecx);
            dat.setInteres(interes);
            dat.setTotal(total);
            dat.setNumfactura(numFact);
            dat.setUsuarioactual(usuarioActual);
            dat.setFechapago(fechaPago);
            otrospagosJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public void cargarTablaOtros(JTable tabla) {
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("id");
        modelo.addColumn("idcorte");
        modelo.addColumn("dercon");
        modelo.addColumn("mulrec");
        modelo.addColumn("interes");
        modelo.addColumn("total");
        modelo.addColumn("usuarioAct");
        for (Otrospagos o : getOtrospagos()) {

            fila[0] = o.getIdotpagos();
            fila[1] = o.getIdcorte().getIdcorte();
            fila[2] = o.getDerconx();
            fila[3] = o.getMulrecx();
            fila[4] = o.getInteres();
            fila[5] = o.getTotal();
            fila[6] = o.getUsuarioactual();

            modelo.addRow(fila);
        }
    }

    public boolean modificarOtrospagos(int idOtrospagos, int idcorte, float derConx, float mulRecx, float interes) {

        try {
            Otrospagos dat = otrospagosJpacontrolador.findOtrospagos(idOtrospagos);
            if (dat == null) {
                return false;
            }
            Corte idOtpg = cc.corteJpacontrolador.findCorte(cc.buscarIdCorte(idcorte).getIdcorte());
            dat.setIdcorte(idOtpg);
            dat.setDerconx(derConx);
            dat.setMulrecx(mulRecx);
            dat.setInteres(interes);
            otrospagosJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarOtrospagos(int idOtrospagos) throws IllegalOrphanException {

        try {
            otrospagosJpacontrolador.destroy(idOtrospagos);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Otrospagos buscarIdOtrospagos(int idOtrospagos) {

        for (Otrospagos dat : getOtrospagos()) {
            if (dat.getIdotpagos().equals(idOtrospagos)) {
                return dat;
            }
        }
        return null;
    }

    public int numFactura() {
        try {
            int j = 1;

            for (Otrospagos dat1 : getOtrospagos()) {
                if (dat1.getNumfactura() > j) {
                    j = dat1.getNumfactura();
                }
            }

            return j;

        } catch (Exception e) {
        }
        return 0;
    }
}
