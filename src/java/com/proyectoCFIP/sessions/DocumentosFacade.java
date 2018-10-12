/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Subprocesos;
//import com.proyectoCFIP.entities.Documentos_;
import com.proyectoCFIP.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Junior Cabal
 */
@Stateless
public class DocumentosFacade extends AbstractFacade<Documentos> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentosFacade() {
        super(Documentos.class);
    }
    public Documentos findByCodDocumento(String query){
          Query q= getEntityManager().createNamedQuery("Documentos.findByCodigoDocumento");
          q.setParameter("codigoDocumento",query);
          try {
            return (Documentos) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }   
    }
    /*public List<Documentos> consultaUsuario(Usuario usuario) {
       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Documentos> cq = cb.createQuery(Documentos.class);
       Root<Documentos> documento = cq.from(Documentos.class);
       cq.where(cb.isMember(usuario, documento.get(Documentos_.usuarioList)));
       TypedQuery<Documentos> q = getEntityManager().createQuery(cq);
       try {
           return q.getResultList();
       } catch (NoResultException ex) {
           return null;
       }
   }*/
    public List<Documentos> conDocumentos(){
         TypedQuery<Documentos> query =
        em.createQuery("SELECT f FROM Documentos f ORDER BY f.codigoDocumento", Documentos.class);
        return query.getResultList();
    }
    public List<Documentos> consultaDocumentosProceso(Procesos idProceso){
        Query q= getEntityManager().createNamedQuery("Documentos.findBySubProceso");
        q.setParameter("idProceso", idProceso);
        return q.getResultList();
    }
}
