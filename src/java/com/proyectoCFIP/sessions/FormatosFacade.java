/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Subprocesos;
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
public class FormatosFacade extends AbstractFacade<Formatos> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormatosFacade() {
        super(Formatos.class);
    }
    public Formatos findByCodFormato(String query){
          Query q= getEntityManager().createNamedQuery("Formatos.findByCodigoFormato");
          q.setParameter("codigoFormato",query);
          try {
            return (Formatos) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }   
    }
      public List<Formatos> consultaFormatoProceso(Subprocesos subprocesos){
        Query q= getEntityManager().createNamedQuery("Formatos.findBySubProceso");
        q.setParameter("idSubproceso", subprocesos);
        return q.getResultList();
    }
    public List<Formatos> ordenarFormato(){
        Query q= getEntityManager().createNamedQuery("Formatos.findAll");
        return q.getResultList();
    }
    
    public List<Formatos> conFormatos(){
         TypedQuery<Formatos> query =
        em.createQuery("SELECT f FROM Formatos f ORDER BY f.codigoFormato", Formatos.class);
        return query.getResultList();
    }
}
