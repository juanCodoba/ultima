/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CronogramaManteDisCon;
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
public class CronogramaManteDisConFacade extends AbstractFacade<CronogramaManteDisCon> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CronogramaManteDisConFacade() {
        super(CronogramaManteDisCon.class);
    }
   
      public List<CronogramaManteDisCon> consultaCronogramaTipoPendiente(boolean estado, TipoMantenimiento tipoMantenimiento){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDisCon.findByEstadoTipoMantenimiento");
        q.setParameter("estadoMantenimiento", estado);
        q.setParameter("idTipoMantenimiento", tipoMantenimiento);
        return q.getResultList();
    }
    public List<CronogramaManteDisCon> consultaTotalTipoPrev(){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDisCon.findByTipoTotalPrev");
        return q.getResultList();
    }
    public List<CronogramaManteDisCon> consultaTotalTipoCorre(){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDisCon.findByTipoTotalCorre");
        return q.getResultList();
    }
    public List<CronogramaManteDisCon> consultaCronogramaEstadoUser(Usuario usuario){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDisCon.findByUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<CronogramaManteDisCon> consultaCronogramaEstadoUserDocente(){
        Query q= getEntityManager().createNamedQuery("CronogramaManteDisCon.findByUsuarioDocente");
        return q.getResultList();
    }
}
