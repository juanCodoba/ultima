/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Usuario;
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
public class ProcesosFacade extends AbstractFacade<Procesos> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesosFacade() {
        super(Procesos.class);
    }

    public List<Procesos> consultaUsuario(Usuario usuario) {
        Query q = getEntityManager().createNamedQuery("Procesos.findByIdUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }

}
