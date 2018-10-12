/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.DiagnosticoActividadEdificios;
import com.proyectoCFIP.entities.DiagnosticoMaquina;
import com.proyectoCFIP.entities.TipoActividad;
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
public class DiagnosticoActividadEdificiosFacade extends AbstractFacade<DiagnosticoActividadEdificios> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoActividadEdificiosFacade() {
        super(DiagnosticoActividadEdificios.class);
    }
    public List<DiagnosticoActividadEdificios> consultaDiagnosticoAseos(TipoActividad tipoActividad){
        Query q= getEntityManager().createNamedQuery("DiagnosticoActividadEdificios.findByTipoActividad");
        q.setParameter("idTipoActividad", tipoActividad);
        return q.getResultList();
    }
     public List<DiagnosticoActividadEdificios> consultaTicket (CronogramaActividadesEdificios crono){
        Query q= getEntityManager().createNamedQuery("DiagnosticoActividadEdificios.findByTicket");
        q.setParameter("idCronogramaActividadesEdificios", crono);
        return q.getResultList();
    }
}
