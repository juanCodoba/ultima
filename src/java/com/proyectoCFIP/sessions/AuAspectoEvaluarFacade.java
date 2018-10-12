/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.AuProcesoEvaluado;
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
public class AuAspectoEvaluarFacade extends AbstractFacade<AuAspectoEvaluar> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuAspectoEvaluarFacade() {
        super(AuAspectoEvaluar.class);
    }
    public List<AuAspectoEvaluar> consultaProceso(AuProcesoEvaluado idAuProcesoEvaluado) {
          Query q= getEntityManager().createNamedQuery("AuAspectoEvaluar.findByProcesoEvaluado");
          q.setParameter("idAuProcesoEvaluado",idAuProcesoEvaluado);
          return q.getResultList();
    }
}
