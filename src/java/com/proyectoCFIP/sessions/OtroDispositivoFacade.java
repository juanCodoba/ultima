/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Area;
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
public class OtroDispositivoFacade extends AbstractFacade<OtroDispositivo> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OtroDispositivoFacade() {
        super(OtroDispositivo.class);
    }
    public OtroDispositivo findByCodigoOtroDispositivo(String query){
          Query q= getEntityManager().createNamedQuery("OtroDispositivo.findByCodigoDispositivo");
          q.setParameter("codigoDispositivo",query);
          try {
            return (OtroDispositivo) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }
    }
    
    public List<OtroDispositivo> consultaOtroDispositivoActivo(boolean estado){
        Query q= getEntityManager().createNamedQuery("OtroDispositivo.findByEstadoDispositivo");
        q.setParameter("estadoDispositivo", estado);
        return q.getResultList();
    }
    public List<OtroDispositivo> consultaUsuario(Usuario usuario){
        Query q= getEntityManager().createNamedQuery("OtroDispositivo.findByUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    
     public List<OtroDispositivo> consultaArea(Area area){
        Query q= getEntityManager().createNamedQuery("OtroDispositivo.findByArea");
        q.setParameter("idArea", area);
        return q.getResultList();
    }
    public List<OtroDispositivo> consultaOtroDispositivoProg(boolean estado){
        Query q= getEntityManager().createNamedQuery("OtroDispositivo.findByEstadoProgramadoDis");
        q.setParameter("estadoProgramadoDis", estado);
        return q.getResultList();
    }
}
