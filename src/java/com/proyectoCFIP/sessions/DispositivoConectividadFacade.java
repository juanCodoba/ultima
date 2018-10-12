/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.DispositivoConectividad;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Junior Cabal
 */
@Stateless
public class DispositivoConectividadFacade extends AbstractFacade<DispositivoConectividad> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DispositivoConectividadFacade() {
        super(DispositivoConectividad.class);
    }
    public DispositivoConectividad findByCodigoDispositivo(String query){
          Query q= getEntityManager().createNamedQuery("DispositivoConectividad.findByCodigoDispositivo");
          q.setParameter("codigo",query);
          try {
            return (DispositivoConectividad) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }
    }
    
     public List<DispositivoConectividad> consultaArea(Area area){
        Query q= getEntityManager().createNamedQuery("DispositivoConectividad.findByArea");
        q.setParameter("idArea", area);
        return q.getResultList();
    }
     public List<DispositivoConectividad> consultaUsuario(Usuario usuario){
        Query q= getEntityManager().createNamedQuery("DispositivoConectividad.findByUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<DispositivoConectividad> consultaOtroDispositivoProg(boolean estado){
        Query q= getEntityManager().createNamedQuery("DispositivoConectividad.findByEstadoProg");
        q.setParameter("estadoProgramado", estado);
        return q.getResultList();
    }
}
