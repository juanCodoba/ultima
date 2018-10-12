/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.MaquinaConfecciones;
import com.proyectoCFIP.entities.TipoMantenimiento;
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
public class CronogramaMantenimientoMaquinaFacade extends AbstractFacade<CronogramaMantenimientoMaquina> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CronogramaMantenimientoMaquinaFacade() {
        super(CronogramaMantenimientoMaquina.class);
    }
    public List<CronogramaMantenimientoMaquina> consultaTipoMantenimientoEstado(TipoMantenimiento tipo, EstadoCronograma estado){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientoMaquina.findByTipoMantenimientoEstado");
        q.setParameter("idTipoMantenimiento", tipo);
        q.setParameter("idEstadoCronograma", estado);
        return q.getResultList();
    }
    public List<CronogramaMantenimientoMaquina> consultaTipoMantenimientoTotalPrev(TipoMantenimiento tipo1, TipoMantenimiento tipo2){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientoMaquina.findByTipoMantenimientoPrev");
        q.setParameter("idTipoMantenimiento1", tipo1);
        q.setParameter("idTipoMantenimiento2", tipo2);
        return q.getResultList();
    }
    public List<CronogramaMantenimientoMaquina> consultaTipoMantenimientoTotal(TipoMantenimiento tipo){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientoMaquina.findByTipoMantenimiento");
        q.setParameter("idTipoMantenimiento", tipo);
        return q.getResultList();
    }
     public List<CronogramaMantenimientoMaquina> consultaMaquina(EstadoCronograma estado,MaquinaConfecciones maquina){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientoMaquina.findByMaquina");
        q.setParameter("estado", estado);
        q.setParameter("idMaquinaConfecciones", maquina);
        return q.getResultList();
    }
}
