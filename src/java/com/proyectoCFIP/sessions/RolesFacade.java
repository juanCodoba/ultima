/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Roles;
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
public class RolesFacade extends AbstractFacade<Roles> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }
    public List<Roles> consultaRol(){
        Query q= getEntityManager().createNamedQuery("Roles.findAll");
        q.setMaxResults(18);
        return q.getResultList();
    }
    public List<Roles> consultaRolCalidad(){
        Query q= getEntityManager().createNamedQuery("Roles.findByIdRol");
        q.setParameter("idRol", "CALIDAD");
        return q.getResultList();
    }
    
}
