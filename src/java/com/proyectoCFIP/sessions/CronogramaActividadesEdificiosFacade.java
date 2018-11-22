/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import com.proyectoCFIP.entities.EstadoActividad;
import com.proyectoCFIP.entities.TipoActividad;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis Carlos Cabal
 */
@Stateless
public class CronogramaActividadesEdificiosFacade extends AbstractFacade<CronogramaActividadesEdificios> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CronogramaActividadesEdificiosFacade() {
        super(CronogramaActividadesEdificios.class);
    }
    
        public List<CronogramaActividadesEdificios> consultaTicketDaños(TipoActividad tipoActividad,Usuario usuario){
        Query q= getEntityManager().createNamedQuery("CronogramaActividadesEdificios.findByConsultaTickesDaños");
        q.setParameter("idTipoActividad", tipoActividad);
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<CronogramaActividadesEdificios> tipoActividad(TipoActividad tipoActividad){
        Query q= getEntityManager().createNamedQuery("CronogramaActividadesEdificios.findByTipoActividad");
        q.setParameter("idTipoActividad", tipoActividad);
        return q.getResultList();
    }
    public List<CronogramaActividadesEdificios> consultaCronograma(TipoActividad tipoActividad,EstadoActividad estadoActividad){
        Query q= getEntityManager().createNamedQuery("CronogramaActividadesEdificios.findByConsultaCronograma");
        q.setParameter("idTipoActividad", tipoActividad);
        q.setParameter("idEstadoActividad", estadoActividad);
        return q.getResultList();
    }   
    public List<CronogramaActividadesEdificios> consultaDaños(Date fecha1, Date fecha2 ){
        Query q= getEntityManager().createNamedQuery("CronogramaActividadesEdificios.findByIndicadorDano");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }
    
}
