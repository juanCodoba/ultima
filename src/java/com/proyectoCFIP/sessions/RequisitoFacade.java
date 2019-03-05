/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Requisito;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis Carlos Cabal
 */
@Stateless
public class RequisitoFacade extends AbstractFacade<Requisito> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequisitoFacade() {
        super(Requisito.class);
    } 
        public List<Requisito> consultaCargo(Cargos idCargo) {
        Query q = getEntityManager().createNamedQuery("Requisito.findByIdCargo");
        q.setParameter("idCargo", idCargo);
        return q.getResultList();
    }
}
