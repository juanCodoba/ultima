/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Facturado;
import com.proyectoCFIP.entities.FacturadoAu;
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
public class FacturadoAuFacade extends AbstractFacade<FacturadoAu> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturadoAuFacade() {
        super(FacturadoAu.class);
    }

    public List<FacturadoAu> consultaCargo(Facturado idFacturado) {
        Query q = getEntityManager().createNamedQuery("FacturadoAu.findByIdFacturado");
        q.setParameter("idFacturado", idFacturado);
        return q.getResultList();
    }
}
