/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.EstadoTicket;
import com.proyectoCFIP.entities.ReporteSiesa;
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
public class ReporteSiesaFacade extends AbstractFacade<ReporteSiesa> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteSiesaFacade() {
        super(ReporteSiesa.class);
    }
    public List<ReporteSiesa> consultaReporteEstadoUser(Usuario usuario){
        Query q= getEntityManager().createNamedQuery("ReporteSiesa.findByUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<ReporteSiesa> consultaReporteEstadoTicket(EstadoTicket estadoTicket){
        Query q= getEntityManager().createNamedQuery("ReporteSiesa.findByEstadoTicket");
        q.setParameter("idEstadoTicket", estadoTicket);
        return q.getResultList();
    }
     public List<ReporteSiesa> consultaReporteEstadoSinDiagnosticar(EstadoTicket estadoTicket, EstadoTicket estadoTicket2 ){
        Query q= getEntityManager().createNamedQuery("ReporteSiesa.findByEstadoTicketSinDiag");
        q.setParameter("idEstadoTicket", estadoTicket);
        q.setParameter("idEstadoTicket2", estadoTicket2);
        return q.getResultList();
    }
}
