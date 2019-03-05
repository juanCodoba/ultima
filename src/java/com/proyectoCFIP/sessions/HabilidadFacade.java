/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Habilidad;
import com.proyectoCFIP.entities.Requisito;
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
public class HabilidadFacade extends AbstractFacade<Habilidad> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HabilidadFacade() {
        super(Habilidad.class);
    }

    public List<Habilidad> consultaCargo(Cargos idCargo) {
        Query q = getEntityManager().createNamedQuery("Habilidad.findByIdCargo");
        q.setParameter("idCargo", idCargo);
        return q.getResultList();
    }

}
