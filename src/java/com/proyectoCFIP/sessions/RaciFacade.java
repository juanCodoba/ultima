/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Raci;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis Carlos Cabal
 */
@Stateless
public class RaciFacade extends AbstractFacade<Raci> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RaciFacade() {
        super(Raci.class);
    }
    
}
