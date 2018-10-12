/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CalidadAccionImplementar;
import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.CalidadSeguimientoAccion;
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
public class CalidadSeguimientoAccionFacade extends AbstractFacade<CalidadSeguimientoAccion> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalidadSeguimientoAccionFacade() {
        super(CalidadSeguimientoAccion.class);
    }
    public List<CalidadSeguimientoAccion> consultaSeguimiento(CalidadAccionImplementar accion) {
          Query q= getEntityManager().createNamedQuery("CalidadSeguimientoAccion.findByAccion");
          q.setParameter("accion",accion);
          return q.getResultList();
    }
    public List<CalidadSeguimientoAccion> consultaSeguimientoSinCerrar(String estado) {
          Query q= getEntityManager().createNamedQuery("CalidadSeguimientoAccion.findByEstado");
          q.setParameter("estado",estado);
          return q.getResultList();
    }
}
