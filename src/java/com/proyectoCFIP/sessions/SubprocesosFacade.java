/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Subprocesos;
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
public class SubprocesosFacade extends AbstractFacade<Subprocesos> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubprocesosFacade() {
        super(Subprocesos.class);
    }
    public List<Subprocesos> consultaSubProcesos(Procesos procesos){
        Query q= getEntityManager().createNamedQuery("Subprocesos.findByIdProceso");
        q.setParameter("idProceso", procesos);
        return q.getResultList();
    }
}
