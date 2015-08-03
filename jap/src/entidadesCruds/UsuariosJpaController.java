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
import entidades.Usuarios;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getMedidorList() == null) {
            usuarios.setMedidorList(new ArrayList<Medidor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Medidor> attachedMedidorList = new ArrayList<Medidor>();
            for (Medidor medidorListMedidorToAttach : usuarios.getMedidorList()) {
                medidorListMedidorToAttach = em.getReference(medidorListMedidorToAttach.getClass(), medidorListMedidorToAttach.getIdmedidor());
                attachedMedidorList.add(medidorListMedidorToAttach);
            }
            usuarios.setMedidorList(attachedMedidorList);
            em.persist(usuarios);
            for (Medidor medidorListMedidor : usuarios.getMedidorList()) {
                Usuarios oldIdusuarioOfMedidorListMedidor = medidorListMedidor.getIdusuario();
                medidorListMedidor.setIdusuario(usuarios);
                medidorListMedidor = em.merge(medidorListMedidor);
                if (oldIdusuarioOfMedidorListMedidor != null) {
                    oldIdusuarioOfMedidorListMedidor.getMedidorList().remove(medidorListMedidor);
                    oldIdusuarioOfMedidorListMedidor = em.merge(oldIdusuarioOfMedidorListMedidor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdusuario());
            List<Medidor> medidorListOld = persistentUsuarios.getMedidorList();
            List<Medidor> medidorListNew = usuarios.getMedidorList();
            List<Medidor> attachedMedidorListNew = new ArrayList<Medidor>();
            for (Medidor medidorListNewMedidorToAttach : medidorListNew) {
                medidorListNewMedidorToAttach = em.getReference(medidorListNewMedidorToAttach.getClass(), medidorListNewMedidorToAttach.getIdmedidor());
                attachedMedidorListNew.add(medidorListNewMedidorToAttach);
            }
            medidorListNew = attachedMedidorListNew;
            usuarios.setMedidorList(medidorListNew);
            usuarios = em.merge(usuarios);
            for (Medidor medidorListOldMedidor : medidorListOld) {
                if (!medidorListNew.contains(medidorListOldMedidor)) {
                    medidorListOldMedidor.setIdusuario(null);
                    medidorListOldMedidor = em.merge(medidorListOldMedidor);
                }
            }
            for (Medidor medidorListNewMedidor : medidorListNew) {
                if (!medidorListOld.contains(medidorListNewMedidor)) {
                    Usuarios oldIdusuarioOfMedidorListNewMedidor = medidorListNewMedidor.getIdusuario();
                    medidorListNewMedidor.setIdusuario(usuarios);
                    medidorListNewMedidor = em.merge(medidorListNewMedidor);
                    if (oldIdusuarioOfMedidorListNewMedidor != null && !oldIdusuarioOfMedidorListNewMedidor.equals(usuarios)) {
                        oldIdusuarioOfMedidorListNewMedidor.getMedidorList().remove(medidorListNewMedidor);
                        oldIdusuarioOfMedidorListNewMedidor = em.merge(oldIdusuarioOfMedidorListNewMedidor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdusuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<Medidor> medidorList = usuarios.getMedidorList();
            for (Medidor medidorListMedidor : medidorList) {
                medidorListMedidor.setIdusuario(null);
                medidorListMedidor = em.merge(medidorListMedidor);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
