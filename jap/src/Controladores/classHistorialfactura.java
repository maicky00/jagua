/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Historialfactura;
import entidadesCruds.HistorialfacturaJpaController;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JC-PC
 */
public class classHistorialfactura {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    private HistorialfacturaJpaController historialfacturaJpacontrolador = new HistorialfacturaJpaController(emf);
    
    public List<Historialfactura> getHistorialfactura() {
        return historialfacturaJpacontrolador.findHistorialfacturaEntities();
    }
    
    public void guardarHistorial(int idus, int numfact, Date fecha) {
        
        try {
            Historialfactura lg = new Historialfactura();
            lg.setIdUsuario(idus);
            lg.setNumFact(numfact);
            lg.setFecha(fecha);
            
            historialfacturaJpacontrolador.create(lg);
            
        } catch (Exception e) {
        }
        
    }
    public  Historialfactura buscar(int id){
        for (Historialfactura col : getHistorialfactura()) {
            if (col.getId().equals(id)) {
                return col;
                
            }
        }
        return null;
    }
}
