/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.EstadoSolicitudEdificio;
import com.proyectoCFIP.entities.EstadoSolicitudRefrigerio;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.SolicitudEdificio;
import com.proyectoCFIP.entities.Usuario;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 *
 * @author Junior Cabal
 */
@Stateless
public class SolicitudEdificioFacade extends AbstractFacade<SolicitudEdificio> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudEdificioFacade() {
        super(SolicitudEdificio.class);
    }
 public SolicitudEdificio findByValidarReserva(Date query1, Date query2,Date query3, Seccion idSeccion){
          Query q= getEntityManager().createNamedQuery("SolicitudEdificio.findByValidacionFecha");
          q.setParameter("fechaAlquiler",query1);
          q.setParameter("horaInicioAlquiler",query2);
          q.setParameter("horaFinAlquiler",query3);
          q.setParameter("idSeccion",idSeccion);
          try {
            return  (SolicitudEdificio) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }
    }
     public  SolicitudEdificio findByValidarAudiovisuales(Date query1, Date query2,Date query3, Seccion idSeccion){
          Query q= getEntityManager().createNamedQuery("SolicitudEdificio.findByValidacionAudiovisuales");
          q.setParameter("fechaAlquiler",query1);
          q.setParameter("horaInicioAlquiler",query2);
          q.setParameter("horaFinAlquiler",query3);
          q.setParameter("idSeccion",idSeccion);
          try {
            return   (SolicitudEdificio) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }
    }
    public List<SolicitudEdificio> consultaEstado(EstadoSolicitudEdificio estado) {
        Query q= getEntityManager().createNamedQuery("SolicitudEdificio.findByIdSolicitudEdificio");
          q.setParameter("idEstadoSolicitudEdificio",estado);
          return q.getResultList();
    }
    
    public List<SolicitudEdificio> consultaEstadoRefrigerio(EstadoSolicitudRefrigerio estado) {
        Query q= getEntityManager().createNamedQuery("SolicitudEdificio.findByEstadoRefrigerio");
        q.setParameter("idEstadoSolicitudRefrigerio" , estado);
        return  q.getResultList();
    }
     public List<SolicitudEdificio> consultaEstadoDia(Date fecha) {
        Query q= getEntityManager().createNamedQuery("SolicitudEdificio.findByEstadoDia");;
        q.setParameter("fechaAlquiler" , fecha);
        return  q.getResultList();
    }
    public List<SolicitudEdificio> consultaSolicitudUser(Usuario usuario){
        Query q= getEntityManager().createNamedQuery("SolicitudEdificio.findByIdUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
}
