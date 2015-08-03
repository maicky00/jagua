/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Detallefactura;
import entidades.Tarifas;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class TarifasJpaController implements Serializable {

    public TarifasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarifas tarifas) {
        if (tarifas.getDetallefacturaList() == null) {
            tarifas.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : tarifas.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            tarifas.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(tarifas);
            for (Detallefactura detallefacturaListDetallefactura : tarifas.getDetallefacturaList()) {
                Tarifas oldIdtarifasOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getIdtarifas();
                detallefacturaListDetallefactura.setIdtarifas(tarifas);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldIdtarifasOfDetallefacturaListDetallefactura != null) {
                    oldIdtarifasOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldIdtarifasOfDetallefacturaListDetallefactura = em.merge(oldIdtarifasOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarifas tarifas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarifas persistentTarifas = em.find(Tarifas.class, tarifas.getIdtarifas());
            List<Detallefactura> detallefacturaListOld = persistentTarifas.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = tarifas.getDetallefacturaList();
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            tarifas.setDetallefacturaList(detallefacturaListNew);
            tarifas = em.merge(tarifas);
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    detallefacturaListOldDetallefactura.setIdtarifas(null);
                    detallefacturaListOldDetallefactura = em.merge(detallefacturaListOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Tarifas oldIdtarifasOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getIdtarifas();
                    detallefacturaListNewDetallefactura.setIdtarifas(tarifas);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldIdtarifasOfDetallefacturaListNewDetallefactura != null && !oldIdtarifasOfDetallefacturaListNewDetallefactura.equals(tarifas)) {
                        oldIdtarifasOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldIdtarifasOfDetallefacturaListNewDetallefactura = em.merge(oldIdtarifasOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarifas.getIdtarifas();
                if (findTarifas(id) == null) {
                    throw new NonexistentEntityException("The tarifas with id " + id + " no longer exists.");
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
            Tarifas tarifas;
            try {
                tarifas = em.getReference(Tarifas.class, id);
                tarifas.getIdtarifas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarifas with id " + id + " no longer exists.", enfe);
            }
            List<Detallefactura> detallefacturaList = tarifas.getDetallefacturaList();
            for (Detallefactura detallefacturaListDetallefactura : detallefacturaList) {
                detallefacturaListDetallefactura.setIdtarifas(null);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
            }
            em.remove(tarifas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarifas> findTarifasEntities() {
        return findTarifasEntities(true, -1, -1);
    }

    public List<Tarifas> findTarifasEntities(int maxResults, int firstResult) {
        return findTarifasEntities(false, maxResults, firstResult);
    }

    private List<Tarifas> findTarifasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarifas.class));
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

    public Tarifas findTarifas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarifas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarifasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarifas> rt = cq.from(Tarifas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
