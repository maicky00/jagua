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
import entidades.Corte;
import entidades.Detallefactura;
import entidades.Otrospagos;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class OtrospagosJpaController implements Serializable {

    public OtrospagosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Otrospagos otrospagos) {
        if (otrospagos.getDetallefacturaList() == null) {
            otrospagos.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corte idcorte = otrospagos.getIdcorte();
            if (idcorte != null) {
                idcorte = em.getReference(idcorte.getClass(), idcorte.getIdcorte());
                otrospagos.setIdcorte(idcorte);
            }
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : otrospagos.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            otrospagos.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(otrospagos);
            if (idcorte != null) {
                idcorte.getOtrospagosList().add(otrospagos);
                idcorte = em.merge(idcorte);
            }
            for (Detallefactura detallefacturaListDetallefactura : otrospagos.getDetallefacturaList()) {
                Otrospagos oldIdotpagosOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getIdotpagos();
                detallefacturaListDetallefactura.setIdotpagos(otrospagos);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldIdotpagosOfDetallefacturaListDetallefactura != null) {
                    oldIdotpagosOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldIdotpagosOfDetallefacturaListDetallefactura = em.merge(oldIdotpagosOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Otrospagos otrospagos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Otrospagos persistentOtrospagos = em.find(Otrospagos.class, otrospagos.getIdotpagos());
            Corte idcorteOld = persistentOtrospagos.getIdcorte();
            Corte idcorteNew = otrospagos.getIdcorte();
            List<Detallefactura> detallefacturaListOld = persistentOtrospagos.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = otrospagos.getDetallefacturaList();
            if (idcorteNew != null) {
                idcorteNew = em.getReference(idcorteNew.getClass(), idcorteNew.getIdcorte());
                otrospagos.setIdcorte(idcorteNew);
            }
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            otrospagos.setDetallefacturaList(detallefacturaListNew);
            otrospagos = em.merge(otrospagos);
            if (idcorteOld != null && !idcorteOld.equals(idcorteNew)) {
                idcorteOld.getOtrospagosList().remove(otrospagos);
                idcorteOld = em.merge(idcorteOld);
            }
            if (idcorteNew != null && !idcorteNew.equals(idcorteOld)) {
                idcorteNew.getOtrospagosList().add(otrospagos);
                idcorteNew = em.merge(idcorteNew);
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    detallefacturaListOldDetallefactura.setIdotpagos(null);
                    detallefacturaListOldDetallefactura = em.merge(detallefacturaListOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Otrospagos oldIdotpagosOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getIdotpagos();
                    detallefacturaListNewDetallefactura.setIdotpagos(otrospagos);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldIdotpagosOfDetallefacturaListNewDetallefactura != null && !oldIdotpagosOfDetallefacturaListNewDetallefactura.equals(otrospagos)) {
                        oldIdotpagosOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldIdotpagosOfDetallefacturaListNewDetallefactura = em.merge(oldIdotpagosOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = otrospagos.getIdotpagos();
                if (findOtrospagos(id) == null) {
                    throw new NonexistentEntityException("The otrospagos with id " + id + " no longer exists.");
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
            Otrospagos otrospagos;
            try {
                otrospagos = em.getReference(Otrospagos.class, id);
                otrospagos.getIdotpagos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The otrospagos with id " + id + " no longer exists.", enfe);
            }
            Corte idcorte = otrospagos.getIdcorte();
            if (idcorte != null) {
                idcorte.getOtrospagosList().remove(otrospagos);
                idcorte = em.merge(idcorte);
            }
            List<Detallefactura> detallefacturaList = otrospagos.getDetallefacturaList();
            for (Detallefactura detallefacturaListDetallefactura : detallefacturaList) {
                detallefacturaListDetallefactura.setIdotpagos(null);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
            }
            em.remove(otrospagos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Otrospagos> findOtrospagosEntities() {
        return findOtrospagosEntities(true, -1, -1);
    }

    public List<Otrospagos> findOtrospagosEntities(int maxResults, int firstResult) {
        return findOtrospagosEntities(false, maxResults, firstResult);
    }

    private List<Otrospagos> findOtrospagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Otrospagos.class));
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

    public Otrospagos findOtrospagos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Otrospagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getOtrospagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Otrospagos> rt = cq.from(Otrospagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
