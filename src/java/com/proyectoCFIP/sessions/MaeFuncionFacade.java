/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.MaeFuncion;

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
public class MaeFuncionFacade extends AbstractFacade<MaeFuncion> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeFuncionFacade() {
        super(MaeFuncion.class);
    }

    public List<MaeFuncion> consultaUsuarioTipo(Cargos idCargo) {
        Query q = getEntityManager().createNamedQuery("MaeFuncion.findByIdCargo");
        q.setParameter("idCargo", idCargo);
        return q.getResultList();
    }

    public List<MaeFuncion> consultaCargo(Cargos idCargo) {
        Query q = getEntityManager().createNamedQuery("MaeFuncion.findByIdCargo");
        q.setParameter("idCargo", idCargo);
        return q.getResultList();
    }

}
