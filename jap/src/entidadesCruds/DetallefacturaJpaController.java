/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Detallefactura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Tarifas;
import entidades.Medidor;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class DetallefacturaJpaController implements Serializable {

    public DetallefacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefactura detallefactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarifas idtarifas = detallefactura.getIdtarifas();
            if (idtarifas != null) {
                idtarifas = em.getReference(idtarifas.getClass(), idtarifas.getIdtarifas());
                detallefactura.setIdtarifas(idtarifas);
            }
            Medidor idmedidor = detallefactura.getIdmedidor();
            if (idmedidor != null) {
                idmedidor = em.getReference(idmedidor.getClass(), idmedidor.getIdmedidor());
                detallefactura.setIdmedidor(idmedidor);
            }
            em.persist(detallefactura);
            if (idtarifas != null) {
                idtarifas.getDetallefacturaList().add(detallefactura);
                idtarifas = em.merge(idtarifas);
            }
            if (idmedidor != null) {
                idmedidor.getDetallefacturaList().add(detallefactura);
                idmedidor = em.merge(idmedidor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefactura detallefactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getIddetallefac());
            Tarifas idtarifasOld = persistentDetallefactura.getIdtarifas();
            Tarifas idtarifasNew = detallefactura.getIdtarifas();
            Medidor idmedidorOld = persistentDetallefactura.getIdmedidor();
            Medidor idmedidorNew = detallefactura.getIdmedidor();
            if (idtarifasNew != null) {
                idtarifasNew = em.getReference(idtarifasNew.getClass(), idtarifasNew.getIdtarifas());
                detallefactura.setIdtarifas(idtarifasNew);
            }
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                detallefactura.setIdmedidor(idmedidorNew);
            }
            detallefactura = em.merge(detallefactura);
            if (idtarifasOld != null && !idtarifasOld.equals(idtarifasNew)) {
                idtarifasOld.getDetallefacturaList().remove(detallefactura);
                idtarifasOld = em.merge(idtarifasOld);
            }
            if (idtarifasNew != null && !idtarifasNew.equals(idtarifasOld)) {
                idtarifasNew.getDetallefacturaList().add(detallefactura);
                idtarifasNew = em.merge(idtarifasNew);
            }
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getDetallefacturaList().remove(detallefactura);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getDetallefacturaList().add(detallefactura);
                idmedidorNew = em.merge(idmedidorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefactura.getIddetallefac();
                if (findDetallefactura(id) == null) {
                    throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura detallefactura;
            try {
                detallefactura = em.getReference(Detallefactura.class, id);
                detallefactura.getIddetallefac();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            Tarifas idtarifas = detallefactura.getIdtarifas();
            if (idtarifas != null) {
                idtarifas.getDetallefacturaList().remove(detallefactura);
                idtarifas = em.merge(idtarifas);
            }
            Medidor idmedidor = detallefactura.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getDetallefacturaList().remove(detallefactura);
                idmedidor = em.merge(idmedidor);
            }
            em.remove(detallefactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefactura> findDetallefacturaEntities() {
        return findDetallefacturaEntities(true, -1, -1);
    }

    public List<Detallefactura> findDetallefacturaEntities(int maxResults, int firstResult) {
        return findDetallefacturaEntities(false, maxResults, firstResult);
    }

    private List<Detallefactura> findDetallefacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefactura.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detallefactura findDetallefactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefactura> rt = cq.from(Detallefactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
