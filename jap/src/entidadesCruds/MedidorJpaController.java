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
import entidades.Usuarios;
import entidades.Corte;
import java.util.ArrayList;
import java.util.List;
import entidades.Detallefactura;
import entidades.Medidor;
import entidades.Mingas;
import entidadesCruds.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class MedidorJpaController implements Serializable {

    public MedidorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medidor medidor) {
        if (medidor.getCorteList() == null) {
            medidor.setCorteList(new ArrayList<Corte>());
        }
        if (medidor.getDetallefacturaList() == null) {
            medidor.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        if (medidor.getMingasList() == null) {
            medidor.setMingasList(new ArrayList<Mingas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios idusuario = medidor.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                medidor.setIdusuario(idusuario);
            }
            List<Corte> attachedCorteList = new ArrayList<Corte>();
            for (Corte corteListCorteToAttach : medidor.getCorteList()) {
                corteListCorteToAttach = em.getReference(corteListCorteToAttach.getClass(), corteListCorteToAttach.getIdcorte());
                attachedCorteList.add(corteListCorteToAttach);
            }
            medidor.setCorteList(attachedCorteList);
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : medidor.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            medidor.setDetallefacturaList(attachedDetallefacturaList);
            List<Mingas> attachedMingasList = new ArrayList<Mingas>();
            for (Mingas mingasListMingasToAttach : medidor.getMingasList()) {
                mingasListMingasToAttach = em.getReference(mingasListMingasToAttach.getClass(), mingasListMingasToAttach.getIdminga());
                attachedMingasList.add(mingasListMingasToAttach);
            }
            medidor.setMingasList(attachedMingasList);
            em.persist(medidor);
            if (idusuario != null) {
                idusuario.getMedidorList().add(medidor);
                idusuario = em.merge(idusuario);
            }
            for (Corte corteListCorte : medidor.getCorteList()) {
                Medidor oldIdmedidorOfCorteListCorte = corteListCorte.getIdmedidor();
                corteListCorte.setIdmedidor(medidor);
                corteListCorte = em.merge(corteListCorte);
                if (oldIdmedidorOfCorteListCorte != null) {
                    oldIdmedidorOfCorteListCorte.getCorteList().remove(corteListCorte);
                    oldIdmedidorOfCorteListCorte = em.merge(oldIdmedidorOfCorteListCorte);
                }
            }
            for (Detallefactura detallefacturaListDetallefactura : medidor.getDetallefacturaList()) {
                Medidor oldIdmedidorOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getIdmedidor();
                detallefacturaListDetallefactura.setIdmedidor(medidor);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldIdmedidorOfDetallefacturaListDetallefactura != null) {
                    oldIdmedidorOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldIdmedidorOfDetallefacturaListDetallefactura = em.merge(oldIdmedidorOfDetallefacturaListDetallefactura);
                }
            }
            for (Mingas mingasListMingas : medidor.getMingasList()) {
                Medidor oldIdmedidorOfMingasListMingas = mingasListMingas.getIdmedidor();
                mingasListMingas.setIdmedidor(medidor);
                mingasListMingas = em.merge(mingasListMingas);
                if (oldIdmedidorOfMingasListMingas != null) {
                    oldIdmedidorOfMingasListMingas.getMingasList().remove(mingasListMingas);
                    oldIdmedidorOfMingasListMingas = em.merge(oldIdmedidorOfMingasListMingas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medidor medidor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidor persistentMedidor = em.find(Medidor.class, medidor.getIdmedidor());
            Usuarios idusuarioOld = persistentMedidor.getIdusuario();
            Usuarios idusuarioNew = medidor.getIdusuario();
            List<Corte> corteListOld = persistentMedidor.getCorteList();
            List<Corte> corteListNew = medidor.getCorteList();
            List<Detallefactura> detallefacturaListOld = persistentMedidor.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = medidor.getDetallefacturaList();
            List<Mingas> mingasListOld = persistentMedidor.getMingasList();
            List<Mingas> mingasListNew = medidor.getMingasList();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                medidor.setIdusuario(idusuarioNew);
            }
            List<Corte> attachedCorteListNew = new ArrayList<Corte>();
            for (Corte corteListNewCorteToAttach : corteListNew) {
                corteListNewCorteToAttach = em.getReference(corteListNewCorteToAttach.getClass(), corteListNewCorteToAttach.getIdcorte());
                attachedCorteListNew.add(corteListNewCorteToAttach);
            }
            corteListNew = attachedCorteListNew;
            medidor.setCorteList(corteListNew);
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            medidor.setDetallefacturaList(detallefacturaListNew);
            List<Mingas> attachedMingasListNew = new ArrayList<Mingas>();
            for (Mingas mingasListNewMingasToAttach : mingasListNew) {
                mingasListNewMingasToAttach = em.getReference(mingasListNewMingasToAttach.getClass(), mingasListNewMingasToAttach.getIdminga());
                attachedMingasListNew.add(mingasListNewMingasToAttach);
            }
            mingasListNew = attachedMingasListNew;
            medidor.setMingasList(mingasListNew);
            medidor = em.merge(medidor);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getMedidorList().remove(medidor);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getMedidorList().add(medidor);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Corte corteListOldCorte : corteListOld) {
                if (!corteListNew.contains(corteListOldCorte)) {
                    corteListOldCorte.setIdmedidor(null);
                    corteListOldCorte = em.merge(corteListOldCorte);
                }
            }
            for (Corte corteListNewCorte : corteListNew) {
                if (!corteListOld.contains(corteListNewCorte)) {
                    Medidor oldIdmedidorOfCorteListNewCorte = corteListNewCorte.getIdmedidor();
                    corteListNewCorte.setIdmedidor(medidor);
                    corteListNewCorte = em.merge(corteListNewCorte);
                    if (oldIdmedidorOfCorteListNewCorte != null && !oldIdmedidorOfCorteListNewCorte.equals(medidor)) {
                        oldIdmedidorOfCorteListNewCorte.getCorteList().remove(corteListNewCorte);
                        oldIdmedidorOfCorteListNewCorte = em.merge(oldIdmedidorOfCorteListNewCorte);
                    }
                }
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    detallefacturaListOldDetallefactura.setIdmedidor(null);
                    detallefacturaListOldDetallefactura = em.merge(detallefacturaListOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Medidor oldIdmedidorOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getIdmedidor();
                    detallefacturaListNewDetallefactura.setIdmedidor(medidor);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldIdmedidorOfDetallefacturaListNewDetallefactura != null && !oldIdmedidorOfDetallefacturaListNewDetallefactura.equals(medidor)) {
                        oldIdmedidorOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldIdmedidorOfDetallefacturaListNewDetallefactura = em.merge(oldIdmedidorOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            for (Mingas mingasListOldMingas : mingasListOld) {
                if (!mingasListNew.contains(mingasListOldMingas)) {
                    mingasListOldMingas.setIdmedidor(null);
                    mingasListOldMingas = em.merge(mingasListOldMingas);
                }
            }
            for (Mingas mingasListNewMingas : mingasListNew) {
                if (!mingasListOld.contains(mingasListNewMingas)) {
                    Medidor oldIdmedidorOfMingasListNewMingas = mingasListNewMingas.getIdmedidor();
                    mingasListNewMingas.setIdmedidor(medidor);
                    mingasListNewMingas = em.merge(mingasListNewMingas);
                    if (oldIdmedidorOfMingasListNewMingas != null && !oldIdmedidorOfMingasListNewMingas.equals(medidor)) {
                        oldIdmedidorOfMingasListNewMingas.getMingasList().remove(mingasListNewMingas);
                        oldIdmedidorOfMingasListNewMingas = em.merge(oldIdmedidorOfMingasListNewMingas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medidor.getIdmedidor();
                if (findMedidor(id) == null) {
                    throw new NonexistentEntityException("The medidor with id " + id + " no longer exists.");
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
            Medidor medidor;
            try {
                medidor = em.getReference(Medidor.class, id);
                medidor.getIdmedidor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medidor with id " + id + " no longer exists.", enfe);
            }
            Usuarios idusuario = medidor.getIdusuario();
            if (idusuario != null) {
                idusuario.getMedidorList().remove(medidor);
                idusuario = em.merge(idusuario);
            }
            List<Corte> corteList = medidor.getCorteList();
            for (Corte corteListCorte : corteList) {
                corteListCorte.setIdmedidor(null);
                corteListCorte = em.merge(corteListCorte);
            }
            List<Detallefactura> detallefacturaList = medidor.getDetallefacturaList();
            for (Detallefactura detallefacturaListDetallefactura : detallefacturaList) {
                detallefacturaListDetallefactura.setIdmedidor(null);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
            }
            List<Mingas> mingasList = medidor.getMingasList();
            for (Mingas mingasListMingas : mingasList) {
                mingasListMingas.setIdmedidor(null);
                mingasListMingas = em.merge(mingasListMingas);
            }
            em.remove(medidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medidor> findMedidorEntities() {
        return findMedidorEntities(true, -1, -1);
    }

    public List<Medidor> findMedidorEntities(int maxResults, int firstResult) {
        return findMedidorEntities(false, maxResults, firstResult);
    }

    private List<Medidor> findMedidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medidor.class));
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

    public Medidor findMedidor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medidor> rt = cq.from(Medidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
