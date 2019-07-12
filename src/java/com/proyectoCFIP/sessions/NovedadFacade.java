/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Novedad;
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
public class NovedadFacade extends AbstractFacade<Novedad> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NovedadFacade() {
        super(Novedad.class);
    }

    public List<Novedad> consultaEstado() {
        Query q = getEntityManager().createNamedQuery("Novedad.findByEstado");
        return q.getResultList();
    }

    public List<Novedad> consultaTodo() {
        Query q = getEntityManager().createNamedQuery("Novedad.findAll");
        return q.getResultList();
    }

}
