/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.TipoSeccion;
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
public class SeccionFacade extends AbstractFacade<Seccion> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeccionFacade() {
        super(Seccion.class);
    }
     public List<Seccion> consultaSeccionEspecial(TipoSeccion tipo1, TipoSeccion tipo2) {
        Query q= getEntityManager().createNamedQuery("Seccion.findByIdTipoSeccion");
          q.setParameter("idTipoSeccion1",tipo1);
          q.setParameter("idTipoSeccion2",tipo2);
          return q.getResultList();
    }
}
