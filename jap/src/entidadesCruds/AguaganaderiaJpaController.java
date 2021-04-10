/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Aguaganaderia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuarios;
import entidades.Detallefacturaganaderia;
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
public class AguaganaderiaJpaController implements Serializable {

    public AguaganaderiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aguaganaderia aguaganaderia) {
        if (aguaganaderia.getDetallefacturaganaderiaCollection() == null) {
            aguaganaderia.setDetallefacturaganaderiaCollection(new ArrayList<Detallefacturaganaderia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios idusuario = aguaganaderia.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                aguaganaderia.setIdusuario(idusuario);
            }
            Collection<Detallefacturaganaderia> attachedDetallefacturaganaderiaCollection = new ArrayList<Detallefacturaganaderia>();
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach : aguaganaderia.getDetallefacturaganaderiaCollection()) {
                detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach = em.getReference(detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach.getClass(), detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach.getIddetallefacganaderia());
                attachedDetallefacturaganaderiaCollection.add(detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach);
            }
            aguaganaderia.setDetallefacturaganaderiaCollection(attachedDetallefacturaganaderiaCollection);
            em.persist(aguaganaderia);
            if (idusuario != null) {
                idusuario.getAguaganaderiaCollection().add(aguaganaderia);
                idusuario = em.merge(idusuario);
            }
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionDetallefacturaganaderia : aguaganaderia.getDetallefacturaganaderiaCollection()) {
                Aguaganaderia oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia = detallefacturaganaderiaCollectionDetallefacturaganaderia.getIdaguaganaderia();
                detallefacturaganaderiaCollectionDetallefacturaganaderia.setIdaguaganaderia(aguaganaderia);
                detallefacturaganaderiaCollectionDetallefacturaganaderia = em.merge(detallefacturaganaderiaCollectionDetallefacturaganaderia);
                if (oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia != null) {
                    oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderiaCollectionDetallefacturaganaderia);
                    oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia = em.merge(oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aguaganaderia aguaganaderia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aguaganaderia persistentAguaganaderia = em.find(Aguaganaderia.class, aguaganaderia.getIdaguaganaderia());
            Usuarios idusuarioOld = persistentAguaganaderia.getIdusuario();
            Usuarios idusuarioNew = aguaganaderia.getIdusuario();
            Collection<Detallefacturaganaderia> detallefacturaganaderiaCollectionOld = persistentAguaganaderia.getDetallefacturaganaderiaCollection();
            Collection<Detallefacturaganaderia> detallefacturaganaderiaCollectionNew = aguaganaderia.getDetallefacturaganaderiaCollection();
            List<String> illegalOrphanMessages = null;
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionOldDetallefacturaganaderia : detallefacturaganaderiaCollectionOld) {
                if (!detallefacturaganaderiaCollectionNew.contains(detallefacturaganaderiaCollectionOldDetallefacturaganaderia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefacturaganaderia " + detallefacturaganaderiaCollectionOldDetallefacturaganaderia + " since its idaguaganaderia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                aguaganaderia.setIdusuario(idusuarioNew);
            }
            Collection<Detallefacturaganaderia> attachedDetallefacturaganaderiaCollectionNew = new ArrayList<Detallefacturaganaderia>();
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach : detallefacturaganaderiaCollectionNew) {
                detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach = em.getReference(detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach.getClass(), detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach.getIddetallefacganaderia());
                attachedDetallefacturaganaderiaCollectionNew.add(detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach);
            }
            detallefacturaganaderiaCollectionNew = attachedDetallefacturaganaderiaCollectionNew;
            aguaganaderia.setDetallefacturaganaderiaCollection(detallefacturaganaderiaCollectionNew);
            aguaganaderia = em.merge(aguaganaderia);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getAguaganaderiaCollection().remove(aguaganaderia);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getAguaganaderiaCollection().add(aguaganaderia);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionNewDetallefacturaganaderia : detallefacturaganaderiaCollectionNew) {
                if (!detallefacturaganaderiaCollectionOld.contains(detallefacturaganaderiaCollectionNewDetallefacturaganaderia)) {
                    Aguaganaderia oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia = detallefacturaganaderiaCollectionNewDetallefacturaganaderia.getIdaguaganaderia();
                    detallefacturaganaderiaCollectionNewDetallefacturaganaderia.setIdaguaganaderia(aguaganaderia);
                    detallefacturaganaderiaCollectionNewDetallefacturaganaderia = em.merge(detallefacturaganaderiaCollectionNewDetallefacturaganaderia);
                    if (oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia != null && !oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia.equals(aguaganaderia)) {
                        oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderiaCollectionNewDetallefacturaganaderia);
                        oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia = em.merge(oldIdaguaganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aguaganaderia.getIdaguaganaderia();
                if (findAguaganaderia(id) == null) {
                    throw new NonexistentEntityException("The aguaganaderia with id " + id + " no longer exists.");
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
            Aguaganaderia aguaganaderia;
            try {
                aguaganaderia = em.getReference(Aguaganaderia.class, id);
                aguaganaderia.getIdaguaganaderia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aguaganaderia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detallefacturaganaderia> detallefacturaganaderiaCollectionOrphanCheck = aguaganaderia.getDetallefacturaganaderiaCollection();
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionOrphanCheckDetallefacturaganaderia : detallefacturaganaderiaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Aguaganaderia (" + aguaganaderia + ") cannot be destroyed since the Detallefacturaganaderia " + detallefacturaganaderiaCollectionOrphanCheckDetallefacturaganaderia + " in its detallefacturaganaderiaCollection field has a non-nullable idaguaganaderia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios idusuario = aguaganaderia.getIdusuario();
            if (idusuario != null) {
                idusuario.getAguaganaderiaCollection().remove(aguaganaderia);
                idusuario = em.merge(idusuario);
            }
            em.remove(aguaganaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aguaganaderia> findAguaganaderiaEntities() {
        return findAguaganaderiaEntities(true, -1, -1);
    }

    public List<Aguaganaderia> findAguaganaderiaEntities(int maxResults, int firstResult) {
        return findAguaganaderiaEntities(false, maxResults, firstResult);
    }

    private List<Aguaganaderia> findAguaganaderiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aguaganaderia.class));
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

    public Aguaganaderia findAguaganaderia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aguaganaderia.class, id);
        } finally {
            em.close();
        }
    }

    public int getAguaganaderiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aguaganaderia> rt = cq.from(Aguaganaderia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
