/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Licencia;
import com.proyectoCFIP.entities.SistemaOperativo;
import com.proyectoCFIP.entities.TipoLicencia;
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
public class LicenciaFacade extends AbstractFacade<Licencia> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenciaFacade() {
        super(Licencia.class);
    }
    public List<Licencia> consultaTipoLicencia(TipoLicencia tipoLicencia){
        Query q= getEntityManager().createNamedQuery("Licencia.findByTipoLicencia");
        q.setParameter("idTipoLicencia", tipoLicencia);
        return q.getResultList();
    }
    public List<Licencia> consultaSO(SistemaOperativo sistemaOperativo){
        Query q= getEntityManager().createNamedQuery("Licencia.findBySistemaOperativo");
        q.setParameter("idSistemaOperativo", sistemaOperativo);
        return q.getResultList();
    }
   
    public List<Licencia> consultaTipoLicenciaAplicativo(){
        Query q= getEntityManager().createNamedQuery("Licencia.findByTipoLicenciaApp");
        return q.getResultList();
    }
    public Licencia findByidLicencia(String query){
          Query q= getEntityManager().createNamedQuery("Licencia.findByCodigoLicencia");
          q.setParameter("codigoLicencia",query);
          try {
            return (Licencia) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }
          
    }
}
