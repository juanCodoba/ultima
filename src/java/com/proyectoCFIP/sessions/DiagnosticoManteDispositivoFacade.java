/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.DiagnosticoManteDispositivo;
import com.proyectoCFIP.entities.DiagnosticoMantenimiento;
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
public class DiagnosticoManteDispositivoFacade extends AbstractFacade<DiagnosticoManteDispositivo> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoManteDispositivoFacade() {
        super(DiagnosticoManteDispositivo.class);
    }
      public List<DiagnosticoManteDispositivo> consultaDiagnosticoDis(OtroDispositivo dispositivo){
        Query q= getEntityManager().createNamedQuery("DiagnosticoManteDispositivo.findByIdOtroDispositivo");
        q.setParameter("idOtroDispositivo", dispositivo);
        return q.getResultList();
    }
      public List<DiagnosticoManteDispositivo> consultaTicket (CronogramaManteDispositivo crono){
        Query q= getEntityManager().createNamedQuery("DiagnosticoManteDispositivo.findByTicket");
        q.setParameter("idCronogramaManteDispositivo", crono);
        return q.getResultList();
    }
}
