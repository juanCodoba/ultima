/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.DiagnosticoMaquina;
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
public class DiagnosticoMaquinaFacade extends AbstractFacade<DiagnosticoMaquina> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoMaquinaFacade() {
        super(DiagnosticoMaquina.class);
    }
   public List<DiagnosticoMaquina> consultaTicket (CronogramaMantenimientoMaquina crono){
        Query q= getEntityManager().createNamedQuery("DiagnosticoMaquina.findByTicket");
        q.setParameter("idCronogramaMantenimientoMaquina", crono);
        return q.getResultList();
    }
}
