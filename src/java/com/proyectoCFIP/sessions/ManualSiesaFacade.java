/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.controller.RetornoCampos;
import com.proyectoCFIP.entities.ManualSiesa;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Junior Cabal
 */
@Stateless
public class ManualSiesaFacade extends AbstractFacade<ManualSiesa> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManualSiesaFacade() {
        super(ManualSiesa.class);
    }
    public ManualSiesa findByCodManual(String query){
          Query q= getEntityManager().createNamedQuery("ManualSiesa.findByCodigo");
          q.setParameter("codigo",query);
          try {
            return (ManualSiesa) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }   
    }
    public List<RetornoCampos> consultaManual() {
        TypedQuery<RetornoCampos> q= getEntityManager().createNamedQuery("ManualSiesa.findConsulta", RetornoCampos.class);
        return q.getResultList();
    }
}
