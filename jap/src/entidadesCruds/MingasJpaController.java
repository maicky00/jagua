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
import entidades.Medidor;
import entidades.Mingas;
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
public class MingasJpaController implements Serializable {

    public MingasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mingas mingas) {
        if (mingas.getPagosmingasList() == null) {
            mingas.setPagosmingasList(new ArrayList<Pagosmingas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidor idmedidor = mingas.getIdmedidor();
            if (idmedidor != null) {
                idmedidor = em.getReference(idmedidor.getClass(), idmedidor.getIdmedidor());
                mingas.setIdmedidor(idmedidor);
            }
            List<Pagosmingas> attachedPagosmingasList = new ArrayList<Pagosmingas>();
            for (Pagosmingas pagosmingasListPagosmingasToAttach : mingas.getPagosmingasList()) {
                pagosmingasListPagosmingasToAttach = em.getReference(pagosmingasListPagosmingasToAttach.getClass(), pagosmingasListPagosmingasToAttach.getIdpagominga());
                attachedPagosmingasList.add(pagosmingasListPagosmingasToAttach);
            }
            mingas.setPagosmingasList(attachedPagosmingasList);
            em.persist(mingas);
            if (idmedidor != null) {
                idmedidor.getMingasList().add(mingas);
                idmedidor = em.merge(idmedidor);
            }
            for (Pagosmingas pagosmingasListPagosmingas : mingas.getPagosmingasList()) {
                Mingas oldIdmingaOfPagosmingasListPagosmingas = pagosmingasListPagosmingas.getIdminga();
                pagosmingasListPagosmingas.setIdminga(mingas);
                pagosmingasListPagosmingas = em.merge(pagosmingasListPagosmingas);
                if (oldIdmingaOfPagosmingasListPagosmingas != null) {
                    oldIdmingaOfPagosmingasListPagosmingas.getPagosmingasList().remove(pagosmingasListPagosmingas);
                    oldIdmingaOfPagosmingasListPagosmingas = em.merge(oldIdmingaOfPagosmingasListPagosmingas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mingas mingas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mingas persistentMingas = em.find(Mingas.class, mingas.getIdminga());
            Medidor idmedidorOld = persistentMingas.getIdmedidor();
            Medidor idmedidorNew = mingas.getIdmedidor();
            List<Pagosmingas> pagosmingasListOld = persistentMingas.getPagosmingasList();
            List<Pagosmingas> pagosmingasListNew = mingas.getPagosmingasList();
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                mingas.setIdmedidor(idmedidorNew);
            }
            List<Pagosmingas> attachedPagosmingasListNew = new ArrayList<Pagosmingas>();
            for (Pagosmingas pagosmingasListNewPagosmingasToAttach : pagosmingasListNew) {
                pagosmingasListNewPagosmingasToAttach = em.getReference(pagosmingasListNewPagosmingasToAttach.getClass(), pagosmingasListNewPagosmingasToAttach.getIdpagominga());
                attachedPagosmingasListNew.add(pagosmingasListNewPagosmingasToAttach);
            }
            pagosmingasListNew = attachedPagosmingasListNew;
            mingas.setPagosmingasList(pagosmingasListNew);
            mingas = em.merge(mingas);
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getMingasList().remove(mingas);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getMingasList().add(mingas);
                idmedidorNew = em.merge(idmedidorNew);
            }
            for (Pagosmingas pagosmingasListOldPagosmingas : pagosmingasListOld) {
                if (!pagosmingasListNew.contains(pagosmingasListOldPagosmingas)) {
                    pagosmingasListOldPagosmingas.setIdminga(null);
                    pagosmingasListOldPagosmingas = em.merge(pagosmingasListOldPagosmingas);
                }
            }
            for (Pagosmingas pagosmingasListNewPagosmingas : pagosmingasListNew) {
                if (!pagosmingasListOld.contains(pagosmingasListNewPagosmingas)) {
                    Mingas oldIdmingaOfPagosmingasListNewPagosmingas = pagosmingasListNewPagosmingas.getIdminga();
                    pagosmingasListNewPagosmingas.setIdminga(mingas);
                    pagosmingasListNewPagosmingas = em.merge(pagosmingasListNewPagosmingas);
                    if (oldIdmingaOfPagosmingasListNewPagosmingas != null && !oldIdmingaOfPagosmingasListNewPagosmingas.equals(mingas)) {
                        oldIdmingaOfPagosmingasListNewPagosmingas.getPagosmingasList().remove(pagosmingasListNewPagosmingas);
                        oldIdmingaOfPagosmingasListNewPagosmingas = em.merge(oldIdmingaOfPagosmingasListNewPagosmingas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mingas.getIdminga();
                if (findMingas(id) == null) {
                    throw new NonexistentEntityException("The mingas with id " + id + " no longer exists.");
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
            Mingas mingas;
            try {
                mingas = em.getReference(Mingas.class, id);
                mingas.getIdminga();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mingas with id " + id + " no longer exists.", enfe);
            }
            Medidor idmedidor = mingas.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getMingasList().remove(mingas);
                idmedidor = em.merge(idmedidor);
            }
            List<Pagosmingas> pagosmingasList = mingas.getPagosmingasList();
            for (Pagosmingas pagosmingasListPagosmingas : pagosmingasList) {
                pagosmingasListPagosmingas.setIdminga(null);
                pagosmingasListPagosmingas = em.merge(pagosmingasListPagosmingas);
            }
            em.remove(mingas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mingas> findMingasEntities() {
        return findMingasEntities(true, -1, -1);
    }

    public List<Mingas> findMingasEntities(int maxResults, int firstResult) {
        return findMingasEntities(false, maxResults, firstResult);
    }

    private List<Mingas> findMingasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mingas.class));
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

    public Mingas findMingas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mingas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMingasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mingas> rt = cq.from(Mingas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
