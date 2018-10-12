/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Roles;
import com.proyectoCFIP.entities.SstReporteUsuario;
import com.proyectoCFIP.entities.Usuario;
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
public class SstReporteUsuarioFacade extends AbstractFacade<SstReporteUsuario> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SstReporteUsuarioFacade() {
        super(SstReporteUsuario.class);
    }
    public List<SstReporteUsuario> consultaUsuario(Usuario idUsuario) {
        Query q= getEntityManager().createNamedQuery("SstReporteUsuario.findByUsuario");
          q.setParameter("idUsuario",idUsuario);
          return q.getResultList();
    }
}
