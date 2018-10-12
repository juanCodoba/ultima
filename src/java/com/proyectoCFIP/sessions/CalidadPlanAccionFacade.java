/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.Usuario;
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
public class CalidadPlanAccionFacade extends AbstractFacade<CalidadPlanAccion> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalidadPlanAccionFacade() {
        super(CalidadPlanAccion.class);
    }
    public List<CalidadPlanAccion> ordenarAccion(){
        Query q= getEntityManager().createNamedQuery("CalidadPlanAccion.findAll");
        return q.getResultList();
    }
}