/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.IndicadorSaludAcupacional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis Carlos Cabal
 */
@Stateless
public class IndicadorSaludAcupacionalFacade extends AbstractFacade<IndicadorSaludAcupacional> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicadorSaludAcupacionalFacade() {
        super(IndicadorSaludAcupacional.class);
    }
    
}
