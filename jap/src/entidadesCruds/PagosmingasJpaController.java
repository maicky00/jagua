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
import entidades.Mingas;
import entidades.Detallefactura;
import entidades.Pagosmingas;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class PagosmingasJpaController implements Serializable {

    public PagosmingasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagosmingas pagosmingas) {
        if (pagosmingas.getDetallefacturaList() == null) {
            pagosmingas.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mingas idminga = pagosmingas.getIdminga();
            if (idminga != null) {
                idminga = em.getReference(idminga.getClass(), idminga.getIdminga());
                pagosmingas.setIdminga(idminga);
            }
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : pagosmingas.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            pagosmingas.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(pagosmingas);
            if (idminga != null) {
                idminga.getPagosmingasList().add(pagosmingas);
                idminga = em.merge(idminga);
            }
            for (Detallefactura detallefacturaListDetallefactura : pagosmingas.getDetallefacturaList()) {
                Pagosmingas oldIdpagomingaOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getIdpagominga();
                detallefacturaListDetallefactura.setIdpagominga(pagosmingas);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldIdpagomingaOfDetallefacturaListDetallefactura != null) {
                    oldIdpagomingaOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldIdpagomingaOfDetallefacturaListDetallefactura = em.merge(oldIdpagomingaOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagosmingas pagosmingas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagosmingas persistentPagosmingas = em.find(Pagosmingas.class, pagosmingas.getIdpagominga());
            Mingas idmingaOld = persistentPagosmingas.getIdminga();
            Mingas idmingaNew = pagosmingas.getIdminga();
            List<Detallefactura> detallefacturaListOld = persistentPagosmingas.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = pagosmingas.getDetallefacturaList();
            if (idmingaNew != null) {
                idmingaNew = em.getReference(idmingaNew.getClass(), idmingaNew.getIdminga());
                pagosmingas.setIdminga(idmingaNew);
            }
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            pagosmingas.setDetallefacturaList(detallefacturaListNew);
            pagosmingas = em.merge(pagosmingas);
            if (idmingaOld != null && !idmingaOld.equals(idmingaNew)) {
                idmingaOld.getPagosmingasList().remove(pagosmingas);
                idmingaOld = em.merge(idmingaOld);
            }
            if (idmingaNew != null && !idmingaNew.equals(idmingaOld)) {
                idmingaNew.getPagosmingasList().add(pagosmingas);
                idmingaNew = em.merge(idmingaNew);
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    detallefacturaListOldDetallefactura.setIdpagominga(null);
                    detallefacturaListOldDetallefactura = em.merge(detallefacturaListOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Pagosmingas oldIdpagomingaOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getIdpagominga();
                    detallefacturaListNewDetallefactura.setIdpagominga(pagosmingas);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldIdpagomingaOfDetallefacturaListNewDetallefactura != null && !oldIdpagomingaOfDetallefacturaListNewDetallefactura.equals(pagosmingas)) {
                        oldIdpagomingaOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldIdpagomingaOfDetallefacturaListNewDetallefactura = em.merge(oldIdpagomingaOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagosmingas.getIdpagominga();
                if (findPagosmingas(id) == null) {
                    throw new NonexistentEntityException("The pagosmingas with id " + id + " no longer exists.");
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
            Pagosmingas pagosmingas;
            try {
                pagosmingas = em.getReference(Pagosmingas.class, id);
                pagosmingas.getIdpagominga();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagosmingas with id " + id + " no longer exists.", enfe);
            }
            Mingas idminga = pagosmingas.getIdminga();
            if (idminga != null) {
                idminga.getPagosmingasList().remove(pagosmingas);
                idminga = em.merge(idminga);
            }
            List<Detallefactura> detallefacturaList = pagosmingas.getDetallefacturaList();
            for (Detallefactura detallefacturaListDetallefactura : detallefacturaList) {
                detallefacturaListDetallefactura.setIdpagominga(null);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
            }
            em.remove(pagosmingas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagosmingas> findPagosmingasEntities() {
        return findPagosmingasEntities(true, -1, -1);
    }

    public List<Pagosmingas> findPagosmingasEntities(int maxResults, int firstResult) {
        return findPagosmingasEntities(false, maxResults, firstResult);
    }

    private List<Pagosmingas> findPagosmingasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagosmingas.class));
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

    public Pagosmingas findPagosmingas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagosmingas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosmingasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagosmingas> rt = cq.from(Pagosmingas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
