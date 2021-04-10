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
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tech-Usuario
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
        if (corte.getOtrospagosCollection() == null) {
            corte.setOtrospagosCollection(new ArrayList<Otrospagos>());
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
            Collection<Otrospagos> attachedOtrospagosCollection = new ArrayList<Otrospagos>();
            for (Otrospagos otrospagosCollectionOtrospagosToAttach : corte.getOtrospagosCollection()) {
                otrospagosCollectionOtrospagosToAttach = em.getReference(otrospagosCollectionOtrospagosToAttach.getClass(), otrospagosCollectionOtrospagosToAttach.getIdotpagos());
                attachedOtrospagosCollection.add(otrospagosCollectionOtrospagosToAttach);
            }
            corte.setOtrospagosCollection(attachedOtrospagosCollection);
            em.persist(corte);
            if (idmedidor != null) {
                idmedidor.getCorteCollection().add(corte);
                idmedidor = em.merge(idmedidor);
            }
            for (Otrospagos otrospagosCollectionOtrospagos : corte.getOtrospagosCollection()) {
                Corte oldIdcorteOfOtrospagosCollectionOtrospagos = otrospagosCollectionOtrospagos.getIdcorte();
                otrospagosCollectionOtrospagos.setIdcorte(corte);
                otrospagosCollectionOtrospagos = em.merge(otrospagosCollectionOtrospagos);
                if (oldIdcorteOfOtrospagosCollectionOtrospagos != null) {
                    oldIdcorteOfOtrospagosCollectionOtrospagos.getOtrospagosCollection().remove(otrospagosCollectionOtrospagos);
                    oldIdcorteOfOtrospagosCollectionOtrospagos = em.merge(oldIdcorteOfOtrospagosCollectionOtrospagos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Corte corte) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corte persistentCorte = em.find(Corte.class, corte.getIdcorte());
            Medidor idmedidorOld = persistentCorte.getIdmedidor();
            Medidor idmedidorNew = corte.getIdmedidor();
            Collection<Otrospagos> otrospagosCollectionOld = persistentCorte.getOtrospagosCollection();
            Collection<Otrospagos> otrospagosCollectionNew = corte.getOtrospagosCollection();
            List<String> illegalOrphanMessages = null;
            for (Otrospagos otrospagosCollectionOldOtrospagos : otrospagosCollectionOld) {
                if (!otrospagosCollectionNew.contains(otrospagosCollectionOldOtrospagos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Otrospagos " + otrospagosCollectionOldOtrospagos + " since its idcorte field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                corte.setIdmedidor(idmedidorNew);
            }
            Collection<Otrospagos> attachedOtrospagosCollectionNew = new ArrayList<Otrospagos>();
            for (Otrospagos otrospagosCollectionNewOtrospagosToAttach : otrospagosCollectionNew) {
                otrospagosCollectionNewOtrospagosToAttach = em.getReference(otrospagosCollectionNewOtrospagosToAttach.getClass(), otrospagosCollectionNewOtrospagosToAttach.getIdotpagos());
                attachedOtrospagosCollectionNew.add(otrospagosCollectionNewOtrospagosToAttach);
            }
            otrospagosCollectionNew = attachedOtrospagosCollectionNew;
            corte.setOtrospagosCollection(otrospagosCollectionNew);
            corte = em.merge(corte);
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getCorteCollection().remove(corte);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getCorteCollection().add(corte);
                idmedidorNew = em.merge(idmedidorNew);
            }
            for (Otrospagos otrospagosCollectionNewOtrospagos : otrospagosCollectionNew) {
                if (!otrospagosCollectionOld.contains(otrospagosCollectionNewOtrospagos)) {
                    Corte oldIdcorteOfOtrospagosCollectionNewOtrospagos = otrospagosCollectionNewOtrospagos.getIdcorte();
                    otrospagosCollectionNewOtrospagos.setIdcorte(corte);
                    otrospagosCollectionNewOtrospagos = em.merge(otrospagosCollectionNewOtrospagos);
                    if (oldIdcorteOfOtrospagosCollectionNewOtrospagos != null && !oldIdcorteOfOtrospagosCollectionNewOtrospagos.equals(corte)) {
                        oldIdcorteOfOtrospagosCollectionNewOtrospagos.getOtrospagosCollection().remove(otrospagosCollectionNewOtrospagos);
                        oldIdcorteOfOtrospagosCollectionNewOtrospagos = em.merge(oldIdcorteOfOtrospagosCollectionNewOtrospagos);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Otrospagos> otrospagosCollectionOrphanCheck = corte.getOtrospagosCollection();
            for (Otrospagos otrospagosCollectionOrphanCheckOtrospagos : otrospagosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Corte (" + corte + ") cannot be destroyed since the Otrospagos " + otrospagosCollectionOrphanCheckOtrospagos + " in its otrospagosCollection field has a non-nullable idcorte field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medidor idmedidor = corte.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getCorteCollection().remove(corte);
                idmedidor = em.merge(idmedidor);
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
