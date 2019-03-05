/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Usuario;
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
public class CargosFacade extends AbstractFacade<Cargos> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargosFacade() {
        super(Cargos.class);
    }
 
        public List<Cargos> consultaUsuario(Usuario idUsuario) {
        Query q = getEntityManager().createNamedQuery("Cargos.findByIdUsuario");
        q.setParameter("idResponsableCargo", idUsuario);
        return q.getResultList();
    }
}
