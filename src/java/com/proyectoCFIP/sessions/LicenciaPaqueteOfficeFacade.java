/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;


import com.proyectoCFIP.entities.LicenciaPaqueteOffice;
import com.proyectoCFIP.entities.PaqueteOffice;
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
public class LicenciaPaqueteOfficeFacade extends AbstractFacade<LicenciaPaqueteOffice> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenciaPaqueteOfficeFacade() {
        super(LicenciaPaqueteOffice.class);
    }
    
     public LicenciaPaqueteOffice findByidLicenciaPaquete(String query){
          Query q= getEntityManager().createNamedQuery("LicenciaPaqueteOffice.findByCodigoLic");
          q.setParameter("codigoLic",query);
          try {
            return (LicenciaPaqueteOffice) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }
          
    }
      public List<LicenciaPaqueteOffice> consultaPaquete(PaqueteOffice paquete){
        Query q= getEntityManager().createNamedQuery("LicenciaPaqueteOffice.findByPaquete");
        q.setParameter("idPaqueteOffice", paquete);
        return q.getResultList();
    }
}
