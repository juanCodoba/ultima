/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.FtFichaTecnica;
import com.proyectoCFIP.entities.FtModificaciones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author junio
 */
@Stateless
public class FtModificacionesFacade extends AbstractFacade<FtModificaciones> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FtModificacionesFacade() {
        super(FtModificaciones.class);
    }
    public List<FtModificaciones> consultaFicha(FtFichaTecnica ficha) {
          Query q= getEntityManager().createNamedQuery("FtModificaciones.findByIdFicha");
          q.setParameter("ficha", ficha);
          return q.getResultList();
    }
    public List<FtModificaciones> consultaTodas() {
          Query q= getEntityManager().createNamedQuery("FtModificaciones.findByTodas");
          return q.getResultList();
    }
}
