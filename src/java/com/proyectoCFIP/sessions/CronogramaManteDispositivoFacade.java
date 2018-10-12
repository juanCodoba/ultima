/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.TipoMantenimiento;
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
public class CronogramaManteDispositivoFacade extends AbstractFacade<CronogramaManteDispositivo> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CronogramaManteDispositivoFacade() {
        super(CronogramaManteDispositivo.class);
    }
     
    public List<CronogramaManteDispositivo> consultaCronogramaTipoPendiente(boolean estado, TipoMantenimiento tipoMantenimiento){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDispositivo.findByEstadoTipoMantenimiento");
        q.setParameter("estadoMantenimientoDis", estado);
        q.setParameter("idTipoMantenimiento", tipoMantenimiento);
        return q.getResultList();
    }
     public List<CronogramaManteDispositivo> consultaCronogramaEstadoUser(Usuario usuario){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDispositivo.findByUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<CronogramaManteDispositivo> consultaCronogramaEstadoUserDocente(){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDispositivo.findByUsuarioDocente");
        return q.getResultList();
    }
    public List<CronogramaManteDispositivo> consultaTotalTipoPrev(){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDispositivo.findByTipoTotalPrev");
        return q.getResultList();
    }
    public List<CronogramaManteDispositivo> consultaTotalTipoCorre(){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDispositivo.findByTipoTotalCorre");
        return q.getResultList();
    }
}
