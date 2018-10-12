/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CambioRegistro;
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
public class CambioRegistroFacade extends AbstractFacade<CambioRegistro> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CambioRegistroFacade() {
        super(CambioRegistro.class);
    }
    public List<CambioRegistro> consultaCambioRegistroSinSolucionar(boolean estado){
        Query q= getEntityManager().createNamedQuery("CambioRegistro.findByEstadoCambio");
        q.setParameter("estadoCambio", estado);
        return q.getResultList();
    }
    
}
