/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Suit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Junior Cabal
 */
@Stateless
public class SuitFacade extends AbstractFacade<Suit> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuitFacade() {
        super(Suit.class);
    }
    
}
