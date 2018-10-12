/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.TipoActualizacion;
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
public class TipoActualizacionFacade extends AbstractFacade<TipoActualizacion> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoActualizacionFacade() {
        super(TipoActualizacion.class);
    }
    public List<TipoActualizacion> consultaTipoActualizacion(){
        Query q= getEntityManager().createNamedQuery("TipoActualizacion.findByConsultaTipoActualizacion");
        return q.getResultList();
    }
}
