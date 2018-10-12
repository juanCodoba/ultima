/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.HistorialOtroDispositivo;
import com.proyectoCFIP.entities.OtroDispositivo;
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
public class HistorialOtroDispositivoFacade extends AbstractFacade<HistorialOtroDispositivo> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialOtroDispositivoFacade() {
        super(HistorialOtroDispositivo.class);
    }
    public List<HistorialOtroDispositivo> consultaDispositivo(OtroDispositivo dispositivo){
        Query q= getEntityManager().createNamedQuery("HistorialOtroDispositivo.findByConsultaDispositivo");
        q.setParameter("idOtroDispositivo", dispositivo);
        return q.getResultList();
    }
}
