/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.CronogramaComponente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author junio
 */
@Stateless
public class CronogramaComponenteFacade extends AbstractFacade<CronogramaComponente> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CronogramaComponenteFacade() {
        super(CronogramaComponente.class);
    }
     public List<CronogramaComponente> consultaComputador(Computador idComputador){
        Query q= getEntityManager().createNamedQuery("CronogramaComponente.findByComputador");
        q.setParameter("idComputador", idComputador);
        return q.getResultList();
    }
     public List<CronogramaComponente> consultaFecha(){
        Query q= getEntityManager().createNamedQuery("CronogramaComponente.findByFechaCambio");
        return q.getResultList();
    }
}
