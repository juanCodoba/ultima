/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.ActividadNovedad;
import com.proyectoCFIP.entities.Factores;
import com.proyectoCFIP.entities.Novedad;
import com.proyectoCFIP.entities.Procesos;
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
public class ActividadNovedadFacade extends AbstractFacade<ActividadNovedad> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadNovedadFacade() {
        super(ActividadNovedad.class);
    }

    public List<ActividadNovedad> consultaTodo() {
        Query q = getEntityManager().createNamedQuery("ActividadNovedad.findAll");
        return q.getResultList();
    }

    public List<ActividadNovedad> consultaNovedad(Novedad idNovedad) {
        Query q = getEntityManager().createNamedQuery("ActividadNovedad.findByIdNovedad");
        q.setParameter("idNovedad", idNovedad);
        return q.getResultList();
    }
}
