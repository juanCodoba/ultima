/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.Tipo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Junior Cabal
 */
@Stateless
public class ModeloFacade extends AbstractFacade<Modelo> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeloFacade() {
        super(Modelo.class);
    }
    public List<Modelo> consultaTipoCompu(){
        Query q= getEntityManager().createNamedQuery("Modelo.findByTipoCompu");
        return q.getResultList();
    }
    public List<Modelo> consultaTipoDispo(){
        Query q= getEntityManager().createNamedQuery("Modelo.findByTipoDispo");
        return q.getResultList();
    }
    public List<Modelo> consultaTipoDispoConec(){
        Query q= getEntityManager().createNamedQuery("Modelo.findByTipoDispoConec");
        return q.getResultList();
    }
       public List<Modelo> consultaTipo(Tipo idTipo){
        Query q= getEntityManager().createNamedQuery("Modelo.findByTipo");
        q.setParameter("idTipo",idTipo);
        return q.getResultList();
    }
}
