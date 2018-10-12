/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.SubprocesoSuit;
import com.proyectoCFIP.entities.Suit;
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
public class SubprocesoSuitFacade extends AbstractFacade<SubprocesoSuit> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubprocesoSuitFacade() {
        super(SubprocesoSuit.class);
    }
    public List<SubprocesoSuit> consultaSubProcesoSuit(Suit suit){
        Query q= getEntityManager().createNamedQuery("SubprocesoSuit.findByIdSuit");
        q.setParameter("idSuit", suit);
        return q.getResultList();
    }
}
