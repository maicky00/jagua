/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Corte;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Medidor;
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
public class CorteJpaController implements Serializable {

    public CorteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Corte corte) {
        if (corte.getOtrospagosList() == null) {
            corte.setOtrospagosList(new ArrayList<Otrospagos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidor idmedidor = corte.getIdmedidor();
            if (idmedidor != null) {
                idmedidor = em.getReference(idmedidor.getClass(), idmedidor.getIdmedidor());
                corte.setIdmedidor(idmedidor);
            }
            List<Otrospagos> attachedOtrospagosList = new ArrayList<Otrospagos>();
            for (Otrospagos otrospagosListOtrospagosToAttach : corte.getOtrospagosList()) {
                otrospagosListOtrospagosToAttach = em.getReference(otrospagosListOtrospagosToAttach.getClass(), otrospagosListOtrospagosToAttach.getIdotpagos());
                attachedOtrospagosList.add(otrospagosListOtrospagosToAttach);
            }
            corte.setOtrospagosList(attachedOtrospagosList);
            em.persist(corte);
            if (idmedidor != null) {
                idmedidor.getCorteList().add(corte);
                idmedidor = em.merge(idmedidor);
            }
            for (Otrospagos otrospagosListOtrospagos : corte.getOtrospagosList()) {
                Corte oldIdcorteOfOtrospagosListOtrospagos = otrospagosListOtrospagos.getIdcorte();
                otrospagosListOtrospagos.setIdcorte(corte);
                otrospagosListOtrospagos = em.merge(otrospagosListOtrospagos);
                if (oldIdcorteOfOtrospagosListOtrospagos != null) {
                    oldIdcorteOfOtrospagosListOtrospagos.getOtrospagosList().remove(otrospagosListOtrospagos);
                    oldIdcorteOfOtrospagosListOtrospagos = em.merge(oldIdcorteOfOtrospagosListOtrospagos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Corte corte) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corte persistentCorte = em.find(Corte.class, corte.getIdcorte());
            Medidor idmedidorOld = persistentCorte.getIdmedidor();
            Medidor idmedidorNew = corte.getIdmedidor();
            List<Otrospagos> otrospagosListOld = persistentCorte.getOtrospagosList();
            List<Otrospagos> otrospagosListNew = corte.getOtrospagosList();
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                corte.setIdmedidor(idmedidorNew);
            }
            List<Otrospagos> attachedOtrospagosListNew = new ArrayList<Otrospagos>();
            for (Otrospagos otrospagosListNewOtrospagosToAttach : otrospagosListNew) {
                otrospagosListNewOtrospagosToAttach = em.getReference(otrospagosListNewOtrospagosToAttach.getClass(), otrospagosListNewOtrospagosToAttach.getIdotpagos());
                attachedOtrospagosListNew.add(otrospagosListNewOtrospagosToAttach);
            }
            otrospagosListNew = attachedOtrospagosListNew;
            corte.setOtrospagosList(otrospagosListNew);
            corte = em.merge(corte);
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getCorteList().remove(corte);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getCorteList().add(corte);
                idmedidorNew = em.merge(idmedidorNew);
            }
            for (Otrospagos otrospagosListOldOtrospagos : otrospagosListOld) {
                if (!otrospagosListNew.contains(otrospagosListOldOtrospagos)) {
                    otrospagosListOldOtrospagos.setIdcorte(null);
                    otrospagosListOldOtrospagos = em.merge(otrospagosListOldOtrospagos);
                }
            }
            for (Otrospagos otrospagosListNewOtrospagos : otrospagosListNew) {
                if (!otrospagosListOld.contains(otrospagosListNewOtrospagos)) {
                    Corte oldIdcorteOfOtrospagosListNewOtrospagos = otrospagosListNewOtrospagos.getIdcorte();
                    otrospagosListNewOtrospagos.setIdcorte(corte);
                    otrospagosListNewOtrospagos = em.merge(otrospagosListNewOtrospagos);
                    if (oldIdcorteOfOtrospagosListNewOtrospagos != null && !oldIdcorteOfOtrospagosListNewOtrospagos.equals(corte)) {
                        oldIdcorteOfOtrospagosListNewOtrospagos.getOtrospagosList().remove(otrospagosListNewOtrospagos);
                        oldIdcorteOfOtrospagosListNewOtrospagos = em.merge(oldIdcorteOfOtrospagosListNewOtrospagos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = corte.getIdcorte();
                if (findCorte(id) == null) {
                    throw new NonexistentEntityException("The corte with id " + id + " no longer exists.");
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
            Corte corte;
            try {
                corte = em.getReference(Corte.class, id);
                corte.getIdcorte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The corte with id " + id + " no longer exists.", enfe);
            }
            Medidor idmedidor = corte.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getCorteList().remove(corte);
                idmedidor = em.merge(idmedidor);
            }
            List<Otrospagos> otrospagosList = corte.getOtrospagosList();
            for (Otrospagos otrospagosListOtrospagos : otrospagosList) {
                otrospagosListOtrospagos.setIdcorte(null);
                otrospagosListOtrospagos = em.merge(otrospagosListOtrospagos);
            }
            em.remove(corte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Corte> findCorteEntities() {
        return findCorteEntities(true, -1, -1);
    }

    public List<Corte> findCorteEntities(int maxResults, int firstResult) {
        return findCorteEntities(false, maxResults, firstResult);
    }

    private List<Corte> findCorteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Corte.class));
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

    public Corte findCorte(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Corte.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Corte> rt = cq.from(Corte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
