/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.AccionCorrectiva;
import com.proyectoCFIP.entities.CalidadSeguimientoCorrectiva;
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
public class CalidadSeguimientoCorrectivaFacade extends AbstractFacade<CalidadSeguimientoCorrectiva> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalidadSeguimientoCorrectivaFacade() {
        super(CalidadSeguimientoCorrectiva.class);
    }

    public List<CalidadSeguimientoCorrectiva> consultaSeguimiento(AccionCorrectiva IdaccionCorrectiva) {
        Query q = getEntityManager().createNamedQuery("CalidadSeguimientoCorrectiva.findByAccionDos");
        q.setParameter("IdaccionCorrectiva", IdaccionCorrectiva);
        return q.getResultList();
    }

    public List<CalidadSeguimientoCorrectiva> consultaSeguimientoSinCerrar() {
        Query q = getEntityManager().createNamedQuery("CalidadSeguimientoCorrectiva.findByEstado");
        return q.getResultList();
    }
}
