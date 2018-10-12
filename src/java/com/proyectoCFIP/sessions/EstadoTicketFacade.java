/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.EstadoTicket;
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
public class EstadoTicketFacade extends AbstractFacade<EstadoTicket> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoTicketFacade() {
        super(EstadoTicket.class);
    }
    public List<EstadoTicket> consultaEstado(){
        Query q= getEntityManager().createNamedQuery("EstadoTicket.findAll");
        q.setMaxResults(2);
        return q.getResultList();
    }
}
