/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Facturado;
import java.util.Date;
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
public class FacturadoFacade extends AbstractFacade<Facturado> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturadoFacade() {
        super(Facturado.class);
    }

    public List<Facturado> consultaTodo() {
        Query q = getEntityManager().createNamedQuery("Facturado.findAll");
        return q.getResultList();
    }

    public List<Facturado> consultaReporteCorrectivoTiempo(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("Facturado.findByReporteTiempoCorrectivo");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }
}
