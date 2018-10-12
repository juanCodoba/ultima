/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.DiagnosticoMantenimiento;
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
public class DiagnosticoMantenimientoFacade extends AbstractFacade<DiagnosticoMantenimiento> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoMantenimientoFacade() {
        super(DiagnosticoMantenimiento.class);
    }
    public List<DiagnosticoMantenimiento> consultaDiagnostico(Computador computador){
        Query q= getEntityManager().createNamedQuery("DiagnosticoMantenimiento.findByIdComputador");
        q.setParameter("idComputador", computador);
        return q.getResultList();
    }
      
    public List<DiagnosticoMantenimiento> consultaTicket (CronogramaMantenimientos crono){
        Query q= getEntityManager().createNamedQuery("DiagnosticoMantenimiento.findByTicket");
        q.setParameter("idCronogramaMantenimientos", crono);
        return q.getResultList();
    }
    
     public List<DiagnosticoMantenimiento> diagnosticosTotales (){
        Query q= getEntityManager().createNamedQuery("DiagnosticoMantenimiento.findByDiagnosticosTotales");
        return q.getResultList();
    }
   
    
}
