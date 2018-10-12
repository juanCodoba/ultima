/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
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
public class CronogramaMantenimientosFacade extends AbstractFacade<CronogramaMantenimientos> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CronogramaMantenimientosFacade() {
        super(CronogramaMantenimientos.class);
    }
    public List<CronogramaMantenimientos> consultaCronogramaPendiente(int estado){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByEstadoMantenimiento");
        q.setParameter("estadoMantenimiento", estado);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaCronogramaTipoPendiente(EstadoCronograma estado, TipoMantenimiento tipoMantenimiento){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByEstadoTipoMantenimiento");
        q.setParameter("estadoMantenimiento", estado);
        q.setParameter("idTipoMantenimiento", tipoMantenimiento);
        return q.getResultList();
    }
     public List<CronogramaMantenimientos> consultaCronogramaAnual(Date fecha){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByFechaProgMantenimiento");
        q.setParameter("fechaProgMantenimiento", fecha);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaTotalTipo(TipoMantenimiento tipoMantenimiento){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByTipoTotal");
        q.setParameter("idTipoMantenimiento", tipoMantenimiento);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaTotalTipoPrev(){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByTipoTotalPrev");
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaTotalTipoCorre(){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByTipoTotalCorre");
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaCronogramaEstadoUser(Usuario usuario){
         Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaCronogramaEstadoUserDocente(){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByUsuarioDocente");
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaCompu(EstadoCronograma estado,Computador compu){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByComputador");
        q.setParameter("estado", estado);
        q.setParameter("idComputador", compu);
        return q.getResultList();
    }
   public List<CronogramaMantenimientos> consultaReporteCorrectivo(Date fecha1, Date fecha2 ){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByReporteCorrectivo");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaReportePreventivo(Date fecha1, Date fecha2 ){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByReportePreventivo");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaReporteCorrectivoEstrellas(Date fecha1, Date fecha2 ){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByReporteCorrectivoEstrellas");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }
   public List<CronogramaMantenimientos> consultaSubReporteCorrectivo(){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findBySubReporteCorrectivo");
       
        return q.getResultList();
    }
   //mejora Continua
    public List<CronogramaMantenimientos> consultaEstadoTicketsDiagnostico(){
         Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByCierreTicketsSinValoracion");
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaEstadoTickets(Usuario usuario){
         Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByCierreTickets");
         q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaCierreTickets(Usuario usuario){
         Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByCierreTickets5dias");
         q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<CronogramaMantenimientos> consultaReporteCorrectivoTiempo(Date fecha1, Date fecha2 ){
        Query q= getEntityManager().createNamedQuery("CronogramaMantenimientos.findByReporteTiempoCorrectivo");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }
}
